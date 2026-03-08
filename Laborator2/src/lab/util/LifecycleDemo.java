package lab.util;

import lab.model.Student;

/**
 * Demonstreaza pe scurt ciclul de viata al unui obiect.
 */
public final class LifecycleDemo {
    private LifecycleDemo() {
    }

    public static void run() {
        System.out.println("\n=== Ciclul de viata al unui obiect ===");

        // 1. Declarare
        Student reference;
        System.out.println("1. Declarare: exista doar referinta, fara obiect creat inca.");

        // 2. Instantiere
        reference = new Student(99, "ObiectTemporar", 1);
        System.out.println("2. Instantiere: obiectul a fost creat in heap.");

        // 3. Utilizare
        reference.printStatus();
        System.out.println("3. Utilizare: referinta acceseaza atribute si metode.");

        // 4. Dereferentiere
        reference = null;
        System.out.println("4. Dereferentiere: referinta a devenit null.");

        // 5. Sugeram GC
        System.gc();
        System.out.println("5. Garbage Collection: Java poate elibera memoria ulterior.");
    }
}
