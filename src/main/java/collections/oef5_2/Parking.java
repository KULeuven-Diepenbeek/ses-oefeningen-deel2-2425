package collections.oef5_2;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Parking {

    private Map<String, Instant> vehicles = new HashMap<>();
    private Set<String> paid = new HashSet<>();

    public void enterParking(String licensePlate) {
        vehicles.put(licensePlate, Instant.now());
    }

    public double amountToPay(String licensePlate) {
        var since = vehicles.get(licensePlate);
        var hours = (Duration.between(since, Instant.now()).toMillis() + 1000*3600 - 1)/(1000*3600);
        return 2 * hours;
    }

    public void pay(String licensePlate) {
        paid.add(licensePlate);
    }

    public boolean leaveParking(String licensePlate) {
        if (paid.contains(licensePlate)) {
            paid.remove(licensePlate);
            vehicles.remove(licensePlate);
            return true;
        }
        return false;
    }
}
