package concurrency.oef7;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Downloader {

    private static String download(String url) {
        try {
            System.out.println("Downloading " + url + " (" + Thread.currentThread() + ")");
            Thread.sleep(1000); // 1 seconde
            return "Contents of " + url;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private final Map<String, String> cache = new HashMap<>();
    /**
     * Optimalisatie: in plaats van 1 globaal lock-object, gebruiken we een lock-object per URL.
     * Zo kunnen meerdere threads `get` uitvoeren voor verschillende URL's zonder
     * te hoeven wachten.
     */
    private final Map<String, Object> locks = new ConcurrentHashMap<>();

    public String get(String url) {
        if (!cache.containsKey(url)) {
            var lock = locks.computeIfAbsent(url, k -> new Object());
            synchronized (lock) {
                if (!cache.containsKey(url)) {
                    var contents = download(url);
                    cache.put(url, contents);
                    locks.remove(url); // lock verwijderen (niet meer nodig, want url zit nu in cache)
                }
            }
        }
        return cache.get(url);
    }

}
