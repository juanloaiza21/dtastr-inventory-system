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

    /**
     * @param id
     * @param amount
     * @return ItemA updated if return is null means somenthing went wrong
     */
    public ItemA devolution(int id, int amount) {
            try {
                Item it = new Item(0, null, 0, 0,"");
                it.getItems();
                ItemA itemToReturn = it.getItem(id);
                if (itemToReturn != null) {
                    itemToReturn.setStock(itemToReturn.getStock() + amount);
                    updateStock(itemToReturn);
                    return itemToReturn;
                } else {
                    throw new InputMismatchException("The item does not exist! ");
            }
        } catch (InputMismatchException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;   
    }

    private void updateStock(ItemA itemToSell) throws SQLException {
        conector.connect();
        LinkedList<Integer> data = new LinkedList<>();
        data.add(itemToSell.getStock());
        conector.updateInt(data, new String[] { "stock" }, itemToSell.getId());
    }

}
