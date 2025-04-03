package concurrency.oef6;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

/**
 * Thread-safe en efficiente versie
 *
 * In plaats van de hele bookSeats-methode synchronized te maken,
 * gebruiken we twee specifieke locks:
 * - eentje voor het beschikbare aantal tickets
 *   (nagaan of er nog tickets zijn, en ze onmidellijk te 'reserveren')
 * - eentje om de vrije stoelen te vinden
 *
 * We vervangen de HashMap ook door een ConcurrentHashMap, zodat die vanuit meerdere threads gebruikt kan worden.
 *
 * Om de snelheid te garanderen, zorgen we dat we geen locks nemen rond operaties die lang duren
 * (bv. pre-processing, ticket-generatie)
 */
public class BookingSystem_V2 {

    private final String[] seats = {"A", "B", "C", "D", "E", "F"};
    private int availableTickets = seats.length;
    private final Map<String, String> seatAssignments = new ConcurrentHashMap<>();

    private final Object ticketCountLock = new Object();
    private final Object findSeatLock = new Object();

    public boolean isAssigned(String seat) {
        return seatAssignments.containsKey(seat);
    }

    private void generateTicket(String seat, String customer) {
        // simulate that creating a ticket takes some time
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
    }

    public List<String> bookSeats(String customer, int requestedSeats) {
        synchronized (ticketCountLock) {
            if (requestedSeats > availableTickets)
                throw new IllegalStateException("Not enough seats available");
            // ! note this was moved from the end of the method to here
            availableTickets -= requestedSeats;
        }

        // simulate that preprocessing takes a lot of time
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }

        List<String> seatsForCustomer = new ArrayList<>();
        int seatIndex = 0;
        while (seatsForCustomer.size() < requestedSeats) {
            String seat = seats[seatIndex];
            synchronized (findSeatLock) {
                while (isAssigned(seat)) {
                    seat = seats[++seatIndex];
                    // aangezien we al een reservatie hebben (begin van de methode),
                    // zijn we zeker dat er genoeg lege stoelen zullen zijn
                }
                seatAssignments.put(seat, customer);
            }
            generateTicket(seat, customer); // slow!
            seatsForCustomer.add(seat);
            seatIndex++;
        }
        return seatsForCustomer;
    }

    public static void main(String[] args) {
        var nbCustomers = 20;
        var bookingSystem = new BookingSystem_V2();
        var assignedSeats = Collections.synchronizedList(new ArrayList<String>());
        try (var executor = Executors.newFixedThreadPool(nbCustomers)) {
            // wait until all customers are ready: simulates opening ticket sales
            var ticketSalesOpening = new CyclicBarrier(nbCustomers);
            for (int i = 0; i < nbCustomers; i++) {
                final var customer = "Customer %02d".formatted(i);
                executor.execute(() -> {
                    try {
                        ticketSalesOpening.await();
                        var seats = bookingSystem.bookSeats(customer, 1);
                        System.out.println(customer + ": " + seats);
                        assignedSeats.addAll(seats);
                    } catch (IllegalStateException e) {
                        // no tickets left for customer
                    } catch (BrokenBarrierException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        var uniqueSeats = new HashSet<>(assignedSeats);
        System.out.println((assignedSeats.size() - uniqueSeats.size()) + " overbookings.");
    }
}