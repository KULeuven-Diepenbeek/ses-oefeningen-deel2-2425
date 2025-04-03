package concurrency.oef7;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Clients {
    public static final String[] urls = { "abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yz" };
    public static String randomURL() {
        return urls[new Random().nextInt(urls.length)];
    }

    private static final Downloader downloader = new Downloader();

    private static void oneThread() {
        for (int j = 0; j < 100; j++) {
            downloader.get(randomURL());
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        var start = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            executor.submit(Clients::oneThread);
        }
        executor.close(); // waits
        var end = System.currentTimeMillis();

        System.out.println("All downloads completed. Took " + (end - start)/1000 + " seconds");
    }
}
