package ro.unibuc.lab.inheritance;

import java.io.FileNotFoundException;

public class AdvancedReporter extends BaseReporter {

    @Override
    public void generateReport() throws FileNotFoundException {
        System.out.println("Generez raportul avansat...");
        throw new FileNotFoundException("Fisierul pentru raport nu a fost gasit.");
    }
}
