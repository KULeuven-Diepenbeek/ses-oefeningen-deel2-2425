package records.oef03;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void rejects_null_address() {
        assertThatNullPointerException().isThrownBy(() -> new Email(null));
    }

    @Test
    public void rejects_no_at_sign() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Email("mymailaddress"));
    }

    @Test
    public void rejects_two_at_signs() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Email("my@mailaddress@example.com"));
    }

    @Test
    public void accepts_one_at_sign() {
        assertThatNoException().isThrownBy(() -> new Email("mymailaddress@example.com"));
    }

    @Test
    public void rejects_ending_with_org() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Email("myaddress@example.org"));
    }

    @Test
    public void accepts_ending_with_com() {
        assertThatNoException().isThrownBy(() -> new Email("myaddress@example.com"));
    }

    @Test
    public void accepts_ending_with_be() {
        assertThatNoException().isThrownBy(() -> new Email("myaddress@example.be"));
    }

}