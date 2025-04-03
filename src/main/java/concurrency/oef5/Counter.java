package concurrency.oef5;

import java.util.concurrent.Semaphore;

class Counter {
    private volatile int count = 0;
    private final Semaphore sem = new Semaphore(1);

    public synchronized void increment() {
        try {
            sem.acquire();
            count++;
            sem.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void decrement() {
        try {
            sem.acquire();
            count--;
            sem.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCount() {
        return count;
    }
}