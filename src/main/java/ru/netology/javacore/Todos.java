package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private Set<String> tasks;
    private final int taskSize = 7;
    private Logging log;

    public Todos() {
        tasks = new HashSet<>();
        this.log = new Logging();
    }

    public void addTask(Operation request) {
        String taskLow = request.task.toLowerCase();
        if (tasks.size() < taskSize) {
            tasks.add(taskLow);
            log.saveLog(request);
        }
    }

    public void removeTask(Operation request) {
        String taskLow = request.task.toLowerCase();
        tasks.remove(taskLow);
        log.saveLog(request);
    }

    public void restore() {
        Operation cancel = log.getLog().pollLast();
        switch (cancel.toDo) {
            case ADD:
                this.removeTask(cancel);
                log.getLog().removeLast();
                break;
            case REMOVE:
                this.addTask(cancel);
                log.getLog().removeLast();
                break;
            default:
                System.out.println("В списке задач пусто");
        }
    }

    public String getAllTasks() {
       Optional<String> reducedTasks = tasks.stream().
                sorted(Comparator.naturalOrder()).
                reduce((value, combinedValue) -> value + ";" + combinedValue);
       if (reducedTasks.isPresent()) {
           return reducedTasks.get();
       } else return "Задач нет";
    }

}
