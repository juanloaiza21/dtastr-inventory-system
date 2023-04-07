package item;

import java.sql.*;
import java.util.*;
import db.Conector;

/**
 * 
 */
public class Devolution {
    private Conector conector;

    public Devolution() {
        //conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "alejo2425");
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        conector.setTable("PRODUCTS");
    }

    public void devolution() {
        Scanner scan = new Scanner(System.in);
        try {

            Item it = new Item(0, null, 0, 0);
            LinkedList<Item> itemsList = it.getItems();
            System.out.println("Which item do u wanna return: ");
            String name = scan.nextLine();

            Item itemToReturn = it.getItem(itemsList, name);
            if (itemToReturn != null) {
                System.out.println("How many: ");
                int Amount = scan.nextInt();

                itemToReturn.setStock(itemToReturn.getStock() + Amount);
                System.out.println("Product returned: " + itemToReturn.getName());
                System.out.println("Thanks for your visit");

                updateStock(itemToReturn);

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
    public static void main(String[] args) {
        Devolution flag = new Devolution();
        flag.devolution();
    }

}
