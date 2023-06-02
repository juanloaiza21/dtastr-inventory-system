package item;
import java.sql.ResultSet;
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
    public void addProduct(String name) throws SQLException {
        ask.Asking(name);
    }

    @Override
    public String[][] updateProductStock(int id, int stock) {
        LinkedList<Integer> data = new LinkedList<>();
        data.add(id);
        data.add(stock);
        try {
            Item it = new Item(0, null, 0, 0,"");
            conector.connect();
            conector.updateInt(data, new String[] { "stock" }, id);
            it.getItems();
            it.getItem(id).stock = stock;
            return formatDataOneData(it.getItem(id));
        } catch (InputMismatchException | NullPointerException| SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String[][] updateProductPrice(int id, int price) {
        LinkedList<Integer> data = new LinkedList<>();
        data.add(id);
        data.add(price);
        try {
            Item it = new Item(0, null, 0, 0,"");
            conector.connect();
            conector.updateInt(data, new String[] { "price" }, id);
            it.getItems();
            it.getItem(id).price = price;
            return formatDataOneData(it.getItem(id));
        } catch (InputMismatchException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String[][] deleteProduct(int id) {
        try{
            Item it = new Item(0, null, 0, 0,"");
            conector.connect();
            conector.deleteOne(id);
            it.getItems();
            return formatDataOneData(it.getItem(id));
        } catch ( NullPointerException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return null; 
    }

    @Override
    public String[][] sellItem(int id, int amount) {
        ItemA data = sell.selling(id, amount);
        return formatDataOneData(data);
    }

    @Override
    public String[][] devolution(int id, int amount) {
        ItemA data = devolution.devolution(id, amount);
        return formatDataOneData(data);
    }

    @Override
    public void productAsk(String name) throws SQLException {
        ask.Asking(name);
    }

    public String[][] getAll() throws SQLException {
        conector.connect();
        ResultSet result = conector.getAll();
        return formatDataResultSet(result);
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
    public String[][] getItemByStock(int stock) {
        ItemA item = filter.getItemByStock(stock);
        return formatDataOneData(item);
    }

    @Override
    public String[][] getItemByPrice(double price) {
        ItemA item = filter.getItemByPrice(price);
        return formatDataOneData(item);
    }

    @Override
    public String[][] getitemByName(String name) {
        ItemA item = filter.getItemByName(name);
        return formatDataOneData(item);
    }

    @Override
    public String[][] itemBynameLess(String seller) {
        return LinkedToBDArray(filter.getListOfItemsByNameLessThan(seller));
    }

    @Override
    public String[][] getItemByStockLess(int stock) {
        return LinkedToBDArray(filter.getListOfItemsByStockLessThan(stock));
    }

    @Override
    public String[][] getItemByPriceLess(double price) {
        return LinkedToBDArray(filter.getListOfItemsByPriceLessThan(price));
    }

    @Override
    public String[][] itemBynameGreater(String name) {
        return formatDataOneData(filter.getItemByNameGreatherThan(name));
    }

    @Override
    public String[][] getItemByPriceGreater(double price) {
        return  formatDataOneData(filter.getItemByPriceGreatherThan(price));
    }

    @Override
    public String[][] getItemByStockGreater(int stock) {
        return  formatDataOneData(filter.getItemByStockGreatherThan(stock));
    }

    private String[][] formatDataResultSet(ResultSet result) throws SQLException{
        LinkedList <String[]> data = new LinkedList<>();
        int i = 0;
        while (result.next()) {
            System.out.println(result.getInt("id") + "       " + result.getString("nombre") + "       "
            + result.getDouble("price") + "         " + result.getInt("stock"));
            data.add(new String[] {Integer.toString(result.getInt("id")), result.getString("nombre"), Double.toString(result.getDouble("price")), Integer.toString(result.getInt("stock")), result.getString("seller")});
            i++;
        }
        String[][] items = new String[i][5];
        for (int j = 0; j < items.length; j++) {
            items[j] = data.get(j);
        }
        return items;
    }

    private String[][] formatDataOneData(ItemA item){
        String[][] data = new String[1][5];
        String [] datata = new String[] {Integer.toString(item.getId()), item.getName(), Double.toString(item.getPrice()), Integer.toString(item.getStock()), item.getSeller()};
        data[0] = datata;
        return data;
    }

    private String[][] LinkedToBDArray(LinkedList<ItemA> data){
        String[][] items = new String[data.size()][5];
        for (int i = 0; i < items.length; i++) {
            items[i] = new String[] {Integer.toString(data.get(i).getId()), data.get(i).getName(), Double.toString(data.get(i).getPrice()), Integer.toString(data.get(i).getStock()), data.get(i).getSeller()};
        }
        return items;
    }

    public static void main(String[] args) throws SQLException {
        Main main = new Main("usuario4@example.com");
        String[][] aaaa = main.updateProductStock(50, 50);
        for (int i = 0; i < aaaa.length; i++) {
            System.out.println(Arrays.toString(aaaa[i]));
        }
    }
}
