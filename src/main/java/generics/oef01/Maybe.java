package generics.oef01;

import java.util.function.Function;

public record Maybe<T>(T value, boolean hasValue) {

    public static <T> Maybe<T> some(T value) {
        return new Maybe<>(value, true);
    }

    public static <T> Maybe<T> none() {
        return new Maybe<>(null, false);
    }


    public T getValue() {
        return this.value();
    }

    public <R> Maybe<R> map(Function<T, R> fn) {
        if (hasValue()) {
            return Maybe.some(fn.apply(this.value()));
        } else {
            return Maybe.none();
        }
    }
}
