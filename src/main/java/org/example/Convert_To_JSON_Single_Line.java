package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Convert_To_JSON_Single_Line {
        public static void main(String[] args) {
            // Path to your JSON file
            String filePath = "/Users/shubham/Downloads/Sample_array_2.json";


            // Read the JSON data from the file
            String jsonData = null;
            try {
                jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            // Use ObjectMapper to read and write JSON
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                Object jsonObject = objectMapper.readValue(jsonData, Object.class);
                String singleLineJson = objectMapper.writeValueAsString(jsonObject);

                System.out.println(singleLineJson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }






