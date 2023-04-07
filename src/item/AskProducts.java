package item;

import java.sql.*;
import java.util.*;
import db.Conector;

/**
 * Class AskProducts
 * @author john pastor
 *
 */

public class AskProducts {
    private Conector conector;

    public AskProducts() {
        //conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "alejo2425");
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        conector.setTable("PRODUCTS");
    }

    public void Asking() throws SQLException {
        Scanner scan = new Scanner(System.in);
        Queue<Item> data = new LinkedList<>();
        while (true) {
            System.out.println("do u wanna ask a product: ");
            String flag = scan.nextLine().toLowerCase();

            if (flag.equals("no")) {
                break;
            } else {
                System.out.println("name:");
                String name = scan.next();
                scan.nextLine();
                System.out.println("how many items: ");
                int stock = scan.nextInt();
                data.add(new Item(0, name, 0, stock));
                scan.nextLine();
            }
        }
        adding(data);
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

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/DTAPROYECT";
        String user = "root";
        String password = "PCTdkx58";

        Conector conector = new Conector(url, user, password);
        conector.connect();
        AskProducts flag = new AskProducts();
        flag.Asking();

    }

}
