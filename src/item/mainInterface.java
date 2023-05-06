package item;

import java.sql.SQLException;

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
    void updateProductStock();
    /**
     * Update price trought console
     */
    void updateProductPrice();
    /**
     * Delete product trought console
     */
    void deleteProduct();

    void sellItem();

    void devolution();

    void productAsk() throws SQLException;

    void productAsked() throws SQLException;

    void getitemByName(String name);

    void getItemByStock(int stock);

    void getItemByPrice(double price);

    void itemBynameLess(String seller);

    void getItemByStockLess(int stock);

    void getItemByPriceLess(double price);

    void itemBynameGreater(String name);

    void getItemByPriceGreater(double price);

    void getItemByStockGreater(int stock);


}
