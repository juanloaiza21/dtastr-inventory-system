package item;
import java.sql.SQLException;
import java.util.*;
/**
 * Item filter implementation
 * @author Juan Loaiza
 * @version 1.0
 */
public class ItemFilter {
    
    private ItemA[] item;
    private TreeMap<Integer, Integer> filterStock;
    private TreeMap<Double, Integer> filterPrice;
    private TreeMap<String, Integer> filterName;
    
    /**
     * Constructor
     * @param item
     */
    public ItemFilter(ItemA[] item){
        this.item = item;
        filterStock = new TreeMap<Integer, Integer>();
        filterPrice = new TreeMap<Double, Integer>();
        filterName = new TreeMap<String, Integer>();
        for (int i = 0; i < this.item.length; i++) {
            this.filterStock.put(this.item[i].getStock(), this.item[i].getId());
            this.filterPrice.put(this.item[i].getPrice(), this.item[i].getId());
            this.filterName.put(this.item[i].getName(), this.item[i].getId());
        }
    }

    /**
     * @param stock
     * @return id
     */
    public int filterBySpecificStock(int stock){
        return this.filterStock.get(stock);
    }

    /**
     * @param price
     * @return id
     */
    public int filterBySpecificIdPrice(double price){
        return this.filterPrice.get(price);
    }

    /**
     * @param name
     * @return id
     */
    public int filterBySpecificIdName(String name){
        return this.filterName.get(name);
    }

    /**
     * @param key
     * @return Sorted map, name=id
     */
    public SortedMap<String, Integer> nameLessThan(String key) {
        return this.filterName.headMap(key);
    }

    /**
     * @param key
     * @return Sorted map, stock=id
     */
    public SortedMap<Integer, Integer> stockLessThan(int key) {
        return this.filterStock.headMap(key);
    }

    /**
     * @param key
     * @return Sorted map, price=id
     */
    public SortedMap<Double, Integer> priceLessThan(double key) {
        return this.filterPrice.headMap(key);
    }

    /**
     * @param key
     * @return Map.Entry, directly greater than  entry, name=id
     */
    public Map.Entry<String, Integer> nameGreatherThan(String key) {
        return this.filterName.higherEntry(key);
    }

    /**
     * @param key
     * @return Map.Entry, directly greater than  entry, stock=id
     */
    public Map.Entry<Integer, Integer> stockGreatherThan(int key) {
        return this.filterStock.higherEntry(key);
    }

    /**
     * @param key
     * @return Map.Entry, directly greater than  entry, price=id
     */
    public Map.Entry<Double, Integer> priceGreatherThan(double key) {
        return this.filterPrice.higherEntry(key);
    }

    

    public static void main(String[] args) throws SQLException {
        ItemA[] item = new ItemA[5];
        item[0] = new Item(1, "item1", 1.0, 6);
        item[1] = new Item(7, "item2", 2.0, 2);
        item[2] = new Item(3, "item3", 3.0, 3);
        item[3] = new Item(4, "item4", 4.0, 4);
        item[4] = new Item(5, "item5", 5.0, 5);
        ItemFilter itemFilter = new ItemFilter(item);
        Map.Entry<Double, Integer> price = itemFilter.priceGreatherThan(3.0);
        System.out.println(price);
    }
}
