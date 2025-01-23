package com.sync.clocks.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            .withZone(ZoneId.systemDefault());

    public static String formatTime(long epochMilli) {
        return FORMATTER.format(Instant.ofEpochMilli(epochMilli));
    }

    public static long calculateLatency(long requestTime, long responseTime) {
        return responseTime - requestTime;
    }
}
