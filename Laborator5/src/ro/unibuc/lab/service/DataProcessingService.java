package ro.unibuc.lab.service;

import ro.unibuc.lab.io.FileConfigLoader;

import java.io.IOException;

public class DataProcessingService {

    private final FileConfigLoader loader = new FileConfigLoader();

    public void processFile(String filePath) throws IOException {
        System.out.println("Incepe procesarea fisierului...");
        loader.loadWithTryResources(filePath);
        System.out.println("Procesarea s-a terminat.");
    }
}
