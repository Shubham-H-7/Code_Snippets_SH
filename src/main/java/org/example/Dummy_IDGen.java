package org.example;

import java.util.Random;

public class Dummy_IDGen {
    public static void main(String[] args) {
        int N = 150; // Set N to the desired number of repetitions

        for (int i = 0; i < N; i++) {
            String randomString = generateRandomString();
            System.out.println(randomString);
        }
    }

    private static String generateRandomString() {
        StringBuilder sb = new StringBuilder();

        // Define the groups of characters in the format
        String[] groups = {"xxxxxxxxxxxxxxx"};

        Random random = new Random();

        for (String group : groups) {
            for (int i = 0; i < group.length(); i++) {
                char randomChar;
                if (group.charAt(i) == 'x') {
                    // Append a random hexadecimal character (0-9, a-f)
                    randomChar = (char) (random.nextInt(6) + (random.nextBoolean() ? 'a' : '0'));
                } else {
                    // Append the hyphen
                    randomChar = '-';
                }
                sb.append(randomChar);
            }
        }

        return sb.toString();
    }
}


