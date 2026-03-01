void main() {

    // ==============================
    // 1. TIPURI PRIMITIVE
    // ==============================

    int varsta = 20;
    double medie = 8.75;
    char initiala = 'A';
    boolean esteStudent = true;

    IO.println("Varsta: " + varsta);
    IO.println("Medie: " + medie);
    IO.println("Initiala: " + initiala);
    IO.println("Este student: " + esteStudent);
    IO.println();


    // ==============================
    // 2. TIPURI REFERINTA
    // ==============================

    String nume = "Andrei";
    int[] note = {8, 9, 10};

    IO.println("Nume: " + nume);
    IO.println("Prima nota: " + note[0]);
    IO.println();


    // ==============================
    // 3. OPERATORI
    // ==============================

    int a = 10;
    int b = 3;

    int suma = a + b;
    int diferenta = a - b;
    int produs = a * b;
    int impartire = a / b;
    int rest = a % b;

    IO.println("Suma: " + suma);
    IO.println("Rest: " + rest);

    // operatori relationali
    IO.println("a > b: " + (a > b));

    // operatori logici
    IO.println("esteStudent && varsta > 18: " + (esteStudent && varsta > 18));
    IO.println();


    // ==============================
    // 4. IF
    // ==============================

    if (medie >= 9) {
        IO.println("Student foarte bun");
    } else if (medie >= 5) {
        IO.println("Student promovat");
    } else {
        IO.println("Student restantier");
    }
    IO.println();


    // ==============================
    // 5. SWITCH CLASIC
    // ==============================

    int zi = 3;

    switch (zi) {
        case 1:
            IO.println("Luni");
            break;
        case 2:
            IO.println("Marti");
            break;
        case 3:
            IO.println("Miercuri");
            break;
        default:
            IO.println("Alta zi");
    }
    IO.println();


    // ==============================
    // 6. ENHANCED SWITCH (Java 14+)
    // ==============================

    String tipZi = switch (zi) {
        case 1, 2, 3, 4, 5 -> "Zi lucratoare";
        case 6, 7 -> "Weekend";
        default -> "Necunoscut";
    };

    IO.println("Tip zi: " + tipZi);
    IO.println();


    // ==============================
    // 7. SWITCH CU YIELD
    // ==============================

    String mesaj = switch (medie >= 5 ? 1 : 0) {
        case 1 -> {
            IO.println("Studentul va promova...");
            yield "Student promovat";
        }
        case 0 -> {
            IO.println("Studentul va fi respins...");
            yield "Student respins";
        }
        default -> "Eroare";
    };

    IO.println("Mesaj: " + mesaj);
    IO.println();


    // ==============================
    // 8. OPERATOR TERNAR
    // ==============================

    String major = (varsta >= 18) ? "Major" : "Minor";
    IO.println("Persoana este: " + major);
    IO.println();


    // ==============================
    // 9. WHILE
    // ==============================

    int contor = 0;

    while (contor < 3) {
        IO.println("While - contor: " + contor);
        contor++;
    }
    IO.println();


    // ==============================
    // 10. DO-WHILE
    // ==============================

    int x = 0;

    do {
        IO.println("Do-While - x: " + x);
        x++;
    } while (x < 3);
    IO.println();


    // ==============================
    // 11. FOR cu CONTINUE si BREAK
    // ==============================

    for (int i = 0; i < 10; i++) {

        if (i == 2) {
            continue; // sare peste iteratia 2
        }

        if (i == 7) {
            break; // opreste complet bucla
        }

        IO.println("For - i: " + i);
    }

}
