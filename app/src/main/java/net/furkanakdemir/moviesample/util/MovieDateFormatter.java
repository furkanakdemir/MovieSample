package net.furkanakdemir.moviesample.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

public class MovieDateFormatter implements DateFormatter {

    @Inject
    public MovieDateFormatter() {
    }

    public String formatDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
            SimpleDateFormat format2 = new SimpleDateFormat("MMMM yyyy");

            return format2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";


    }
}