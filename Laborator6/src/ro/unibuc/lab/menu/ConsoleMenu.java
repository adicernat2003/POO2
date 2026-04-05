package ro.unibuc.lab.menu;

import ro.unibuc.lab.model.Student;
import ro.unibuc.lab.service.BinaryDataService;
import ro.unibuc.lab.service.ClassicIOService;
import ro.unibuc.lab.service.Nio2Service;
import ro.unibuc.lab.service.RandomAccessService;
import ro.unibuc.lab.util.InputHelper;

public class ConsoleMenu {

    private final ClassicIOService classicIOService = new ClassicIOService();
    private final Nio2Service nio2Service = new Nio2Service();
    private final BinaryDataService binaryDataService = new BinaryDataService();
    private final RandomAccessService randomAccessService = new RandomAccessService();

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int option = InputHelper.readInt("Alege optiunea: ");

            switch (option) {
                case 1 -> classicIOService.writeTextToFile(                 // fisier_text.txt
                        InputHelper.readLine("Fisier: "),
                        InputHelper.readLine("Text: "),
                        true
                );
                case 2 -> classicIOService.readTextCharacterByCharacter(
                        InputHelper.readLine("Fisier: ")
                );
                case 3 -> classicIOService.copyTextFile(
                        InputHelper.readLine("Sursa: "),
                        InputHelper.readLine("Destinatie: ")
                );
                case 4 -> classicIOService.copyBinaryFile(                  //imagine.png
                        InputHelper.readLine("Sursa binara: "),
                        InputHelper.readLine("Destinatie binara: ")
                );
                case 5 -> classicIOService.bufferedReadLines(               //buffer_text.txt
                        InputHelper.readLine("Fisier: ")
                );
                case 6 -> classicIOService.countTextStats(
                        InputHelper.readLine("Fisier: ")
                );
                case 7 -> classicIOService.fileInfo(                        //imagine.png
                        InputHelper.readLine("Fisier/Director: ")
                );
                case 8 -> nio2Service.createDirectoryIfMissing(
                        InputHelper.readLine("Director: ")
                );
                case 9 -> nio2Service.createFileIfMissing(
                        InputHelper.readLine("Fisier: ")
                );
                case 10 -> nio2Service.writeString(
                        InputHelper.readLine("Fisier: "),
                        InputHelper.readLine("Text: ")
                );
                case 11 -> nio2Service.appendString(
                        InputHelper.readLine("Fisier: "),
                        InputHelper.readLine("Text de adaugat: ")
                );
                case 12 -> nio2Service.readString(
                        InputHelper.readLine("Fisier: ")
                );
                case 13 -> nio2Service.readAllLines(
                        InputHelper.readLine("Fisier: ")
                );
                case 14 -> nio2Service.copyFile(
                        InputHelper.readLine("Sursa: "),
                        InputHelper.readLine("Destinatie: ")
                );
                case 15 -> nio2Service.moveFile(
                        InputHelper.readLine("Sursa: "),
                        InputHelper.readLine("Destinatie noua: ")
                );
                case 16 -> nio2Service.showPathDetails(
                        InputHelper.readLine("Path: ")
                );
                case 17 -> nio2Service.showFileDetails(
                        InputHelper.readLine("Fisier/Director: ")
                );
                case 18 -> nio2Service.listDirectory(
                        InputHelper.readLine("Director: ")
                );
                case 19 -> nio2Service.walkDirectory(
                        InputHelper.readLine("Director: ")
                );
                case 20 -> nio2Service.deleteIfExists(
                        InputHelper.readLine("Fisier/Director de sters: ")
                );
                case 21 -> saveStudentsFlow();  // studenti.bin
                case 22 -> binaryDataService.printStudentsFromBinary(
                        InputHelper.readLine("Fisier binar studenti: ")
                );
                case 23 -> randomAccessService.createFixedLengthFile(   // raf.bin
                        InputHelper.readLine("Fisier RAF: ")
                );
                case 24 -> randomAccessService.readAllRecords(
                        InputHelper.readLine("Fisier RAF: ")
                );
                case 25 -> randomAccessService.updateGradeByRecordIndex(
                        InputHelper.readLine("Fisier RAF: "),
                        InputHelper.readInt("Index record: "),
                        InputHelper.readDouble("Noua valoare: ")
                );
                case 26 -> randomAccessService.showPointerDemo(
                        InputHelper.readLine("Fisier RAF: ")
                );
                case 0 -> {
                    running = false;
                    System.out.println("Aplicatia s-a inchis.");
                }
                default -> System.out.println("Optiune invalida.");
            }

            System.out.println();
        }
    }

    private void saveStudentsFlow() {
        String fileName = InputHelper.readLine("Fisier binar studenti: ");
        int n = InputHelper.readInt("Numar studenti: ");

        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1));
            String nume = InputHelper.readLine("Nume: ");
            int grupa = InputHelper.readInt("Grupa: ");
            double medie = InputHelper.readDouble("Medie: ");

            students[i] = new Student(nume, grupa, medie);
        }

        binaryDataService.saveStudents(fileName, students);
    }

    private void printMenu() {
        System.out.println("========= FILE PROCESSING TOOLKIT =========");
        System.out.println("1. Scrie text in fisier (FileWriter)");
        System.out.println("2. Citeste text caracter cu caracter (FileReader)");
        System.out.println("3. Copiaza fisier text");
        System.out.println("4. Copiaza fisier binar");
        System.out.println("5. Citeste linii cu BufferedReader");
        System.out.println("6. Statistici fisier text");
        System.out.println("7. Afiseaza detalii fisier/director");
        System.out.println("8. Creeaza director (NIO.2)");
        System.out.println("9. Creeaza fisier (NIO.2)");
        System.out.println("10. Scrie text cu Files.writeString");
        System.out.println("11. Adauga text cu Files.writeString + APPEND");
        System.out.println("12. Citeste continut cu Files.readString");
        System.out.println("13. Citeste toate liniile cu Files.readAllLines");
        System.out.println("14. Copiaza fisier cu Files.copy");
        System.out.println("15. Muta/redenumeste fisier cu Files.move");
        System.out.println("16. Afiseaza detalii Path");
        System.out.println("17. Afiseaza detalii fisier/director");
        System.out.println("18. Listeaza director cu Files.list");
        System.out.println("19. Parcurge recursiv cu Files.walk");
        System.out.println("20. Sterge fisier/director cu Files.deleteIfExists");
        System.out.println("21. Salveaza studenti in format binar");
        System.out.println("22. Citeste studenti din format binar");
        System.out.println("23. Creeaza fisier RandomAccessFile");
        System.out.println("24. Citeste toate inregistrarile RAF");
        System.out.println("25. Actualizeaza o valoare la un offset fix");
        System.out.println("26. Demonstreaza pointer-ul din RAF");
        System.out.println("0. Iesire");
    }
}