package generics.oef01;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MaybeAsSealedTests {

    @Test
    public void maybeWithValue() {
        var maybe = MaybeAsSealed.some("Yes");
        assertThat(maybe.hasValue()).isTrue();
        assertThat(maybe.getValue()).isEqualTo("Yes");
    }

    @Test
    public void maybeWithoutValue() {
        var maybe = MaybeAsSealed.none();
        assertThat(maybe.hasValue()).isFalse();
        // assertThat(maybe.getValue()).isNull(); compiler error
    }

    @Test
    public void maybeMapWithValue() {
        var maybe = MaybeAsSealed.some("Hello");
        var result = maybe.map((str) -> str.length());
        assertThat(result.hasValue()).isTrue();
        assertThat(result.getValue()).isEqualTo(5);
    }

    @Test
    public void maybeMapWithValue2() {
        var maybe = MaybeAsSealed.some("Hello");
        var result = maybe.map((str) -> str + "!");
        assertThat(result.hasValue()).isTrue();
        assertThat(result.getValue()).isEqualTo("Hello!");
    }

    @Test
    public void maybeMapWithoutValue() {
        var maybe = MaybeAsSealed.<String>none();
        var result = maybe.map((str) -> str.length());
        assertThat(result.hasValue()).isFalse();
    }
}
