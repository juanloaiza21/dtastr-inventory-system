package item;
import java.sql.SQLException;
import java.util.*;
import db.Conector;

/**
 * @author Juan Loaiza
 */
public class Main implements mainInterface {

    AskProducts ask;
    Conector conector;
    Devolution devolution;
    SellItems sell;
    FilterMenu filter;

    public Main (String seller) throws SQLException{
        ask = new AskProducts(seller);
        devolution = new Devolution();
        sell = new SellItems();
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        conector.setTable("PRODUCTS");
        filter = new FilterMenu();
    }
    
    @Override
    public void addProduct() throws SQLException {
        ask.Asking();
    }

    @Override
    public ItemA updateProductStock(int id, int stock) {
        LinkedList<Integer> data = new LinkedList<>();
        data.add(id);
        data.add(stock);
        try {
            Item it = new Item(0, null, 0, 0,"");
            conector.connect();
            conector.updateInt(data, new String[] { "stock" }, id);
            System.out.println("Updated correctly");
            it.getItem(id).stock = stock;
            return it.getItem(id);
        } catch (InputMismatchException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ItemA updateProductPrice(int id, int price) {
        LinkedList<Integer> data = new LinkedList<>();
        data.add(id);
        data.add(price);
        try {
            Item it = new Item(0, null, 0, 0,"");
            conector.connect();
            conector.updateInt(data, new String[] { "price" }, id);
            it.getItem(id).price = price;
            return it.getItem(id);
        } catch (InputMismatchException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ItemA deleteProduct(int id) {
        try{
            Item it = new Item(0, null, 0, 0,"");
            conector.connect();
            conector.deleteOne(id);
            return it.getItem(id);
        } catch ( NullPointerException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return null; 
    }

    @Override
    public ItemA sellItem(int id, int amount) {
        return sell.selling(id, amount);
    }

    @Override
    public ItemA devolution(int id, int amount) {
        return devolution.devolution(id, amount);
    }

    @Override
    public void productAsk() throws SQLException {
        ask.Asking();
    }

    public void getAll() throws SQLException {
        conector.connect();
        conector.getAll();
    }

    @Override
    public void productAsked() throws SQLException {
        try {
            LinkedList <ItemA >items = ask.getAsk();
            for (ItemA item : items) {
                System.out.println("id: "+item.getId() + " Nombre: "+ item.getName());
            }
        } catch (Exception e) {
            System.out.println("Producto no encontrado");
        }
    }

    @Override
    public ItemA getItemByStock(int stock) {
        return filter.getItemByStock(stock);
    }

    @Override
    public ItemA getItemByPrice(double price) {
        return filter.getItemByPrice(price);
    }

    @Override
    public ItemA getitemByName(String name) {
        return filter.getItemByName(name);
    }

    @Override
    public LinkedList<ItemA> itemBynameLess(String seller) {
        return filter.getListOfItemsByNameLessThan(seller);
    }

    @Override
    public LinkedList<ItemA> getItemByStockLess(int stock) {
        return filter.getListOfItemsByStockLessThan(stock);
    }

    @Override
    public LinkedList<ItemA> getItemByPriceLess(double price) {
        return filter.getListOfItemsByPriceLessThan(price);
    }

    @Override
    public ItemA itemBynameGreater(String name) {
        return filter.getItemByNameGreatherThan(name);
    }

    @Override
    public ItemA getItemByPriceGreater(double price) {
        return filter.getItemByPriceGreatherThan(price);
    }

    @Override
    public ItemA getItemByStockGreater(int stock) {
        return filter.getItemByStockGreatherThan(stock);
    }
    
}
