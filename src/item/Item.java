package item;
import java.util.*;

import db.Conector;

import java.sql.*;

/**
 * @author john pastor
 * @version 1.0
 * updated 1.0 [28/04/2023]
 * @Updated_by Juan Loaiza
 */
public class Item extends ItemA{

    LinkedList<ItemA> items;
    
    public Item() throws SQLException{
        super(0, "", 0.0, 0, "");
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        conector.setTable("PRODUCTS");
    }

    public Item(int id, String name, double price, int stock, String seller ) throws SQLException {
        super(id, name, price, stock, seller);
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        conector.setTable("PRODUCTS");
        items = null;
    }

    protected LinkedList<ItemA> chargeData() {
        LinkedList<ItemA> items = new LinkedList<>();
        try {
            conector.connect();
            ResultSet result = conector.getAll();
            while (result.next()) { // result.next() = true while there are more items,
                // to create an object "Item" with the data and add it to the list
                int id = result.getInt("id"); // "id" or 1
                String name = result.getString("nombre"); // "name" or 2
                double price = result.getDouble("price"); // "price " or 3
                int stock = result.getInt("stock"); // "stock" or 4
                String seller = result.getString("seller"); // "seller" or 5
                items.add(new Item(id, name, price, stock, seller));
            }
            conector.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return items;
    }
    
    // get an item from the linked list
    public ItemA getItem(int id) {
            for (ItemA item : items) {
                if (item.getId() == id) {
                    return item;
                }
            }
            return null;
    }

    public LinkedList<ItemA> getItems() throws SQLException {
        return items = chargeData();
    }

    public static void main(String[] args) {
        try {
            Item item = new Item();
            LinkedList<ItemA> items = item.getItems();
            for (ItemA itemA : items) {
                System.out.println(itemA.getId() + " " + itemA.getName() + " " + itemA.getPrice() + " " + itemA.getStock());
            }
            try {
                System.out.println(item.getItem(1).getName());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
