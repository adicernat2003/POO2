package ro.unibuc.lab.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileConfigLoader {

    public void loadConfigWithFinally(String filePath) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            System.out.println("Prima linie din configuratie: " + line);
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului: " + e.getMessage());
        } finally {
            System.out.println("finally se executa intotdeauna.");

            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("Resursa a fost inchisa.");
                } catch (IOException e) {
                    System.out.println("Eroare la inchiderea resursei: " + e.getMessage());
                }
            }
        }
    }

    public void loadConfigAndRethrow(String filePath) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            System.out.println(reader.readLine());
            reader.close();
        } catch (IOException e) {
            System.out.println("Exceptia este prinsa local si apoi re-aruncata.");
            throw e;
        } finally {
            System.out.println("finally din loadConfigAndRethrow s-a executat.");
        }
    }

    public void loadWithTryResources(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("Citire cu try-with-resources: " + reader.readLine());
        }
    }
}