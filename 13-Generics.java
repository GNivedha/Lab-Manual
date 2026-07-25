class Box<T> {

    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }

    public void showType() {
        System.out.println("Type of stored item : " + item.getClass().getName());
    }
}

class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void display() {
        System.out.println(key + " = " + value);
    }
}

public class GenericDemo {

    public static <T extends Comparable<T>> T findMax(T[] array) {

        T max = array[0];

        for (int i = 1; i < array.length; i++) {

            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {

        Box<Integer> intBox = new Box<>();
        intBox.set(100);

        System.out.println("Integer Box Value : " + intBox.get());
        intBox.showType();

        System.out.println();

        Box<String> strBox = new Box<>();
        strBox.set("Hello Generics");

        System.out.println("String Box Value : " + strBox.get());
        strBox.showType();

        System.out.println();

        System.out.println("---- Key-Value Pairs ----");

        Pair<String, Integer> p1 = new Pair<>("Rahul", 88);
        Pair<Integer, String> p2 = new Pair<>(101, "CSE");

        p1.display();
        p2.display();

        System.out.println();

        Integer[] numbers = {45, 67, 23, 89, 56};
        String[] names = {"Rahul", "Kiran", "Sneha", "Arjun"};
        Double[] marks = {85.5, 92.3, 78.9, 88.1};

        System.out.println("Maximum Number : " + findMax(numbers));
        System.out.println("Maximum (Alphabetical) : " + findMax(names));
        System.out.println("Maximum Marks : " + findMax(marks));
    }
}
