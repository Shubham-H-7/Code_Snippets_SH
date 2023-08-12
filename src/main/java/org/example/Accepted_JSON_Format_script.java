package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Accepted_JSON_Format_script {
    public static void main(String[] args) {
        String inputFilePath = "/Users/shubham/Downloads/new_array_data.json";   // Replace with your input JSON file path
        String outputFilePath = "/Users/shubham/Downloads/final_array_data.json"; // Replace with your output JSON file path

        try {
            String jsonString = readJSONStringFromFile(inputFilePath);
            String singleLineJSON = jsonString.replaceAll("\\s+", "");
            writeJSONToFile(singleLineJSON, outputFilePath);
            System.out.println("Conversion complete.");
            formatJSON(outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readJSONStringFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            return jsonString.toString();
        }
    }

    private static void writeJSONToFile(String jsonContent, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonContent);
        }
    }

    private static void formatJSON(String outputFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(outputFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/shubham/Downloads/Array_data_new.json"));
            String line;
            try {
                line = reader.readLine();
                while (line != null) {
                    String[] objects = line.split("(?<=\\})|(?=\\{)");
                    for (String object : objects) {
                        writer.write(object.trim());
                        writer.newLine();
                    }
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            reader.close();
            writer.close();
            System.out.println("JSON objects separated and written to output.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

