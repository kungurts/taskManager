package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TodosTests {

    private Todos todos;
    Operation operation;


    @BeforeEach
    void setUp() {
        todos = new Todos();
         operation = new Operation(OperationType.ADD, "Погулять с собакой");
        todos.addTask(operation);
    }

    @Test
    @DisplayName("Тестирование добавления задачи")
    void getAllTasksAdd() {
        Assertions.assertEquals("погулять с собакой", todos.getAllTasks());
    }

    @Test
    @DisplayName("Тестирование удаления задачи")
    void getAllTasksRemove() {
        todos.removeTask(operation);
        Assertions.assertEquals("Задач нет", todos.getAllTasks());
    }

    @Test
    @DisplayName("Тестирование добавления более 7 задач и сортировки")
    void getAllTasksTooMuch() {
        todos.addTask(new Operation(OperationType.ADD, "7"));
        todos.addTask(new Operation(OperationType.ADD, "5"));
        todos.addTask(new Operation(OperationType.ADD, "3"));
        todos.addTask(new Operation(OperationType.ADD, "1"));
        todos.addTask(new Operation(OperationType.ADD, "2"));
        todos.addTask(new Operation(OperationType.ADD, "4"));
        todos.addTask(new Operation(OperationType.ADD, "6"));

        String expectedResult = "1;2;3;4;5;7;погулять с собакой";
        Assertions.assertEquals(expectedResult, todos.getAllTasks());
    }

    @Test
    @DisplayName("Тестирование команды RESTOE")
    void getAllTasksRestore() {
        todos.removeTask(operation);
        todos.addTask(new Operation(OperationType.ADD, "1"));
        todos.addTask(new Operation(OperationType.ADD, "2"));
        todos.removeTask(new Operation(OperationType.REMOVE, "1"));
        todos.addTask(new Operation(OperationType.ADD, "3"));
        todos.restore();
        todos.restore();
        String expectedResult = "1;2";
        Assertions.assertEquals(expectedResult, todos.getAllTasks());
    }
}
