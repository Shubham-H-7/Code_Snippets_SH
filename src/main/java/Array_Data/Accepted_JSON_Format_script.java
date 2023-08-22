package Array_Data;

import java.io.*;
import java.util.Properties;

public class Accepted_JSON_Format_script {
    static String filepath1;
    static String filepath3;
    static  String filepath2;
    static {
        loadConfig();
    }
    private static void loadConfig(){
        Properties properties= new Properties();
        try(FileInputStream input= new FileInputStream("/Users/shubham/IdeaProjects/Code_Snippets_SH/src/main/java/Array_Data/config.properties")) {
            properties.load(input);
            filepath1 = properties.getProperty("filepath1");
            filepath2 = properties.getProperty("filepath2");
            filepath3 = properties.getProperty("filepath3");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
       finalMethod();
    }
    static  void finalMethod(){
        String inputFilePath = filepath1;   // Replace with your input JSON file path
        String outputFilePath = filepath2; // Replace with your output JSON file path

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

    private static String readJSONStringFromFile(String filepath2) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath2))) {
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath3));

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

