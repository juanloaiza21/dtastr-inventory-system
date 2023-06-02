package item;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author Juan Loaiza
 */

public interface mainInterface {

    /**
     * Add a prduct trought console
     */
    void addProduct(String name) throws SQLException;
    /**
     * Update stock trought console
     */
    String[][] updateProductStock(int id, int stock);

    /**
     * Update price trought console
     */
    String[][] updateProductPrice(int id, int price);

    /**
     * Delete product
     * @param id
     */
    String[][] deleteProduct(int id);

    String[][] sellItem(int id, int amount);

    String[][] devolution(int id, int amount);

    void productAsk(String name) throws SQLException;

    void productAsked() throws SQLException;

    String[][] getitemByName(String name);

    String[][] getItemByStock(int stock);

    String[][] getItemByPrice(double price);

    String[][] itemBynameLess(String seller);

    String[][] getItemByStockLess(int stock);

    String[][] getItemByPriceLess(double price);

    String[][] itemBynameGreater(String name);

    String[][] getItemByPriceGreater(double price);

    String[][] getItemByStockGreater(int stock);


}
