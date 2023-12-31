package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGen {
        // This code helps in generating between certain range of dates
        public static void main(String[] args) throws ParseException {
            String startDateStr = "16/08/2023"; // Start date in dd/MM/yyyy format
            String endDateStr = "30/08/2023";   // End date in dd/MM/yyyy format
            int N = 75; // Number of random dates to generate

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


