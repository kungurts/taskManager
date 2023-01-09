package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;
import java.util.Scanner;

public class TodoServer {
    private int port;
    private Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        ) {
                    out.println(todos.getAllTasks());
                    String inputJson = in.readLine();
                    Operation request = gson.fromJson(inputJson, Operation.class);
                    switch (request.toDo) {
                        case ADD:
                            todos.addTask(request);
                            out.println(todos.getAllTasks());
                            break;
                        case REMOVE:
                            todos.removeTask(request);
                            out.println(todos.getAllTasks());
                            break;
                        case RESTORE:
                            todos.restore();
                            out.println(todos.getAllTasks());
                            break;
                    }
                }
            }
        }
    }
}
