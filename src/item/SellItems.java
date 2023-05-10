package item;

import java.sql.*;
import java.util.*;
import db.Conector;

/**
 * @author john pastor
 *         Class SellItems
 */

public class SellItems {
    private ItemFilter filter;
    private Conector conector;
    private Item item;
    private ItemA[] items;

    public SellItems() throws SQLException {
       // conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "alejo2425");
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        conector.setTable("PRODUCTS");
        item = new Item();
        items = new ItemA[item.getItems().size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = item.getItems().get(i);
        }
        filter = new ItemFilter(items);
    }

    public void Selling() {

        Scanner scan = new Scanner(System.in);
        Boolean controller = true;
        while (controller) {
            try {
                System.out.println("id of the item do u wanna buy: ");
                int name = scan.nextInt();
                System.out.println("How many: ");
                int Amount = scan.nextInt();
                ItemA itemToSell = item.getItem(name);
                if (itemToSell != null) {
                    if (itemToSell.getStock() >= Amount) {
                        itemToSell.setStock(itemToSell.getStock() - Amount);
                        System.out.println("Product selled: " + itemToSell.getName());
                        System.out.println("Thanks for your purchase");
                        updateStock(itemToSell);
                        controller = false;
                    } else {
                        System.out.println("There are not enough stock! ");
                        controller = false;
                    }
                } else {
                    System.out.println("Item not Found!");
                    controller = false;
                }
            } catch (InputMismatchException | SQLException e) {
                System.err.println(e.getMessage());
                scan.nextLine();
            }
        }
    }

    private void updateStock(ItemA itemToSell) throws SQLException {
        conector.connect();
        LinkedList<Integer> data = new LinkedList<>();
        data.add(itemToSell.getStock());
        conector.updateInt(data, new String[] { "stock" }, itemToSell.getId());
    }

    public static void main(String[] args) {
        try {
            SellItems sell = new SellItems();
            sell.Selling();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
