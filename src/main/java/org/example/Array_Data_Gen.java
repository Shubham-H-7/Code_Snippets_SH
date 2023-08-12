package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Array_Data_Gen {

    // This code helps to generate array data type data for testing
    static int i;

    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();

        //Here please copy and paste the IDs file path from which you want IDs to be fetched and be part of Array data
        List<String> adIds = readAdIdsFromCSV("/Users/shubham/Downloads/1000_AdId_IDs.csv");
        if (adIds.isEmpty()) {
            System.err.println("No AdIds found in the CSV file.");
            return;
        }
        for (i = 1; i < 100; i++) {
            JSONObject jsonObject = createSampleJsonObject(adIds);
            jsonArray.put(jsonObject);
        }

        System.out.println(jsonArray.toString(4)); // Printing JSON with indentation of 4 spaces
    }

    private static JSONObject createSampleJsonObject(List<String> adIds) {
        JSONObject jsonObject = new JSONObject();

        int j;
        for (j = 0; j < i; j++) {
            String adId = adIds.get(j);
            jsonObject.put("ID", adId);
        }
        // Generate random age between 20 and 30 (inclusive)
        int age = generateRandomAge(30, 35);
        jsonObject.put("age", age);
        jsonObject.put("isConsent", generateRandomYesNo());
        jsonObject.put("isMarketingPreference", generateRandomYesNo());

        // Add an array of values (Transcations) [Data Type - Double]
        JSONArray transcations = new JSONArray();
        transcations.put(95000.00);
        transcations.put(97000.01);
        transcations.put(99000.90);
        transcations.put(93000.45);
        transcations.put(94000);
        jsonObject.put("Transcations", transcations);

        // Add an array of values (Banks) [Data Type - String]
        JSONArray banks = new JSONArray();
        banks.put("JPMorgan");
        banks.put("BAC");
        banks.put("Wells Fargo");
        jsonObject.put("Banks", banks);

        // Add an array of values (Credit) [Data Type - Integer]
        JSONArray credit = new JSONArray();
        credit.put(9000);
        credit.put(9500);
        credit.put(9999);
        credit.put(94500);

        jsonObject.put("credited", credit);

        long timestamp = generateRandomTimestamp(1688196888, 1690702488); // Example date range: 11 August 2023 to  21 August 2023
        jsonObject.put("timestamp", timestamp);

        return jsonObject;
    }

    private static int generateRandomAge(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private static String generateRandomYesNo() {
        String[] options = {"yes", "no"};
        Random random = new Random();
        int randomIndex = random.nextInt(options.length);
        return options[randomIndex];
    }

    private static long generateRandomTimestamp(long min, long max) {
        Random random = new Random();
        long randomTimestamp = random.nextLong() % (max - min) + min;
        return randomTimestamp;
    }

    private static List<String> readAdIdsFromCSV(String filePath) {
        List<String> adIds = new ArrayList<>();

        try {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new FileReader(filePath));

            for (CSVRecord record : parser) {
                String adId = record.get("AdId"); // Change "AdId" to the column header for IDs
                adIds.add(adId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return adIds;
    }
}