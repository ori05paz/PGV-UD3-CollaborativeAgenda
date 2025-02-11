package net.salesianos.client;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Listens for incoming messages from the server.
 * Runs on a separate thread to ensure non-blocking communication.
 */
public class ClientListener extends Thread {
    private BufferedReader in;

    /**
     * Constructor for ClientListener.
     * 
     * @param in The BufferedReader used to receive messages from the server.
     */
    public ClientListener(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Servidor dice: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}