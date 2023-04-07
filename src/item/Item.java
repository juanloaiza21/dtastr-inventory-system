package item;

/**
 * @author john pastor
 * Item class implementation
 */
import java.util.*;

import db.Conector;

import java.sql.*;

public class Item {
    private int id;

    private String name;
    private double price;
    private int stock;
    Conector conector;

    public Item(int id, String name, double price, int stock) throws SQLException {
        this.name = name;
        this.id = id;
        this.price = price;
        this.stock = stock;
        //conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "alejo2425");
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        conector.setTable("PRODUCTS");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // get an item from the linked list
    public Item getItem(LinkedList<Item> list, String name) {
        for (Item item : list) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public LinkedList<Item> getItems() throws SQLException {
        conector.connect();

        ResultSet result = conector.getAllResultSet();

        LinkedList<Item> itemsList = new LinkedList<>();
        while (result.next()) { // result.next() = true while there are more items,

            // to create an object "Item" with the data and add it to the list
            int id = result.getInt("id"); // "id" or 1
            String name = result.getString("name"); // "name" or 2
            double price = result.getDouble("price"); // "price " or 3
            int stock = result.getInt("stock"); // "stock" or 4

            itemsList.add(new Item(id, name, price, stock));

        }
        result.close();
        return itemsList;
    }
}
