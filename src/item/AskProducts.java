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
    Item item;

    public AskProducts(String name) throws SQLException {
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        // conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root",
        // "PCTdkx58");
        conector.setTable("ASKPRODUCTS");
        seller = name;
        item = new Item();
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
                    data.add(new Item(0, name, 0, 0, seller));
                    adding(data);
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
            ResultSet result = conector.getAllResultSet();
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

    public static void main(String[] args) throws SQLException {
        AskProducts askProducts = new AskProducts("Juan");
        askProducts.Asking();
    }
}