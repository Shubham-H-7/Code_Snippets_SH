package org.example;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Array_Data_Gen {
    static  int i;
        public static void main(String[] args) {

            JSONArray jsonArray = new JSONArray();

            List<String> adIds = readAdIdsFromCSV("/Users/shubham/Downloads/ADID_Csv.csv");
            if (adIds.isEmpty()) {
                System.err.println("No AdIds found in the CSV file.");
                return;
            }
            for (i = 1; i < 200; i++)
            {
                JSONObject jsonObject = createSampleJsonObject(adIds);
                jsonArray.put(jsonObject);
            }

            System.out.println(jsonArray.toString(4)); // Printing JSON with indentation of 4 spaces
        }

        private static JSONObject createSampleJsonObject(List<String> adIds) {
            JSONObject jsonObject = new JSONObject();

            // Generate random AdId from the list
            //String adId = getRandomAdId(adIds);
            int j;
            for (j=0; j<i; j++) {
                String ad = adIds.get(j);
                //System.out.println(ad);

                jsonObject.put("name", ad);
            }
                // Generate random age between 20 and 30 (inclusive)
                int age = generateRandomAge(20, 30);
                jsonObject.put("age", age);
                jsonObject.put("isConsent", getRandomBoolean());

                // Add an array of values (Transcations)
                JSONArray transcations = new JSONArray();
                transcations.put(5000.55);
                transcations.put(10000.00);
                transcations.put(20000);
                transcations.put(17000);
                jsonObject.put("Transcations", transcations);

                // Add an array of values (Banks)
                JSONArray banks = new JSONArray();
                banks.put("HDFC");
                banks.put("ICICI");
                banks.put("SBI");
                banks.put("Axis");
                jsonObject.put("Banks", banks);

                // Add an array of values (Credit)
                JSONArray credit = new JSONArray();
                credit.put(25000);
                credit.put(35000);
                credit.put(50000);
                credit.put(65000);
                credit.put(75000);
                jsonObject.put("credited", credit);


            return jsonObject;
        }

        private static int generateRandomAge(int min, int max) {
            Random random = new Random();
            return random.nextInt(max - min + 1) + min;
        }

        private static String getRandomAdId(List<String> adIds) {
            Random random = new Random();
            return adIds.get(random.nextInt(adIds.size()));
        }

        private static boolean getRandomBoolean() {
            Random random = new Random();
            return random.nextBoolean();
        }

        private static List<String> readAdIdsFromCSV(String filePath) {
            List<String> adIds = new ArrayList<>();

            try {
                CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new FileReader(filePath));

                for (CSVRecord record : parser) {
                    String adId = record.get("AdId"); // Change "AdId" to the column header for AdIds
                    adIds.add(adId);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return adIds;
        }
    }




