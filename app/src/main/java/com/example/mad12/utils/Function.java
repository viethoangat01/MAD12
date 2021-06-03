package com.example.mad12.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Function {
    //Convert timestamp to date
    public static String getDateFromTimestamp(String timestamp) {
        Date date = new Date(Long.parseLong(timestamp) * 1000L);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        String formatted = format.format(date);
        return formatted;
    }
}
