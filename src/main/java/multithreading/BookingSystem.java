package multithreading;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

public class BookingSystem {

    private final String[] seats = {"A", "B", "C", "D", "E", "F"};
    private int availableTickets = seats.length;
    private final Map<String, String> seatAssignments = new HashMap<>();

    public boolean isAssigned(String seat) {
        return seatAssignments.containsKey(seat);
    }

    private void generateTicket(String seat, String customer) {
        // simulate that creating a ticket takes some time
        try {
            Thread.sleep(2100);
        } catch (InterruptedException ignored) {
        }
    }

    private final Object reservationLock = new Object();

    public List<String> bookSeats(String customer, int requestedSeats) {
        synchronized (this) {
            if (requestedSeats > availableTickets)
                throw new IllegalStateException("Not enough seats available");
            availableTickets -= requestedSeats;
        }

        // simulate that preprocessing takes a lot of time
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }

        List<String> seatsForCustomer = new ArrayList<>();
        synchronized (reservationLock) {
            int seatIndex = 0;
            while (seatsForCustomer.size() < requestedSeats) {
                String seat = seats[seatIndex];
                if (!isAssigned(seat)) {
                    seatAssignments.put(seat, customer);
                    seatsForCustomer.add(seat);
                }
                seatIndex++;
            }
        }
        for (var seat : seatsForCustomer) {
            generateTicket(seat, customer);
        }
        return seatsForCustomer;
    }

    public static void main(String[] args) {
        var nbCustomers = 20;
        var bookingSystem = new BookingSystem();
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