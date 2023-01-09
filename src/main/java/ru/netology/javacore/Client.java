package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 8989;

        Scanner sc = new Scanner(System.in);
        OperationType whatToDo = null;
        String task;
        Operation request;

        try (
                Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            while (true) {
                System.out.println("Текущий список задач: " + in.readLine());
                System.out.println("Введи операцию ADD or REMOVE и текст задачи через двоеточие, либо RESTORE");
                String input = sc.nextLine();
                String[] parts = input.split(":");
                switch (parts[0]) {
                    case "ADD":
                        whatToDo = OperationType.ADD;
                        task = parts[1];
                        request = new Operation(whatToDo, task);
                        break;
                    case "REMOVE":
                        whatToDo = OperationType.REMOVE;
                        task = parts[1];
                        request = new Operation(whatToDo, task);
                        break;
                    case "RESTORE":
                        whatToDo = OperationType.RESTORE;
                        request = new Operation(whatToDo, null);
                        break;
                    default:
                        System.out.println("вы ошиблись в наименовании операции");
                        continue;
                }
                break;
            }

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String requestJson = gson.toJson(request);
            out.println(requestJson);
            System.out.println(in.readLine());
        }
    }
}
