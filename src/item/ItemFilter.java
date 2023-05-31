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
    private TreeMap<String, Integer> filterSeller;
    
    /**
     * Constructor
     * @param item
     */
    public ItemFilter(ItemA[] item){
        this.item = item;
        filterStock = new TreeMap<Integer, Integer>();
        filterPrice = new TreeMap<Double, Integer>();
        filterName = new TreeMap<String, Integer>();
        filterSeller = new TreeMap<String, Integer>();
        for (int i = 0; i < this.item.length; i++) {
            this.filterStock.put(this.item[i].getStock(), this.item[i].getId());
            this.filterPrice.put(this.item[i].getPrice(), this.item[i].getId());
            this.filterName.put(this.item[i].getName(), this.item[i].getId());
            this.filterSeller.put(this.item[i].getSeller(), this.item[i].getId());
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
     * @param seller
     * @return id
     */
    public int filterBySpecificIdSeller(String seller){
        return this.filterSeller.get(seller);
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
     * @return Sorted map, seller=id
    */
    public SortedMap<String, Integer> sellerLessThan(String key) {
        return this.filterSeller.headMap(key);
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
     * @return Map.Entry, directly greater than  entry, seller=id
     */
    public Map.Entry<String, Integer> sellerGreatherThan(String key) {
        return this.filterSeller.higherEntry(key);
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

    
}
