package generics.oef06;

import java.util.ArrayList;
import java.util.List;

public class Shop<I extends StockItem> {
    private final ArrayList<I> stock = new ArrayList<>();

    // ? extends I
    // want PECS: de parameter (lijst van items) produceert I's die gebruikt worden in deze methode
    public void buy(List<? extends I> items) {
        stock.addAll(items);
    }

    // ? super I
    // want PECS: de parameter (inventory-lijst) consumeert I's die aangeleverd worden door deze methode
    public void addStockToInventory(List<? super I> inventory) {
        inventory.addAll(stock);
    }
}
