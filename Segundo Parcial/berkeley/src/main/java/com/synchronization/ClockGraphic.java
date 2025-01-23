package com.synchronization;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class ClockGraphic extends JPanel {
    private long timeMillis;

    public ClockGraphic(long timeMillis) {
        this.timeMillis = timeMillis;
        setPreferredSize(new Dimension(100, 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Configurar el reloj
        int radius = Math.min(getWidth(), getHeight()) / 2 - 10;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Dibujar el contorno del reloj
        g.setColor(Color.BLACK);
        g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // Convertir el tiempo en horas, minutos y segundos
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        int hours = calendar.get(Calendar.HOUR) % 12;
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        // Dibujar las manecillas del reloj
        drawHand(g, centerX, centerY, radius * 0.6, hours * 30 + minutes / 2, Color.BLUE);
        drawHand(g, centerX, centerY, radius * 0.8, minutes * 6, Color.GREEN);
        drawHand(g, centerX, centerY, radius * 0.9, seconds * 6, Color.RED);
    }

    private void drawHand(Graphics g, int x, int y, double length, double angle, Color color) {
        angle = Math.toRadians(angle - 90);
        int endX = (int) (x + length * Math.cos(angle));
        int endY = (int) (y + length * Math.sin(angle));
        g.setColor(color);
        g.drawLine(x, y, endX, endY);
    }
}
