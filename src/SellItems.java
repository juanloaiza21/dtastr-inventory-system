package Selling;

import java.sql.*;
import java.util.*;
/**
 * @author john pastor
 * Class SellItems 
 */

public class SellItems {
    private String url, user, password;
    private Connection connection;

    public void Selling() {

        Scanner scan = new Scanner(System.in);
        url = "jdbc:mysql://localhost:3306/DTAPROYECT";
        user = "root";
        password = "alejo2425";
        connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            LinkedList<Item> itemsList = Item.getItems(connection);

            System.out.println("Which item do u wanna buy: ");
            String name = scan.nextLine();
            System.out.println("How many: ");
            int Amount = scan.nextInt();

            Item itemToSell = Item.getItem(itemsList, name);
            if (itemToSell != null) {
                if (itemToSell.getStock() >= Amount) {
                    itemToSell.setStock(itemToSell.getStock() - Amount);
                    System.out.println("Product selled: " + itemToSell.getName());
                    System.out.println("Thanks for your purchase");

                    updateStock(connection, itemToSell);
                } else {
                    System.out.println("There are not enough stock! ");

                }
            } else {
                System.out.println("Item not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        scan.close();
    }

    private void updateStock(Connection connection, Item itemToSell) throws SQLException {
        String updStock = "UPDATE PRODUCTS SET stock =" + itemToSell.getStock() + " WHERE id=" + itemToSell.getId();
        Statement updStatement = connection.createStatement();
        updStatement.executeUpdate(updStock);

        updStatement.close();
    }

}
