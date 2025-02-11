package net.salesianos.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * The Client class connects to the server and allows interaction.
 * It can send commands to add or remove events.
 */
public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8082);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor. Usa 'ADD <evento>' o 'REMOVE <ID>'");

            new ClientListener(in).start();

            while (true) {
                String command = scanner.nextLine();
                out.println(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}