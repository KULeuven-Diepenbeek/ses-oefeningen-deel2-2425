package generics.oef06;

import java.util.ArrayList;
import java.util.List;

public class Shop<I extends StockItem> {
    private final ArrayList<I> stock = new ArrayList<>();

    public void buy(List<? extends I> items) {
        stock.addAll(items);
    }

    public void addStockToInventory(List<? super StockItem> inventory) {
        inventory.addAll(stock);
    }
}
