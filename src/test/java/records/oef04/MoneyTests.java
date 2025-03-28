package records.oef04;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MoneyTests {

    @Test
    public void rejects_no_currency() {
        assertThatNullPointerException().isThrownBy(() -> new Money(20, null));
    }

    @Test
    public void add_same_currency() {
        var m1 = new Money(20, "USD");
        var m2 = new Money(30, "USD");
        assertThat(m1.add(m2)).isEqualTo(new Money(50, "USD"));
    }

    @Test
    public void add_different_currency() {
        var m1 = new Money(20, "USD");
        var m2 = new Money(30, "EUR");
        assertThatIllegalArgumentException().isThrownBy(() -> m1.add(m2));
    }
}
