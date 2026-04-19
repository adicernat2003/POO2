package ro.unibuc.lab7.generics;

public class Box<T> {
    private final T content;

    public Box(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Box{content=" + content + "}";
    }
}