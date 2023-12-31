package org.example;
import  java.util.Random;

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

                    // Define the groups of characters in the format, you have any type here xx-xxx-xxx, x-xx-xxx-xx
                    String[] groups = {"xxx-xxx-xxxxxxxxx"};

                    Random random = new Random();

                    for (String group : groups) {
                        for (int i = 0; i < group.length(); i++) {
                            char randomChar;
                            if (group.charAt(i) == 'x') {
                                // Append a random hexadecimal character (0-9, a-f), you can change this according to the requirement
                                randomChar = (char) (random.nextInt(6) + (random.nextBoolean() ? 'a' : '0'));
                            } else {
                                // Append the hyphen, you can change this according to the requirement [@,/,|,ect]
                                randomChar = '-';
                            }
                            sb.append(randomChar);
                        }
                    }

                    return sb.toString();
                }
            }


