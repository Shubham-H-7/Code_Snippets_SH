package org.example;

import java.io.*;

public class Accepted_JSON_Format {
    private static final Object JSON = null ;

    // This code helps to convert single line JSON formatted code from previous snippets to Collect accepted json format
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/shubham/Downloads/final_array_data.json"));
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
