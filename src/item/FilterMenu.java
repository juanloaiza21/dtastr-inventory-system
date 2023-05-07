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

    public void getItemByName(String name) {
        try {
            int index = itemFilter.filterBySpecificIdName(name);
            if (index == -1) {
                System.out.println("No se encontro el item");
            } else {
                System.out.println("id: "+item.getItem(index).id+" Nombre: "+ item.getItem(index).name+ " precio: "+item.getItem(index).price+ " Stock:  "+item.getItem(index).stock + " Vendedor: " + item.getItem(index).seller);
            }   
        } catch (NullPointerException e ) {
            System.out.println("No se encontro el item");
        }
    }

    public void getItemByStock(int stock) {
        try {
            int index = itemFilter.filterBySpecificStock(stock);
            if (index == -1) {
                System.out.println("No se encontro el item");
            } else {
                System.out.println("id: "+item.getItem(index).id+" Nombre: "+ item.getItem(index).name+ " precio: "+item.getItem(index).price+ " Stock:  "+item.getItem(index).stock + " Vendedor: " + item.getItem(index).seller);
            }   
        } catch (NullPointerException e ) {
            System.out.println("No se encontro el item");
        }
    }

    public void getItemByPrice(double price) {
        try {
            int index = itemFilter.filterBySpecificIdPrice(price);
            if (index == -1) {
                System.out.println("No se encontro el item");
            } else {
                System.out.println("id: "+item.getItem(index).id+" Nombre: "+ item.getItem(index).name+ " precio: "+item.getItem(index).price+ " Stock:  "+item.getItem(index).stock + " Vendedor: " + item.getItem(index).seller);
            }   
        } catch (NullPointerException e ) {
            System.out.println("No se encontro el item");
        }
    }

    
    public void getListOfItemsByNameLessThan(String name){
        try {
            SortedMap<String, Integer> subMap = itemFilter.nameLessThan(name);
            Collection <Integer> item = subMap.values();
            for (Integer i : item) {
                System.out.println("id: "+this.item.getItem(i).id+" Nombre: "+ this.item.getItem(i).name+ " precio: "+this.item.getItem(i).price+ " Stock:  "+this.item.getItem(i).stock + " Vendedor: " + this.item.getItem(i).seller);
            }
        } catch (NullPointerException e ) {
            System.out.println("No se encontro el item");
        }
    }

    public void getListOfItemsByStockLessThan(int stock){
        try {
            SortedMap<Integer, Integer> subMap = itemFilter.stockLessThan(stock);
            Collection <Integer> item = subMap.values();
            for (Integer i : item) {
                System.out.println("id: "+this.item.getItem(i).id+" Nombre: "+ this.item.getItem(i).name+ " precio: "+this.item.getItem(i).price+ " Stock:  "+this.item.getItem(i).stock + " Vendedor: " + this.item.getItem(i).seller);
            }
        } catch (NullPointerException e ) {
            System.out.println("No se encontro el item");
        }
    }

    public void getListOfItemsByPriceLessThan(double price){
        try {
            SortedMap<Double, Integer> subMap = itemFilter.priceLessThan(price);
            Collection <Integer> item = subMap.values();
            for (Integer i : item) {
                System.out.println("id: "+this.item.getItem(i).id+" Nombre: "+ this.item.getItem(i).name+ " precio: "+this.item.getItem(i).price+ " Stock:  "+this.item.getItem(i).stock + " Vendedor: " + this.item.getItem(i).seller);
            }
        } catch (NullPointerException e ) {
            System.out.println("No se encontro el item");
        }
    }
    
    public void getItemByStockGreatherThan(int stock){
        try {
            Entry <Integer, Integer> data = itemFilter.stockGreatherThan(stock);
            System.out.println("id: "+this.item.getItem(data.getValue()).id+" Nombre: "+ this.item.getItem(data.getValue()).name+ " precio: "+this.item.getItem(data.getValue()).price+ " Stock:  "+this.item.getItem(data.getValue()).stock + " Vendedor: " + this.item.getItem(data.getValue()).seller);
        } catch (Exception e) {
            System.out.println("No se encontro el item");
        }
    }

    public void getItemByNameGreatherThan(String name){
        try {
            Entry <String, Integer> data = itemFilter.nameGreatherThan(name);
            System.out.println("id: "+this.item.getItem(data.getValue()).id+" Nombre: "+ this.item.getItem(data.getValue()).name+ " precio: "+this.item.getItem(data.getValue()).price+ " Stock:  "+this.item.getItem(data.getValue()).stock + " Vendedor: " + this.item.getItem(data.getValue()).seller);
        } catch (Exception e) {
            System.out.println("No se encontro el item");
        }
    }

    public void getItemByPriceGreatherThan(Double price){
        try {
            Entry <Double, Integer> data = itemFilter.priceGreatherThan(price);
            System.out.println("id: "+this.item.getItem(data.getValue()).id+" Nombre: "+ this.item.getItem(data.getValue()).name+ " precio: "+this.item.getItem(data.getValue()).price+ " Stock:  "+this.item.getItem(data.getValue()).stock + " Vendedor: " + this.item.getItem(data.getValue()).seller);
        } catch (Exception e) {
            System.out.println("No se encontro el item");
        }
    }

    public static void main(String[] args) throws SQLException {
        FilterMenu filterMenu = new FilterMenu();
        filterMenu.getItemByStockGreatherThan(999);
    }
}
