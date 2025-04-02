package collections.oef4_1;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Timing {

    record DefaultHashcode(int i) {}
    record CustomHashcode(int i) {
        @Override
        public int hashCode() {
            return 4;
        }
    }

    public static void main(String[] args) {
        System.out.print("With default hashcode: ");
        test(DefaultHashcode::new);
        System.gc();
        System.out.print("With identical hashcode: ");
        test(CustomHashcode::new);
    }

    private static <T> void test(Function<Integer, T> ctor) {
        var set = new HashSet<T>();
        var start = System.nanoTime();
        for (int i = 0; i < 50_000; i++) {
            set.add(ctor.apply(i));
        }
        var end = System.nanoTime();
        System.out.println(set.size() + " elements added in " + TimeUnit.NANOSECONDS.toMillis(end - start) + "ms");
    }
}