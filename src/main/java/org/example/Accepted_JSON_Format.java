package org.example;

import java.io.*;

public class Accepted_JSON_Format {
    private static final Object JSON = null;

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/shubham/Downloads/one_line.json"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/shubham/Downloads/Array_line4.json"));
            String line;
            try {
                line = reader.readLine();
                while (line != null) {
                    String[] objects = line.split("(?<=\\})&&(?<=\\,)|(?=\\{)");
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
