package generics.oef06;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ShopTest {

    @Test
    public void testGenerics() {
        Shop<Fruit> fruitShop = new Shop<>();
        Shop<Electronics> electronicsShop = new Shop<>();

        List<Apple> apples = List.of(new Apple(), new Apple());
        List<Fruit> oranges = List.of(new Orange(), new Orange(), new Orange());

        List<Smartphone> phones = List.of(new Smartphone(), new Smartphone());

        fruitShop.buy(apples);
        fruitShop.buy(oranges);

        electronicsShop.buy(phones);

        List<StockItem> inventory = new ArrayList<>();
        fruitShop.addStockToInventory(inventory);
        assertThat(inventory).hasSize(5);

        electronicsShop.addStockToInventory(inventory);

        assertThat(inventory).hasSize(7);
    }
}
