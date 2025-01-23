package com.sync.clocks;

import com.sync.clocks.client.ClockClient;
import com.sync.clocks.server.ClockServer;

public class MainApp {
    public static void main(String[] args) {
        // Start the server in a separate thread
        new Thread(() -> ClockServer.start(12345)).start();

        // Start the client
        try {
            Thread.sleep(1000); // Ensure server starts first
            ClockClient.requestTime("localhost", 12345);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
