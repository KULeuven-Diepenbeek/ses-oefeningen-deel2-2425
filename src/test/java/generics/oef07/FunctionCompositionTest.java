package generics.oef07;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.function.Function;
import static generics.oef07.FunctionUtils.compose;
import static generics.oef07.FunctionUtils.compose_alt;

public class FunctionCompositionTest {

    interface Ingredient {}
    record Fruit() implements Ingredient {}
    record PeeledFruit(Fruit fruit) implements Ingredient {}
    record Chopped(Ingredient food) implements Ingredient {}

    @Test
    public void testCompose() {
        Function<Fruit, PeeledFruit> peelFruit = (var fruit) -> new PeeledFruit(fruit);
        Function<Ingredient, Chopped> chopIngredient = (var food) -> new Chopped(food);

        var makeFruitSalad = compose(peelFruit, chopIngredient);

        assertThat(makeFruitSalad.apply(new Fruit())).isEqualTo(new Chopped(new PeeledFruit(new Fruit())));
    }

    @Test
    public void testCompose_alt() {
        Function<Fruit, PeeledFruit> peelFruit = (var fruit) -> new PeeledFruit(fruit);
        Function<Ingredient, Chopped> chopIngredient = (var food) -> new Chopped(food);

        var makeFruitSalad = compose_alt(peelFruit, chopIngredient);

        assertThat(makeFruitSalad.apply(new Fruit())).isEqualTo(new Chopped(new PeeledFruit(new Fruit())));
    }
}
