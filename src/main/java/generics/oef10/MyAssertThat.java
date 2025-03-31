package generics.oef10;

import java.util.List;

public class MyAssertThat {
    /**
     * Het generisch type SELF is bedoeld om naar de concrete subklasse van Assertion te wijzen,
     * Bijvoorbeeld, wanneer je een StringAssertion aanmaakt, zal SELF = StringAssertion.
     * Zo kan de methode isNotNull() een assertion teruggeven van hetzelfde type als waarmee je begonnen bent
     * (bv. een StringAssertion als je .isNotNull() op een StringAssertion-object oproept).
     * <p>
     * Dit heet een self-referential generic type, en is erg populair bij zogenaamde 'fluent' interfaces.
     */
    public static abstract class Assertion<T, SELF extends Assertion<T, SELF>> {
        private final T object;

        protected Assertion(T object) {
            this.object = object;
        }

        protected T getObject() {
            return object;
        }

        protected abstract SELF self();

        public SELF isNotNull() {
            if (getObject() == null) {
                throw new AssertionError("Object is null");
            }
            return self();
        }
    }

    public static class ListAssertion<T> extends Assertion<List<T>, ListAssertion<T>> {
        public ListAssertion(List<T> object) {
            super(object);
        }

        @Override
        protected ListAssertion<T> self() {
            return this;
        }

        public ListAssertion<T> hasSize(int n) {
            if (getObject().size() != n) {
                throw new AssertionError("Wrong size");
            }
            return this;
        }

        public ListAssertion<T> containsItem(T item) {
            if (!getObject().contains(item)) {
                throw new AssertionError("Does not contain item");
            }
            return this;
        }
    }

    public static class StringAssertion extends Assertion<String, StringAssertion> {
        public StringAssertion(String object) {
            super(object);
        }

        @Override
        protected StringAssertion self() {
            return this;
        }

        public StringAssertion isEqualToIgnoringCase(String str) {
            if (!getObject().equalsIgnoreCase(str)) {
                throw new AssertionError("Not equal");
            }
            return this;
        }
    }

    public static class IntegerAssertion extends Assertion<Integer, IntegerAssertion> {
        public IntegerAssertion(Integer object) {
            super(object);
        }

        @Override
        protected IntegerAssertion self() {
            return this;
        }

        public IntegerAssertion isGreaterThan(int n) {
            if (getObject() <= n) throw new AssertionError("Not greater than");
            return this;
        }
    }

    public static <T> ListAssertion<T> assertThat(List<T> obj) {
        return new ListAssertion<>(obj);
    }

    public static StringAssertion assertThat(String obj) {
        return new StringAssertion(obj);
    }

    public static IntegerAssertion assertThat(int obj) {
        return new IntegerAssertion(obj);
    }

    public static void main(String[] args) {
        // een List<String>
        List<String> someListOfStrings = List.of("hello", "there", "how", "are", "you");
        assertThat(someListOfStrings).isNotNull().hasSize(5).containsItem("hello");

        // een String
        String someString = "hello";
        assertThat(someString).isNotNull().isEqualToIgnoringCase("hello");

        // een Integer
        int someInteger = 4;
        assertThat(someInteger).isNotNull().isGreaterThan(4);


        // assertThat(someInteger).isNotNull().isEqualToIgnoringCase("hello"); // <= compileert niet ❌
    }
}
