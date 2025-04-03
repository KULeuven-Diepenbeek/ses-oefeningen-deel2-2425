package concurrency.oef7;

import java.util.HashMap;
import java.util.Map;

public class Downloader_Original {

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

    public String get(String url) {
        if (!cache.containsKey(url)) {
            var contents = download(url);
            cache.put(url, contents);
        }
        return cache.get(url);
    }
}
