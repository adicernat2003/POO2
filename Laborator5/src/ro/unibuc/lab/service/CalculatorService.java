package ro.unibuc.lab.service;

public class CalculatorService {

    public double computeAverage(String sum, String count) {
        int total = Integer.parseInt(sum);
        int numberOfItems = Integer.parseInt(count);

        if (numberOfItems == 0) {
            throw new ArithmeticException("Nu se poate imparti la zero.");
        }

        return (double) total / numberOfItems;
    }
}