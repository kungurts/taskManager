package ru.netology.javacore;

public class Operation {
    OperationType toDo;
    String task;

    public Operation(OperationType toDo, String task) {
        this.toDo = toDo;
        this.task = task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "toDo=" + toDo +
                ", task='" + task + '\'' +
                '}';
    }
}
