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
    void addProduct() throws SQLException;
    /**
     * Update stock trought console
     */
    ItemA updateProductStock(int id, int stock);

    /**
     * Update price trought console
     */
    ItemA updateProductPrice(int id, int price);

    /**
     * Delete product
     * @param id
     */
    ItemA deleteProduct(int id);

    ItemA sellItem(int id, int amount);

    ItemA devolution(int id, int amount);

    void productAsk() throws SQLException;

    void productAsked() throws SQLException;

    ItemA getitemByName(String name);

    ItemA getItemByStock(int stock);

    ItemA getItemByPrice(double price);

    LinkedList<ItemA> itemBynameLess(String seller);

    LinkedList<ItemA> getItemByStockLess(int stock);

    LinkedList<ItemA> getItemByPriceLess(double price);

    ItemA itemBynameGreater(String name);

    ItemA getItemByPriceGreater(double price);

    ItemA getItemByStockGreater(int stock);


}
