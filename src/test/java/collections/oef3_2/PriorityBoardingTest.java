package collections.oef3_2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class PriorityBoardingTest {

    @Test
    public void priority_example() {
        var p1 = new Passenger("Bob", 1);
        var p2 = new Passenger("Alex", 2);
        var p3 = new Passenger("Charlie", 3);
        var p4 = new Passenger("Donald", 3);

        var prio = new PriorityBoarding();
        prio.checkin(p4);
        prio.checkin(p1);
        prio.checkin(p3);
        prio.checkin(p2);

        assertThat(prio.nextPassenger()).isEqualTo(p1);
        assertThat(prio.nextPassenger()).isEqualTo(p2);
        assertThat(prio.nextPassenger()).isEqualTo(p3);
        assertThat(prio.nextPassenger()).isEqualTo(p4);
    }
}
