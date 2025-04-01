package collections.oef3_2;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityBoarding {

    private final PriorityQueue<Passenger> queue =
            new PriorityQueue<>(
            Comparator.comparing(Passenger::priority)
                    .thenComparing(Passenger::name));

    public void checkin(Passenger p) {
        queue.offer(p);
    }

    public Passenger nextPassenger() {
        return queue.poll();
    }
}
