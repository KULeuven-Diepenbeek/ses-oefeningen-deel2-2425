package generics.demo;

import java.util.function.Function;

public class FunctionExample {

    public interface HasName {
        String name();
    }

    public record Person(String name) implements HasName {} ;

    public static <T> T extractInfo(
            Function<? super Person, ? extends T> fn) {
            //Function<Person, T> fn) {
        return fn.apply(new Person("Me"));
    }

    public static void main(String[] args) {
        Function<HasName, String> fn = HasName::name;

        var extracted = extractInfo(fn);
        System.out.println(extracted);
    }
}
