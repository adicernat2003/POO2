package lab.app;

import lab.service.LabManager;
import lab.util.LifecycleDemo;

public class Main {
    public static void main(String[] args) {
        LabManager labManager = new LabManager();
        labManager.runDemo();

        for (int i = 0; i < 3; i++) {
            System.out.println();
        }

        LifecycleDemo.run();
    }
}
