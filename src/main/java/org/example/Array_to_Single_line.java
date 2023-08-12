package org.example;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Array_to_Single_line {

        public static void main(String[] args) {
            String inputFilePath = "/Users/shubham/Downloads/new_array_data.json"; // Replace witweh your input JSON file path
            String outputFilePath = "/Users/shubham/Downloads/final_array_data.json"; // Replace with your output JSON file path

            try {
                JSONArray jsonArray = readJSONArrayFromFile(inputFilePath);
                String formattedJSON = formatJSONArraySingleLine(jsonArray);
                writeFormattedJSONToFile(formattedJSON, outputFilePath);
                System.out.println("Conversion complete.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static JSONArray readJSONArrayFromFile(String filePath) throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return new JSONArray(stringBuilder.toString());
            }
        }

        private static String formatJSONArraySingleLine(JSONArray jsonArray) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                stringBuilder.append(jsonObject.toString());
                if (i < jsonArray.length() - 1) {
                    stringBuilder.append(",");
                }
            }
            return "[" + stringBuilder.toString() + "]";
        }

        private static void writeFormattedJSONToFile(String formattedJSON, String filePath) throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(formattedJSON);
            }
        }
    }


