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
}
