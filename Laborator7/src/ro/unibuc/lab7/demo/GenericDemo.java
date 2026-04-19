package ro.unibuc.lab7.demo;

import ro.unibuc.lab7.generics.Box;
import ro.unibuc.lab7.generics.GenericUtils;
import ro.unibuc.lab7.generics.NumberBox;
import ro.unibuc.lab7.generics.Pair;

import java.util.ArrayList;
import java.util.List;

public class GenericDemo {

    public static void run() {
        System.out.println("=== GENERIC DEMO ===");

        Box<String> messageBox = new Box<>("Salut!");
        Box<Integer> numberBoxSimple = new Box<>(100);
        Box<List<Integer>> listBoxSimple = new Box<>(new ArrayList<>());

        listBoxSimple.getContent().add(1);
        listBoxSimple.getContent().add(2);

        System.out.println(messageBox);
        System.out.println(numberBoxSimple);
        System.out.println(listBoxSimple);

        NumberBox<Double> doubleBox = new NumberBox<>(9.75);
        System.out.println("NumberBox value as double = " + doubleBox.doubleValue());

        var studentPair = new Pair<>("Ana", new ArrayList<>());
        studentPair.value().add(20);
        System.out.println(studentPair);
        System.out.println(studentPair.key());
        System.out.println(studentPair.value());

        String[] names = {"Ana", "Mihai", "Ioana"};
        Integer[] values = {1, 2, 3};

        System.out.println("\nprintArray pentru String:");
        GenericUtils.printArray(names);

        System.out.println("\nprintArray pentru Integer:");
        GenericUtils.printArray(values);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        System.out.println("\nprintList pentru List <Integer>:");
        GenericUtils.printList(numbers);

        System.out.println("\nSuma numerelor = " + GenericUtils.sumNumbers(numbers));

        List<Number> moreNumbers = new ArrayList<>();
        GenericUtils.addDefaultIntegers(moreNumbers);
        System.out.println("Dupa addDefaultIntegers: " + moreNumbers);

        System.out.println("Primul element = " + GenericUtils.getFirst(numbers));
        System.out.println();
    }
}