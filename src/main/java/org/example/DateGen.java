package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGen {
    public static void main(String[] args) throws ParseException {
        String startDateStr = "01/01/1996"; // Start date in dd/MM/yyyy format
        String endDateStr = "01/11/1997";   // End date in dd/MM/yyyy format
        int N = 38; // Number of random dates to generate

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = sdf.parse(startDateStr);
        Date endDate = sdf.parse(endDateStr);

        long startTimeMillis = startDate.getTime();
        long endTimeMillis = endDate.getTime();

        System.out.println("Generated Random Dates:");
        for (int i = 0; i < N; i++) {
            long randomTimeMillis = startTimeMillis + (long) (Math.random() * (endTimeMillis - startTimeMillis));
            Date randomDate = new Date(randomTimeMillis);

            String formattedRandomDate = sdf.format(randomDate);
            System.out.println(formattedRandomDate);
        }
    }
}


