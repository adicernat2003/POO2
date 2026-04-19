package ro.unibuc.lab7.generics;

public class NumberBox<T extends Number> {
    private final T number;

    public NumberBox(T number) {
        this.number = number;
    }

    public double doubleValue() {
        return number.doubleValue();
    }

    @Override
    public String toString() {
        return "NumberBox{number=" + number + "}";
    }
}