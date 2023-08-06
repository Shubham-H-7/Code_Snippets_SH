package org.example;
import java.util.Random;
public class NamesGen {

        public static void main(String[] args) {
            int N = 75; // Change this value to generate names N times

            String[] europeanMaleNames = { // Add required names from which to be picked
                    "Andersen", "Bianchi", "Carlsen", "Dijkstra", "Eriksson", "Faber", "Gomes", "Hansen", "Ionescu", "Jansen",
                    "Kovalenko", "Lehmann", "Muller", "Novak", "Donnell", "Petrović", "Rossi", "Schmidt", "Toth", "Van der Berg",
                    /* Add more names here */
            };

            Random random = new Random();

            System.out.println("Generated Names:");

            for (int i = 0; i < N; i++) {
                int randomIndex = random.nextInt(europeanMaleNames.length);
                String name = europeanMaleNames[randomIndex];
                System.out.println(name);
            }
        }
    }


