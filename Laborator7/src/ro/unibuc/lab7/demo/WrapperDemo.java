package ro.unibuc.lab7.demo;

import java.util.ArrayList;
import java.util.List;

public class WrapperDemo {

    public static void run() {
        System.out.println("=== WRAPPER DEMO ===");

        int primitiveValue = 5;

        Integer boxedValue = primitiveValue; // autoboxing
        int unboxedValue = boxedValue;       // unboxing

        System.out.println("primitiveValue = " + primitiveValue);
        System.out.println("boxedValue = " + boxedValue);
        System.out.println("unboxedValue = " + unboxedValue);

        List<Integer> grades = new ArrayList<>();
        grades.add(10); // autoboxing
        grades.add(9);
        grades.add(8);

        int sum = 0;
        for (Integer grade : grades) {
            sum += grade; // unboxing
        }

        System.out.println("Lista note = " + grades);
        System.out.println("Suma notelor = " + sum);
        System.out.println();
    }
}