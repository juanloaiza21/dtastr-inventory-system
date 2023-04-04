package Selling;

import java.sql.*;
import java.util.*;
/**
*@author john pastor
*SellItems class
**/

public class SellItems {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Connect to DataBase
        String url = "jdbc:mysql://localhost:3306/DTAPROYECT";
        String user = "root";
        String password = "alejo2425";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
            LinkedList<Item> itemsList = Item.getItems(connection); // insert items into a linkedlist

            System.out.println("Which item do u wanna buy: ");
            String name = scan.next().toLowerCase();
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
                System.out.println("Item not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // this closes the connection to the database
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

    public static void updateStock(Connection connection, Item itemToSell) throws SQLException {
        // statement for sql to update
        String updateStock = "UPDATE PRODUCTS SET stock =" + itemToSell.getStock() + " WHERE id =" + itemToSell.getId();
        Statement updStatement = connection.createStatement();
        updStatement.executeUpdate(updateStock); // execute the statement

        updStatement.close();

    }

}
