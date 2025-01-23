package com.sync.clocks.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ClockServer {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            .withZone(ZoneId.systemDefault());

    public static void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("ClockServer started on port " + port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     OutputStream out = clientSocket.getOutputStream()) {
                    long currentTime = Instant.now().toEpochMilli();
                    String formattedTime = FORMATTER.format(Instant.ofEpochMilli(currentTime));
                    out.write((formattedTime + "\n").getBytes());
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
