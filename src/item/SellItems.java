package item;

import java.sql.*;
import java.util.*;

import javax.naming.LimitExceededException;

import db.Conector;

/**
 * @author john pastor
 *         Class SellItems
 * @update 26/05.2023
 * @updatedBy Juan Loaiza
 */

public class SellItems {
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
    }
    /**
     * If items is returned means succesfully done.
     * @param id
     * @param amount
     * @return ItemA
     */
   
     public Boolean selling(int id, int amount) {
            try {
                
                ItemA itemToSell = item.getItem(id);
                if (itemToSell != null) {
                    if (itemToSell.getStock() >= amount) {
                        itemToSell.setStock(itemToSell.getStock() - amount);
                        updateStock(itemToSell);
                        return true;
                    } else {
                        throw new LimitExceededException("There are not enough stock! ");
                    }
                } else {
                    throw new InputMismatchException("The item does not exist! ");
                }
            } catch (InputMismatchException | SQLException | LimitExceededException e) {
                System.err.println(e.getMessage());
                return false;
            }
    }

    private void updateStock(ItemA itemToSell) throws SQLException {
        conector.connect();
        LinkedList<Integer> data = new LinkedList<>();
        data.add(itemToSell.getStock());
        conector.updateInt(data, new String[] { "stock" }, itemToSell.getId());
    }

}
