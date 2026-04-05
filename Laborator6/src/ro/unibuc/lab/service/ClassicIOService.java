package ro.unibuc.lab.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ClassicIOService {

    public void writeTextToFile(String fileName, String content, boolean append) {
        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(content);
            writer.write(System.lineSeparator());
            System.out.println("Text scris cu succes in fisier.");
        } catch (IOException e) {
            System.out.println("Eroare la scriere: " + e.getMessage());
        }
    }

    public void readTextCharacterByCharacter(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Eroare la citire: " + e.getMessage());
        }
    }

    public void copyTextFile(String source, String destination) {
        try (FileReader reader = new FileReader(source);
             FileWriter writer = new FileWriter(destination)) {

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }

            System.out.println("Fisier text copiat.");
        } catch (IOException e) {
            System.out.println("Eroare la copiere text: " + e.getMessage());
        }
    }

    public void copyBinaryFile(String source, String destination) {
        try (FileInputStream in = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(destination)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            System.out.println("Fisier binar copiat.");
        } catch (IOException e) {
            System.out.println("Eroare la copiere binara: " + e.getMessage());
        }
    }

    public void bufferedReadLines(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNo = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(lineNo + ": " + line);
                lineNo++;
            }
        } catch (IOException e) {
            System.out.println("Eroare la citire bufferizata: " + e.getMessage());
        }
    }

    public void fileInfo(String fileName) {
        File file = new File(fileName);

        System.out.println("Exista: " + file.exists());
        System.out.println("Nume: " + file.getName());
        System.out.println("Cale absoluta: " + file.getAbsolutePath());
        System.out.println("Este fisier: " + file.isFile());
        System.out.println("Este director: " + file.isDirectory());
        System.out.println("Dimensiune: " + file.length() + " bytes");
    }

    public void countTextStats(String fileName) {
        int lines = 0;
        int words = 0;
        int chars = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                chars += line.length();

                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    words += trimmed.split("\\s+").length;
                }
            }

            System.out.println("Linii: " + lines);
            System.out.println("Cuvinte: " + words);
            System.out.println("Caractere fara newline: " + chars);

        } catch (IOException e) {
            System.out.println("Eroare la statistici text: " + e.getMessage());
        }
    }
}