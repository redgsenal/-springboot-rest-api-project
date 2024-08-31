package com.exam.project.reservation.utility;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtils {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:00.000";

    public static LocalDateTime extractJSONDateValue(JSONObject jsonData, String key) throws RuntimeException{
        if (jsonData == null || !jsonData.has(key)) {
            throw new RuntimeException("Invalid timeslot");
        }
        DateTimeFormatter formatter = createDateFormatter();
        return LocalDateTime.parse(jsonData.getString("timeSlot"), formatter);
    }

    public static int extractJSONIntegerValue(JSONObject jsonData, String key) throws RuntimeException {
        if (jsonData == null || !jsonData.has(key)) {
            return 0;
        }
        return jsonData.getInt(key);
    }

    public static String extractJSONStringValue(JSONObject jsonData, String key) throws RuntimeException {
        if (jsonData == null || !jsonData.has(key)) {
            throw new RuntimeException("Invalid key");
        }
        return jsonData.getString(key);
    }

    public static boolean extractJSONBooleanValue(JSONObject jsonData, String key) throws RuntimeException {
        if (jsonData == null || !jsonData.has(key)) {
            throw new RuntimeException("Invalid key");
        }
        return jsonData.getBoolean(key);
    }

    public static DateTimeFormatter createDateFormatter(){
        return DateTimeFormatter.ofPattern(DATE_PATTERN);
    }
}
