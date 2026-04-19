package ro.unibuc.lab7.generics;

public record Pair<K, V>(K key, V value) {

    @Override
    public String toString() {
        return "Pair{key=" + key + ", value=" + value + "}";
    }
}