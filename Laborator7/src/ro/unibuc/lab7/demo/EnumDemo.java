package ro.unibuc.lab7.demo;

import ro.unibuc.lab7.enums.CourseType;
import ro.unibuc.lab7.enums.StudentStatus;

public class EnumDemo {

    public static void run() {
        System.out.println("=== ENUM DEMO ===");

        StudentStatus status = StudentStatus.ACTIVE;
        CourseType courseType = CourseType.MANDATORY;

        System.out.println("Status: " + status);
        System.out.println("Descriere status: " + status.getDescription());
        System.out.println("Tip curs: " + courseType);
        System.out.println("Descriere tip curs: " + courseType.getDescription());

        System.out.println("\nToate valorile StudentStatus:");
        for (StudentStatus value : StudentStatus.values()) {
            System.out.println(value + " | ordinal=" + value.ordinal());
        }

        StudentStatus parsed = StudentStatus.valueOf("GRADUATED");
        System.out.println("\nvalueOf(\"GRADUATED\") = " + parsed);

        for (StudentStatus value : StudentStatus.values()) {
            switch (value) {
                case ACTIVE -> System.out.println("Studentul este activ.");
                case SUSPENDED -> System.out.println("Studentul este suspendat.");
                case GRADUATED -> System.out.println("Studentul a absolvit.");
            }
        }

        System.out.println();
    }

}
