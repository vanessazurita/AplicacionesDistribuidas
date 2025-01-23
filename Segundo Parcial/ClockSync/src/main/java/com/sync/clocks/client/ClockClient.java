package com.sync.clocks.client;

import com.sync.clocks.utils.TimeUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.time.Instant;

public class ClockClient {
    public static void requestTime(String serverAddress, int port) {
        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             OutputStream out = socket.getOutputStream()) {

            // Record the time request was sent
            long requestTime = Instant.now().toEpochMilli();

            // Read the server's response
            String response = in.readLine();
            long responseTime = Instant.now().toEpochMilli();

            // Calculate network latency and adjusted time
            long roundTripTime = responseTime - requestTime;
            System.out.println("Server Time (formatted): " + response);
            System.out.println("Round Trip Time: " + roundTripTime + " ms");

            // Display current local time for comparison
            System.out.println("Local Time: " + TimeUtils.formatTime(Instant.now().toEpochMilli()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
