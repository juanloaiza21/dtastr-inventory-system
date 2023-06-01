package item;

import java.sql.*;
import java.util.*;

import javax.naming.LimitExceededException;

import db.Conector;

/**
 * Class AskProducts
 * 
 * @author john pastor
 *
 */

public class AskProducts {
    private Conector conector;
    private String seller;
    Item item;

    public AskProducts(String name) throws SQLException {
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        // conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root",
        // "PCTdkx58");
        conector.setTable("ASKPRODUCTS");
        seller = name;
        item = new Item();
    }

    public Item Asking(String name) throws SQLException {
        Queue<Item> data = new LinkedList<>();
        Boolean controller = true;

        while (controller) {
            try {
                data.add(new Item(0, name, 0, 0, seller));
                adding(data);
                controller = false;

            } catch (InputMismatchException | SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        if (!data.isEmpty()) {
            return data.poll();
        } else {
            return null; // Devuelve null si no se agregó ningún elemento a la cola
        }
    }

    private void adding(Queue<Item> data) throws SQLException {
        conector.connect();
        for (Item item : data) {
            String name = item.getName();
            String seller = item.getSeller();
            conector.insertAsk(name, seller);
        }
        data.clear();

    }

    public LinkedList<ItemA> getItems() throws SQLException {
        return item.getItems();
    }

    public LinkedList<ItemA> getAsk() throws SQLException {
        LinkedList<ItemA> items = new LinkedList<>();
        try {
            conector.connect();
            ResultSet result = conector.getAll();
            while (result.next()) {
                int id = result.getInt("id");
                String name2 = result.getString("nombre");
                String seller = result.getString("askedBy");
                items.add(new ItemA(id, name2, 0, 0, seller));
            }
            conector.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return items;
    }

}