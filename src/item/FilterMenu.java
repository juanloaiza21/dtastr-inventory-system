package item;

import java.sql.SQLException;

public class FilterMenu {
    
    private ItemFilter itemFilter;
    private ItemA[] items;
    private Item item;

    public FilterMenu() throws SQLException {
        this.item = new Item();
        this.items = new ItemA[item.getItems().size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = item.getItems().get(i);
        }
        this.itemFilter = new ItemFilter(items);
    }

    

}
