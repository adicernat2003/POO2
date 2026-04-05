package ro.unibuc.lab.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class Nio2Service {

    public void createDirectoryIfMissing(String dirName) {
        Path path = Paths.get(dirName);
        try {
            if (Files.notExists(path)) {
                Files.createDirectories(path);
                System.out.println("Director creat: " + path.toAbsolutePath());
            } else {
                System.out.println("Directorul exista deja: " + path.toAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Eroare la creare director: " + e.getMessage());
        }
    }

    public void createFileIfMissing(String fileName) {
        Path path = Paths.get(fileName);
        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
                System.out.println("Fisier creat: " + path.toAbsolutePath());
            } else {
                System.out.println("Fisierul exista deja.");
            }
        } catch (IOException e) {
            System.out.println("Eroare la creare fisier: " + e.getMessage());
        }
    }

    public void writeString(String fileName, String content) {  // Creează dacă nu există, altfel suprascrie complet
        Path path = Paths.get(fileName);
        try {
            Files.writeString(path, content, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Text scris cu Files.writeString()");
        } catch (IOException e) {
            System.out.println("Eroare writeString: " + e.getMessage());
        }
    }

    public void appendString(String fileName, String content) {  // Creează dacă nu există, altfel adaugă la final
        Path path = Paths.get(fileName);
        try {
            Files.writeString(path, content + System.lineSeparator(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            System.out.println("Text adaugat la final.");
        } catch (IOException e) {
            System.out.println("Eroare appendString: " + e.getMessage());
        }
    }

    public void readString(String fileName) {
        Path path = Paths.get(fileName);
        try {
            String content = Files.readString(path, StandardCharsets.UTF_8);
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Eroare readString: " + e.getMessage());
        }
    }

    public void readAllLines(String fileName) {
        Path path = Paths.get(fileName);
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (int i = 0; i < lines.size(); i++) {
                System.out.println((i + 1) + ": " + lines.get(i));
            }
        } catch (IOException e) {
            System.out.println("Eroare readAllLines: " + e.getMessage());
        }
    }

    public void copyFile(String source, String destination) {
        try {
            Files.copy(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Fisier copiat cu NIO.2");
        } catch (IOException e) {
            System.out.println("Eroare copy: " + e.getMessage());
        }
    }

    public void moveFile(String source, String destination) {
        try {
            Files.move(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Fisier mutat/redenumit.");
        } catch (IOException e) {
            System.out.println("Eroare move: " + e.getMessage());
        }
    }

    public void deleteIfExists(String fileName) {
        try {
            boolean deleted = Files.deleteIfExists(Paths.get(fileName));
            System.out.println("Sters: " + deleted);
        } catch (IOException e) {
            System.out.println("Eroare deleteIfExists: " + e.getMessage());
        }
    }

    public void showPathDetails(String fileName) {
        Path path = Paths.get(fileName);

        System.out.println("Path: " + path); // caile relative sunt identice pe toate OS-urile
        System.out.println("Absolute: " + path.toAbsolutePath());
        System.out.println("Normalized: " + path.normalize()); // "a/b/../c/./d/../file.txt" -> a/c/file.txt
        System.out.println("Filename: " + path.getFileName());
        System.out.println("Parent: " + path.getParent());
        System.out.println("Root: " + path.getRoot()); // pentru cai relative, root este null pentru ca nu e cale absoluta
        System.out.println("Is absolute: " + path.isAbsolute());
    }

    public void showFileDetails(String fileName) {
        Path path = Paths.get(fileName);
        try {
            System.out.println("Exista: " + Files.exists(path));
            System.out.println("Nu exista: " + Files.notExists(path));
            System.out.println("Este director: " + Files.isDirectory(path));
            System.out.println("Este fisier regulat: " + Files.isRegularFile(path)); // Un fișier regulat este: un fișier care conține date și nu este un director, link simbolic sau alt tip special de fișier.
            if (Files.exists(path) && !Files.isDirectory(path)) {
                System.out.println("Dimensiune: " + Files.size(path) + " bytes");
            }
        } catch (IOException e) {
            System.out.println("Eroare la detalii fisier: " + e.getMessage());
        }
    }

    public void listDirectory(String dirName) { // Listeaza fisierele/directoarele din interiorul directorului specificat, dar nu recursiv
        Path path = Paths.get(dirName);

        try (Stream<Path> stream = Files.list(path)) {
            stream.forEach(p -> System.out.println(p.getFileName()));
        } catch (IOException e) {
            System.out.println("Eroare list directory: " + e.getMessage());
        }
    }

    public void walkDirectory(String dirName) {
        Path path = Paths.get(dirName);

        try (Stream<Path> stream = Files.walk(path)) {
            stream.forEach(p -> System.out.println(p.toAbsolutePath()));
        } catch (IOException e) {
            System.out.println("Eroare walk directory: " + e.getMessage());
        }
    }
}