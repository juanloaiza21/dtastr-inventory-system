package item;

import java.sql.SQLException;

/**
 * @author Juan Loaiza
 */

public interface mainInterface {

    /**
     * Add a prduct trought console
     */
    public Boolean addProduct(String name, double price, int stock ,String user) throws SQLException;
    /**
     * Update stock trought console
     */
    Boolean updateProductStock(int id, int stock);

    /**
     * Update price trought console
     */
    Boolean updateProductPrice(int id, double price);

    /**
     * Delete product
     * @param id
     */
    Boolean deleteProduct(int id);

    Boolean sellItem(int id, int amount);

    Boolean devolution(int id, int amount);

    Boolean productAsk(String name) throws SQLException;

    String[][] productAsked() throws SQLException;

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
