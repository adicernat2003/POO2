package ro.unibuc.poo.lab8;

import ro.unibuc.poo.lab8.demo.AnonymousClassesDemo;
import ro.unibuc.poo.lab8.demo.LambdaDemo;
import ro.unibuc.poo.lab8.demo.MethodReferenceDemo;
import ro.unibuc.poo.lab8.demo.StreamsDemo;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== LAB 8 - Java Functional Programming ===");

        AnonymousClassesDemo.run();
        LambdaDemo.run();
        MethodReferenceDemo.run();
        StreamsDemo.run();
    }
}