package item;

import java.sql.*;
import java.util.*;
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

    public AskProducts(String name) {
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "alejo2425");
        // conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root",
        // "PCTdkx58");
        conector.setTable("USERS");
        seller = name;
    }

    public void Asking() throws SQLException {
        Scanner scan = new Scanner(System.in);
        Queue<Item> data = new LinkedList<>();
        Boolean controller = true;
        while (controller) {
            try {
                System.out.println("do u wanna ask a product: 1. yes, 2. no");
                int flag = scan.nextInt();
                if (flag == 2) {
                    controller = false;
                } else if (flag == 1) {
                    System.out.println("name:");
                    String name = scan.next();
                    scan.nextLine();
                    System.out.println("how much stock: ");
                    int stock = scan.nextInt();
                    data.add(new Item(0, name, 0, stock, seller));
                    adding(data);
                    scan.nextLine();
                    controller = false;
                } else {
                    System.out.println("Incorrect Option");
                }
            } catch (InputMismatchException e) {
                // TODO: handle exception
                System.err.println("Must be int");
                scan.nextLine();
            }
        }

        scan.close();
    }

    private void adding(Queue<Item> data) throws SQLException {
        conector.connect();
        for (Item item : data) {
            String name = item.getName();
            double price = item.getPrice();
            int stock = item.getStock();
            conector.insertProduct(name, price, stock);

        }
        data.clear();

    }

    private LinkedList<ItemA> chargeData() {
        LinkedList<ItemA> items = new LinkedList<>();
        try {
            conector.connect();
            ResultSet result = conector.getAllResultSet();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                double price = result.getDouble("price");
                int stock = result.getInt("stock");
                String type = result.getString("seller");
                items.add(new Item(id, name, price, stock, type));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return items;

    }

    public LinkedList<ItemA> getItems() throws SQLException {
        LinkedList<ItemA> items = new LinkedList<>();
        items = chargeData();
        return items;
    }


}