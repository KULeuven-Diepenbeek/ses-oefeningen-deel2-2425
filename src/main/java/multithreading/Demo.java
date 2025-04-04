package multithreading;

public class Demo {

    private static void print() {
        System.out.println("Hello from " + Thread.currentThread().getName());
    }


    public static void main(String[] args) {
        Thread thread = new Thread(Demo::print);
        Thread thread2 = new Thread(Demo::print);
        thread.start();
        thread2.start();
    }
}
