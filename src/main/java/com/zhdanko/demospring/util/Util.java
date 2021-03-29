package com.zhdanko.demospring.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static Date convertToDateTime(String dateString) {
        Date result = null;
        String date = dateString.replace("T", " ");
        try {
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse((date));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public static double getTotalCost(Date start, Date finish, double costMinute) {
        double cost = (finish.getTime() - start.getTime()) / 60000 * costMinute;

        return roundAvoid(cost, 2);
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
