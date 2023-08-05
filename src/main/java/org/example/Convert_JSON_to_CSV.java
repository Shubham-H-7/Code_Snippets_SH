package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Convert_JSON_to_CSV {

        public static void main(String[] args) {

            // Replace "your_input_file.json" with the path to your JSON file
            String jsonFilePath = "/Users/shubham/Downloads/JSON_Array_of_values.json";

            // Replace "output.csv" with the desired path for the CSV output file
            String csvOutputFilePath = "/Users/shubham/Documents/CSv_JSON/csv_json1.csv";

            try {
                convertJsonToCsv(jsonFilePath, csvOutputFilePath);
                System.out.println("JSON to CSV conversion successful.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private static void convertJsonToCsv(String jsonFilePath, String csvOutputFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Read the JSON file
        JsonNode root = objectMapper.readTree(new File(jsonFilePath));

        // Write the CSV content
        File csvOutputFile = new File(csvOutputFilePath);
        FileUtils.writeStringToFile(csvOutputFile, "", "UTF-8", false); // Clear the file if it exists

        // Process JSON and write to CSV
        writeJsonToCsv(root, csvOutputFile);
    }

    private static void writeJsonToCsv(JsonNode node, File csvOutputFile) throws IOException {
        if (node.isArray()) {
            // Array of objects, process each element
            for (JsonNode element : node) {
                writeJsonToCsv(element, csvOutputFile);
            }
        } else if (node.isObject()) {
            // Object, write a CSV record
            StringBuilder csvLine = new StringBuilder();
            Iterator<String> fieldNames = node.fieldNames();

            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldValue = node.get(fieldName);

                if (csvLine.length() > 0) {
                    csvLine.append(",");
                }

                if (fieldValue.isArray()) {
                    // Handle arrays by converting them to comma-separated strings
                    ArrayNode arrayNode = (ArrayNode) fieldValue;
                    String arrayAsString = arrayNode.toString().replaceAll("[\\[\\]\"]", "");
                    csvLine.append("\"").append(arrayAsString).append("\"");
                } else if (fieldValue.isObject()) {
                    // Handle nested objects recursively
                    writeJsonToCsv(fieldValue, csvOutputFile);
                } else {
                    // Add the field value as a regular CSV field
                    csvLine.append("\"").append(fieldValue.asText()).append("\"");
                }
            }

            csvLine.append("\n");
            FileUtils.writeStringToFile(csvOutputFile, csvLine.toString(), "UTF-8", true);
        }
    }}


