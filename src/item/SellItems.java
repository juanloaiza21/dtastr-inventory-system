package item;

import java.sql.*;
import java.util.*;
import db.Conector;

/**
 * @author john pastor
 *         Class SellItems
 */

public class SellItems {
    private Conector conector;

    public SellItems() {
       // conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "alejo2425");
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        conector.setTable("PRODUCTS");
    }

    public void Selling() {

        Scanner scan = new Scanner(System.in);

        try {

            Item it = new Item(0, null, 0, 0);
            LinkedList<Item> itemsList = it.getItems();
            System.out.println("Which item do u wanna buy: ");
            String name = scan.nextLine();
            System.out.println("How many: ");
            int Amount = scan.nextInt();

            Item itemToSell = it.getItem(itemsList, name);
            if (itemToSell != null) {
                if (itemToSell.getStock() >= Amount) {
                    itemToSell.setStock(itemToSell.getStock() - Amount);
                    System.out.println("Product selled: " + itemToSell.getName());
                    System.out.println("Thanks for your purchase");

                    updateStock(itemToSell);
                } else {
                    System.out.println("There are not enough stock! ");

                }
            } else {
                System.out.println("Item not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scan.close();
    }

    private void updateStock(Item itemToSell) throws SQLException {
        conector.connect();
        LinkedList<Integer> data = new LinkedList<>();
        data.add(itemToSell.getStock());
        conector.updateInt(data, new String[] { "stock" }, itemToSell.getId());
    }

    // public static void main(String[] args) throws SQLException {
    //     String url = "jdbc:mysql://localhost:3306/DTAPROYECT";
    //     String user = "root";
    //     String password = "alejo2425";

    //     // SellItems sell = new SellItems();
    //     // sell.Selling();
    //     Conector conector = new Conector(url, user, password);
    //     conector.setTable("PRODUCTS");
    //     conector.connect();
    //     conector.getAll();
    // }

}
