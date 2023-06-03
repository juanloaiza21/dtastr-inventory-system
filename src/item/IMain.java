package item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.text.StyledEditorKit.BoldAction;

import db.Conector;

/**
 * @author Juan Loaiza
 */
public class IMain implements mainInterface {

    AskProducts ask;
    Conector conector;
    Devolution devolution;
    SellItems sell;
    FilterMenu filter;

    public IMain (String seller) throws SQLException{
        ask = new AskProducts(seller);
        devolution = new Devolution();
        sell = new SellItems();
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        conector.setTable("PRODUCTS");
        filter = new FilterMenu();
    }
    
    @Override
    public Boolean addProduct(String name, double price, int stock, String user) throws SQLException {
        try {
            conector.connect();
            if (conector.insertProduct(name, price, stock, user)){
                    return true;
                }else{
                    return false;
                }
        } catch (Exception e) {
            System.err.println(e.getMessage());            
           return false;
        }
    }

    @Override
    public Boolean updateProductStock(int id, int stock) {
        LinkedList<Integer> data = new LinkedList<>();
        data.add(stock);
        try {
            conector.connect();
            conector.updateInt(data, new String[] { "stock" }, id);
            return true;
        } catch (InputMismatchException | NullPointerException| SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateProductPrice(int id, double price) {
        LinkedList<Double> data = new LinkedList<>();
        data.add(price);
        try {
            conector.connect();
            conector.updateDouble(data, new String[] { "price" }, id);
            return true;
        } catch (InputMismatchException | SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    
    @Override
    public Boolean deleteProduct(int id) {
        try{
            conector.connect();
            conector.deleteOne(id);
            return true;
        } catch ( NullPointerException | SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean sellItem(int id, int amount) {
        return sell.selling(id, amount);
    }

    @Override
    public Boolean devolution(int id, int amount) {
        return devolution.devolution(id, amount);
    }

    @Override
    public Boolean productAsk(String name) throws SQLException {
        return ask.Asking(name);
    }

    public String[][] getAll() throws SQLException {
        conector.connect();
        ResultSet result = conector.getAll();
        return formatDataResultSet(result);
    }

    @Override
    public String[][] productAsked() throws SQLException {
        try {
            LinkedList <ItemA >items = ask.getAsk();
            for (ItemA item : items) {
                System.out.println("id: "+item.getId() + " Nombre: "+ item.getName());
            }
            return LinkedToBDArray(items);
        } catch (Exception e) {
            System.out.println("Producto no encontrado");
            return new String[0][0];
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
        if(item==null){
            return new String[0][0];
        }
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

    //public static void main(String[] args) throws SQLException {
     //   IMain main = new IMain("usuario4@example.com");
      //  String[][] aaaa = main.updateProductStock(50, 50);
     //   for (int i = 0; i < aaaa.length; i++) {
      //      System.out.println(Arrays.toString(aaaa[i]));
      //  }
    //}
}
