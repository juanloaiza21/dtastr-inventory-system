package item;

import java.util.*;
import java.sql.SQLException;
import java.util.SortedMap;
import java.util.Map.Entry;

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

    /**
     * Returns null if items does not exist else returns the item.
     * @param name
     * @return
     */
    public ItemA getItemByName(String name) {
        try {
            int index = itemFilter.filterBySpecificIdName(name);
            if (index == -1) {
                throw new NullPointerException("No se encontro el item");
            } else {
                return item.getItem(index);
            } 
        } catch (NullPointerException e ) {
            System.out.println("No se encontro el item");
            return null;
        }
    }

    /**
     * Returns null if items does not exist else returns the item.
     * @param name
     * @return
     */
    public ItemA getItemByStock(int stock) {
        try {
            int index = itemFilter.filterBySpecificStock(stock);
            if (index == -1) {
                throw new NullPointerException("No se encontro el item");
            } else {
                return item.getItem(index);
            } 
        } catch (NullPointerException e ) {
            System.out.println("No se encontro el item");
            return null;
        }
    }

    /**
     * Returns null if items does not exist else returns the item.
     * @param name
     * @return
     */
    public ItemA getItemByPrice(double price) {
        try {
            int index = itemFilter.filterBySpecificIdPrice(price);
            if (index == -1) {
                throw new NullPointerException("No se encontro el item");
            } else {
                return item.getItem(index);
            } 
        } catch (NullPointerException e ) {
            System.out.println("No se encontro el item");
            return null;
        }
    }

    /**
     * Returns null if items does not exist else returns the items.
     * @param name
     * @return
     */
    public LinkedList<ItemA> getListOfItemsByNameLessThan(String name){
        LinkedList<ItemA> items = new LinkedList<ItemA>();
        try {
            SortedMap<String, Integer> subMap = itemFilter.nameLessThan(name);
            Collection <Integer> item = subMap.values();
            for (Integer i : item) {
                items.add( this.item.getItem(i));
            }
        } catch (NullPointerException e ) {
            System.err.println("No se encontro el item");
            items = null;
        }
        return items;
    }

    public LinkedList<ItemA> getListOfItemsByStockLessThan(int stock){
        LinkedList<ItemA> items = new LinkedList<ItemA>();
        try {
            SortedMap<Integer, Integer> subMap = itemFilter.stockLessThan(stock);
            Collection <Integer> item = subMap.values();
            for (Integer i : item) {
                items.add( this.item.getItem(i));
            }
        } catch (NullPointerException e ) {
            System.err.println("No se encontro el item");
            items = null;
        }
        return items;
    }

    public LinkedList<ItemA> getListOfItemsByPriceLessThan(double price){
        LinkedList<ItemA> items = new LinkedList<ItemA>();
        try {
            SortedMap<Double, Integer> subMap = itemFilter.priceLessThan(price);
            Collection <Integer> item = subMap.values();
            for (Integer i : item) {
                items.add( this.item.getItem(i));
            }
        } catch (NullPointerException e ) {
            System.err.println("No se encontro el item");
            items = null;
        }
        return items;
    }
    
    public ItemA getItemByStockGreatherThan(int stock){
        try {
            Entry <Integer, Integer> data = itemFilter.stockGreatherThan(stock);
            return this.item.getItem(data.getValue());
        } catch (Exception e) {
            System.err.println("No se encontro el item");
        }
        return null;
    }

    public ItemA getItemByNameGreatherThan(String name){
        try {
            Entry <String, Integer> data = itemFilter.nameGreatherThan(name);
            return this.item.getItem(data.getValue());
        } catch (Exception e) {
            System.err.println("No se encontro el item");
        }
        return null;
    }


    public ItemA getItemByPriceGreatherThan(Double price){
        try {
            Entry <Double, Integer> data = itemFilter.priceGreatherThan(price);
            return this.item.getItem(data.getValue());
        } catch (Exception e) {
            System.err.println("No se encontro el item");
        }
        return null;
    }

}
