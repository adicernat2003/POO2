package ro.unibuc.lab.service;

import ro.unibuc.lab.model.Student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryDataService {

    public void saveStudents(String fileName, Student[] students) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName))) {
            out.writeInt(students.length);  // scriem intai numarul de studenti, ca sa stim cate obiecte sa citim la citire

            for (Student student : students) {
                out.writeUTF(student.nume());
                out.writeInt(student.grupa());
                out.writeDouble(student.medie());
            }

            System.out.println("Studentii au fost salvati binar.");
        } catch (IOException e) {
            System.out.println("Eroare la salvare binara: " + e.getMessage());
        }
    }

    public void printStudentsFromBinary(String fileName) {
        Student[] students = loadStudents(fileName);
        if (students.length == 0) {
            System.out.println("Nu exista studenti sau fisierul nu a putut fi citit.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }

        // In fisierul binar vom putea citi cu Hex Editor (ca bytes) -> numar studenti (4 bytes)
        // -> lungime string nume (2 bytes), nume (cate 1 byte per caracter), grupa (4 bytes), medie (8 bytes) ... pana la finalul fisierului
    }

    private Student[] loadStudents(String fileName) {
        Student[] students = null;

        try (DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            int n = in.readInt();

            students = new Student[n];

            for (int i = 0; i < n; i++) {
                String nume = in.readUTF();
                int grupa = in.readInt();
                double medie = in.readDouble();

                students[i] = new Student(nume, grupa, medie);
            }

            System.out.println("Studentii au fost cititi din fisierul binar.");
        } catch (IOException e) {
            System.out.println("Eroare la citire binara: " + e.getMessage());
        }

        return students;
    }
}