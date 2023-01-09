package ru.netology.javacore;

import java.util.*;

public class Logging {
    private Deque<Operation> log;

    public Logging() {
        log = new ArrayDeque<>();
    }

    public void saveLog(Operation task) {
        log.add(task);
    }

    public Deque<Operation> getLog() {
        return log;
    }

    @Override
    public String toString() {
        return "Logging{" +
                "log=" + log +
                '}';
    }
}
