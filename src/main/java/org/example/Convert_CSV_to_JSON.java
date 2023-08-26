package org.example;

import org.json.*;

import java.io.*;

public class Convert_CSV_to_JSON {

        public static void main(String[] args) {
            String csvFilePath = "/Users/shubham/Downloads/300_Rec.csv";     // Replace with your CSV file path
            String jsonFilePath = "/Users/shubham/Downloads/csv_to_json.json";  // Replace with your desired JSON file path

            try {
                JSONArray jsonArray = convertCsvToJson(csvFilePath);
                writeJsonToFile(jsonArray, jsonFilePath);
                formatJsonFile(jsonFilePath);
                System.out.println("CSV to JSON conversion and formatting completed successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static JSONArray convertCsvToJson(String csvFilePath) throws IOException {
            JSONArray jsonArray = new JSONArray();
            BufferedReader csvReader = new BufferedReader(new FileReader(csvFilePath));

            String line;
            String[] headers = csvReader.readLine().split(",");
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < headers.length; i++) {
                    jsonObject.put(headers[i], data[i]);
                }
                jsonArray.put(jsonObject);
            }
            csvReader.close();

            return jsonArray;
        }
        public static void writeJsonToFile(JSONArray jsonArray, String jsonFilePath) throws IOException {
            try (Writer writer = new FileWriter(jsonFilePath)) {
                jsonArray.write(writer, 4, 0);
            }
        }
        public static void formatJsonFile(String jsonFilePath) throws IOException {
            StringBuilder formattedJson = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                formattedJson.append(line.trim());
            }
            reader.close();

            String finalJson = formattedJson.toString()
                    .replace("[{", "{")
                    .replace("},", "}\n")
                    .replace("}]", "}");

            try (Writer writer = new FileWriter(jsonFilePath)) {
                writer.write(finalJson);
            }
        }
}
