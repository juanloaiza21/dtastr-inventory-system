package Selling;

import java.util.*;
import java.sql.*;

public class Item {
    private int id;

    private String name;
    private double price;
    private int stock;

    public Item(int id, String name, double price, int stock) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.stock = stock;

    }
    
    //getters and setters

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
    
    //get an item from the linked list
    public static Item getItem(LinkedList<Item> list, String name) {
        for (Item item : list) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    // get items from the database
    public static LinkedList<Item> getItems(Connection connection) throws SQLException {
        String query = "SELECT * FROM PRODUCTS";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

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

        statement.close();
        return itemsList;
    }

}
