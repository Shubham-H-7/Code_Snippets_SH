package Array_Data;

import java.io.*;

public class Accepted_JSON_Format_script {
    public static void main(String[] args) {
        String inputFilePath = "/Users/shubham/Downloads/Array_Gen_Data_File.json";   // Replace with your input JSON file path
        String outputFilePath = "/Users/shubham/Downloads/Single_line_convert.json"; // Replace with your output JSON file path

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
        StringBuilder jsonString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            String rawJson = jsonString.toString().trim();

            if (rawJson.startsWith("[") && rawJson.endsWith("]")) {
                rawJson = rawJson.substring(1, rawJson.length() - 1);
            }

            rawJson = rawJson.replace("},", "}");

            return rawJson;
        }
    }

    private static void writeJSONToFile(String jsonContent, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonContent);
        }
    }

    private static void formatJSON(String outputFilePath) {
        String []  objects;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(outputFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/shubham/Downloads/Final_Array_Data.json"));

            String line;
            try {
                line = reader.readLine();
                while (line != null) {
                   objects = line.split("(?<=\\})|(?=\\{)");
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

