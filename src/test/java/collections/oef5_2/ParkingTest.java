package collections.oef5_2;

import org.junit.jupiter.api.Test;


import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

public class ParkingTest {

    @Test
    public void test_parking() throws InterruptedException {
        Parking parking = new Parking();
        parking.enterParking("ABC123");
        Thread.sleep(Duration.ofSeconds(1));
        var amount = parking.amountToPay("ABC123");
        assertThat(amount).isEqualTo(2.0);
        assertThat(parking.leaveParking("ABC123")).isFalse();
        parking.pay("ABC123");
        assertThat(parking.leaveParking("ABC123")).isTrue();
    }
}
