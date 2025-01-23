package com.synchronization;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ClockNode {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
            .withZone(ZoneId.systemDefault());

    private int id;
    private long offset; // Diferencia con el tiempo real en milisegundos

    public ClockNode(int id, long offset) {
        this.id = id;
        this.offset = offset;
    }

    public long getAdjustedTime() {
        return System.currentTimeMillis() + offset;
    }

    public String getFormattedTime() {
        return TIME_FORMATTER.format(Instant.ofEpochMilli(getAdjustedTime()));
    }

    public int getId() {
        return id;
    }

    public void adjustOffset(long adjustment) {
        this.offset += adjustment;
    }

    /**
     * Ajusta el offset para establecer un nuevo tiempo ajustado.
     *
     * @param newTime El nuevo tiempo ajustado en milisegundos desde la época.
     */
    public void setAdjustedTime(long newTime) {
        this.offset = newTime - System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Node " + id + " Time: " + getFormattedTime();
    }
}
