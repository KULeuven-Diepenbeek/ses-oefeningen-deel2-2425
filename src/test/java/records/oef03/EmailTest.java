package records.oef03;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class EmailTest {

    @Test
    public void email_address_is_stored() {
        var email = new Email("test@example.com");
        assertThat(email.address()).isEqualTo("test@example.com");
    }
    @Test
    public void rejects_null() {
        assertThatNullPointerException().isThrownBy(
                () -> new Email(null));
    }
    @Test
    public void requires_at_least_one_at() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Email("testexample.com"));
    }
    @Test
    public void rejects_more_than_one_at() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Email("test@ex@ample.com")
        );
    }
    @Test
    public void rejects_not_com_be() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Email("test@example.org")
        );
    }

}
