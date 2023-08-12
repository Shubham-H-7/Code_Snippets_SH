package org.example;

import java.util.Random;

public class DigitsGen {
    public static void main(String[] args) {

        int N = 150; // Change this value to generate N random numbers

        int numberOfDigits = 10; // Change this value to the no of digits you want your number should have
        long minNumber = (long) Math.pow(10, numberOfDigits - 1);
        long maxNumber = (long) Math.pow(10, numberOfDigits) - 1;

        Random random = new Random();

        System.out.println("Generated Random Numbers:");

        for (int i = 0; i < N; i++) {
            long randomNumber = minNumber + Math.abs(random.nextLong()) % (maxNumber - minNumber);
            System.out.println(randomNumber);
        }
    }
}


