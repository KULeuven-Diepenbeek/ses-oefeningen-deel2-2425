package generics.oef01;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MaybeTest {
    @Test
    public void maybeWithValue() {
        Maybe<String> maybe = Maybe.some("Yes");
        assertThat(maybe.hasValue()).isTrue();
        assertThat(maybe.getValue()).isEqualTo("Yes");
    }

    @Test
    public void maybeWithoutValue() {
        Maybe<String> maybe = Maybe.none();
        assertThat(maybe.hasValue()).isFalse();
        assertThat(maybe.getValue()).isNull();
    }
}
