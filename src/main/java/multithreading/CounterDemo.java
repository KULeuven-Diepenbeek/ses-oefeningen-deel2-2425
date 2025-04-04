package multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterDemo {
    static class Counter {
        private int count = 0;

        public void increment() { count++; }
        public void decrement() { count--; }
        public int getCount() { return count; }
    }

    public static void main(String[] args) throws InterruptedException {

        var counter = new Counter();

        var inc = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) counter.increment();
        });
        var dec = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) counter.decrement();
        });

        inc.start();
        dec.start();
        inc.join();
        dec.join();

        System.out.println(counter.getCount());
    }
}
