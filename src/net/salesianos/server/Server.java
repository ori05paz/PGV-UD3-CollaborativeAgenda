package net.salesianos.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * The Server class manages multiple client connections.
 * It runs on a specific port and allows real-time communication between
 * clients.
 */
public class Server {
    private static final int PORT = 8082;
    private static final List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler client = new ClientHandler(socket, clients);
                clients.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}