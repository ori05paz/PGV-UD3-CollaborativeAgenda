package net.salesianos.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import net.salesianos.common.Event;
import net.salesianos.common.Protocol;

/**
 * Handles communication between the server and a connected client.
 * Manages authentication, message exchange, and event handling.
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private List<ClientHandler> clients;
    private User user;

    /**
     * Constructor for ClientHandler.
     * 
     * @param socket The socket associated with the connected client.
     * @param clients List of all clients connected to the server.
     */
    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Ingrese usuario:");
            String username = in.readLine();
            out.println("Ingrese contraseña:");
            String password = in.readLine();
            user = AuthManager.authenticate(username, password);

            if (user == null) {
                out.println("Error: Credenciales incorrectas.");
                socket.close();
                return;
            }
            out.println("Usuario autenticado con éxito.");

            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + input);
                if (input.startsWith(Protocol.ADD_EVENT)) {
                    String eventData = input.substring(Protocol.ADD_EVENT.length()).trim();
                    Event event = new Event(EventManager.generateEventId(), eventData);
                    EventManager.addEvent(event);
                    System.out.println("Nuevo evento agregado: " + event.getName());

                    NotificationManager.scheduleNotification(event, clients);

                    broadcast(Protocol.EVENT_UPDATE + " Evento agregado: " + event.getName());
                } else if (input.startsWith(Protocol.REMOVE_EVENT)) {
                    int id = Integer.parseInt(input.split(" ")[1]);
                    Event removedEvent = EventManager.getEvent(id); 

                    if (removedEvent != null && EventManager.removeEvent(id)) {
                        String message = "Evento eliminado '" + removedEvent.getName() + "' con id " + id;
                        System.out.println(message);
                        broadcast(Protocol.EVENT_UPDATE + " " + message);
                    } else {
                        out.println("Error: Evento no encontrado.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a message to all connected clients.
     * 
     * @param message The message to be broadcasted.
     */
    private void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

     /**
     * Sends a message to a specific client.
     * 
     * @param message The message to send.
     */
    public void sendMessage(String message) {
        out.println(message);
    }
}