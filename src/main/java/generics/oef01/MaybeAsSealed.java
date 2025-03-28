package generics.oef01;

import java.util.function.Function;

public sealed interface MaybeAsSealed<T> {

    boolean hasValue();

    static <T> Some<T> some(T value) {
        return new Some<>(value);
    }
    static <T> None<T> none() {
        return new None<>();
    }

    <R> MaybeAsSealed<R> map(Function<T, R> fn);


    record Some<T>(T value) implements MaybeAsSealed<T> {
        @Override
        public boolean hasValue() {
            return true;
        }

        public T getValue() {
            return value;
        }

        @Override
        public <R> Some<R> map(Function<T, R> fn) {
            return MaybeAsSealed.some(fn.apply(this.value()));
        }
    }
    record None<T>() implements MaybeAsSealed<T> {
        @Override
        public boolean hasValue() {
            return false;
        }

        @Override
        public <R> None<R> map(Function<T, R> fn) {
            return MaybeAsSealed.none();
        }
    }

}
