package net.salesianos.server;

import net.salesianos.common.Event;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Handles the scheduling and broadcasting of event notifications.
 */
public class NotificationManager {

    /**
     * Schedules a notification for an event.
     * 
     * @param event   The event to notify users about.
     * @param clients The list of connected clients to send notifications to.
     */
    public static void scheduleNotification(Event event, List<ClientHandler> clients) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String message = "Notificación: ¡El evento '" + event.getName() + "' está próximo!";
                System.out.println(message);

                for (ClientHandler client : clients) {
                    client.sendMessage(message);
                }
            }
        }, event.getTimeBeforeStart());
    }
}