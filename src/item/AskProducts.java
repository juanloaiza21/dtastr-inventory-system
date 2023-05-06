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

    public AskProducts() {
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "alejo2425");
        // conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root",
        // "PCTdkx58");
        conector.setTable("USERS");
    }
    
    public void Asking(String vendedor) throws SQLException {
        Scanner scan = new Scanner(System.in);
        Queue<Item> data = new LinkedList<>();
        Boolean controller = true;
        while (controller) {
            try {   System.out.println("name:");
                    String name = scan.next();
                    scan.nextLine();
                    System.out.println("how much stock: ");
                    int stock = scan.nextInt();
                    data.add(new Item(0, name, 0, stock,vendedor));
                    adding(data);
                    scan.nextLine();
                    controller = false;
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
            String name = item.getVendedorName();
            conector.insertProduct(name, price, stock,vendedor);

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
                items.add(new Item(id, name, price, stock));
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

    public static void main(String[] args) throws SQLException {
        AskProducts a = new AskProducts();
        // a.Asking();
        LinkedList<ItemA> list = a.getItems();
        System.out.println(list.get(0).name);
    }

}