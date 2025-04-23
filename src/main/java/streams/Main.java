package streams;

import java.util.*;
import java.util.stream.Collectors;
import static streams.Data.DATASET;

public class Main {
    public static void main(String[] args) {
        exercise01();
        exercise02();
        exercise03();
        exercise04();
        exercise05();
        exercise06();
        exercise07();
        exercise08();
        exercise09();
        exercise10();
        exercise11();
        exercise12();
        exercise13();
        exercise14();
        exercise15();
        exercise16();
    }

    private static void exercise01() {
        // TODO
        // je kan print gebruiken om het resultaat af te drukken, bv.
        print(DATASET.stream()
                .map(adult -> adult.firstName() + " " + adult.lastName())
                .toList()
        );
    }

    private static void exercise02() {
        // TODO
        print(DATASET.stream()
                .allMatch(adult -> adult.age() >= 18)
        );
    }

    private static void exercise03() {
        // TODO
        print(DATASET.stream()
                .anyMatch(adult -> adult.firstName().equals("Joseph"))
        );
    }

    private static void exercise04() {
        // TODO
        var stats = DATASET.stream()
                .mapToInt(Data.Adult::age)
                .filter(age -> age >= 30)
                .summaryStatistics();
        print(stats.getMin());
        print(stats.getAverage());
        print(stats.getMax());
    }

    private static void exercise05() {
        // TODO
        print(DATASET.stream()
                .max(Comparator.comparing(adult -> adult.lastName().length()))
        );
    }

    private static void exercise06() {
        // TODO
        print(DATASET.stream()
                .collect(Collectors.groupingBy(adult -> adult.zipCode()))
        );
    }

    private static void exercise07() {
        // TODO
        print(DATASET.stream()
                .sorted(Comparator.comparing(Data.Adult::age).reversed())
                .limit(5)
                .sorted(Comparator.comparing(Data.Adult::firstName))
                .map(adult -> adult.firstName() + " " +
                        adult.lastName() + " " + adult.age())
                .collect(Collectors.joining("\n"))
        );
    }

    private static void exercise08() {
        // TODO
    }

    private static void exercise09() {
        // TODO
    }

    private static void exercise10() {
        // TODO
    }

    private static void exercise11() {
        // TODO
    }

    private static void exercise12() {
        // TODO
    }

    private static void exercise13() {
        // TODO
    }

    private static void exercise14() {
        // TODO
    }

    private static void exercise15() {
        // TODO
    }

    private static void exercise16() {
        // TODO
    }

    // Hulpfunctie om het resultaat van een oefening te printen
    // Voegt ook de oefening (naam van de methode) toe.
    private static void print(Object value) {
        var s = Arrays.stream(Thread.currentThread().getStackTrace())
                .dropWhile(m -> !m.getMethodName().startsWith("ex"))
                .findFirst().get();
        if (value instanceof Optional<?> o) {
            if (o.isPresent())
                value = o.get();
            else
                value = "Nothing found.";
        } else if (value instanceof OptionalDouble o) {
            if (o.isPresent()) {
                value = o.getAsDouble();
            } else {
                value = "Nothing found.";
            }
        }
        System.out.println("\n- " + s.getMethodName() + ":\n" +
                value.toString().lines().map(l -> "  " + l).collect(Collectors.joining("\n")));
    }
}