package generics.oef01;

public class Maybe<T> {

    private final T value;

    public static <U> Maybe<U> some(U value) {
        return new Maybe<U>(value);
    }
    public static <U> Maybe<U> none() {
        return new Maybe<U>(null);
    }
    private Maybe(T value) {
        this.value = value;
    }

    public boolean hasValue() {
        return value != null;
    }
    public T getValue() {
        return value;
    }
}
