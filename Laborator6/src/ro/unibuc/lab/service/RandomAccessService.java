package ro.unibuc.lab.service;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessService {

    public void createFixedLengthFile(String fileName) {
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            raf.setLength(0);

            raf.writeInt(1001);
            raf.writeDouble(9.45);

            raf.writeInt(1002);
            raf.writeDouble(8.75);

            raf.writeInt(1003);
            raf.writeDouble(7.90);

            System.out.println("Fisier cu acces aleatoriu creat.");
        } catch (IOException e) {
            System.out.println("Eroare createFixedLengthFile: " + e.getMessage());
        }
    }

    public void readAllRecords(String fileName) {
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            long fileLength = raf.length(); // 3 * (4 + 8) = 36 bytes
            int recordSize = Integer.BYTES + Double.BYTES; // 4 + 8 = 12 bytes

            while (raf.getFilePointer() < fileLength) { // getFilePointer() = poziția curentă în fișier
                int id = raf.readInt(); // Ordinea trebuie să fie IDENTICĂ cu scrierea: întâi citim un int, apoi un double
                double value = raf.readDouble();
                System.out.println("ID=" + id + ", value=" + value);
            }   // pointer-ul avansează automat: după readInt() avansează cu 4, după readDouble() avansează cu 8

            System.out.println("Pointer final: " + raf.getFilePointer()); // pointer-ul ar trebui să fie egal cu fileLength la final
            System.out.println("Record size: " + recordSize); // dimensiunea unui record
            System.out.println("Numar total de inregistrari: " + (fileLength / recordSize));
        } catch (IOException e) {
            System.out.println("Eroare readAllRecords: " + e.getMessage());
        }
    }

    public void updateGradeByRecordIndex(String fileName, int index, double newValue) {
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            int recordSize = Integer.BYTES + Double.BYTES;
            long position = (long) index * recordSize + Integer.BYTES; // index de la 0 adunat cu 4 bytes ca sa sarim peste id si sa ajungem la value

            if (position + Double.BYTES > raf.length()) { // daca indexul este prea mare si depaseste lungimea fisierului, afisam mesaj de eroare
                System.out.println("Index invalid.");
                return;
            }

            raf.seek(position); // mutam pointer-ul la pozitia calculata (offset position masurat in bytes de la inceputul fisierului)
            raf.writeDouble(newValue); // suprascrie

            System.out.println("Valoare actualizata la index " + index);
        } catch (IOException e) {
            System.out.println("Eroare updateGradeByRecordIndex: " + e.getMessage());
        }
    }

    public void showPointerDemo(String fileName) {
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            System.out.println("Pointer initial: " + raf.getFilePointer());

            int id = raf.readInt();
            System.out.println("ID citit: " + id);
            System.out.println("Pointer dupa int: " + raf.getFilePointer());

            double value = raf.readDouble();
            System.out.println("Valoare citita: " + value);
            System.out.println("Pointer dupa double: " + raf.getFilePointer());

            raf.seek(0); // muta pointerul inapoi la inceputul fisierului
            System.out.println("Pointer dupa seek(0): " + raf.getFilePointer());

        } catch (IOException e) {
            System.out.println("Eroare showPointerDemo: " + e.getMessage());
        }
    }
}