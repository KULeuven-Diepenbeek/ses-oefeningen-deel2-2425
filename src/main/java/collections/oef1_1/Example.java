package collections.oef1_1;

public class Example {
    public static void main(String[] args) {
        IntRange range = new IntRange(3, 6);
        for (int x : range) {
            System.out.println(x);
        }
    }
}
