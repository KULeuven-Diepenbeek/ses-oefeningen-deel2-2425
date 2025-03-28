package generics.oef01;

class MaybePrint {
    public static <T> void print(Maybe<T> maybe) {
        if (maybe.hasValue()) {
            System.out.println("Contains a value: " + maybe.getValue());
        } else {
            System.out.println("No value :(");
        }
    }

    /** Alternatieve oplossing: gebruik van een wildcard */
    public static void print_alternative(Maybe<?> maybe) {
        if (maybe.hasValue()) {
            System.out.println("Contains a value: " + maybe.getValue());
        } else {
            System.out.println("No value :(");
        }
    }

    public static void main(String[] args) {
        Maybe<String> maybeAString = Maybe.some("yes");
        Maybe<String> maybeAnotherString = Maybe.none();

        print(maybeAString);
        print(maybeAnotherString);

        // Test met Integer
        Maybe<Integer> maybeAnInteger = Maybe.some(4);
        print(maybeAnInteger);
        print_alternative(maybeAnInteger);
    }
}