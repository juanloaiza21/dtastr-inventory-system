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
    public Main (){
        ask = new AskProducts();
        devolution = new Devolution();
        sell = new SellItems();
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        conector.setTable("PRODUCTS");
    }
    
    @Override
    public void addProduct() throws SQLException {
        ask.Asking();
    }

    @Override
    public void updateProductStock() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'uodateProductStock'");
    }

    @Override
    public void updateProductPrice() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProductPrice'");
    }

    @Override
    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the product id to delete");
        while (true) {
            try {
                int id = sc.nextInt();
                conector.connect();
                conector.deleteOne(id);
                System.out.println("Deleted correctly");
                break;
            } catch (InputMismatchException  | SQLException e) {
                // TODO: handle exception
                System.err.println(e.getMessage());
            } 
        }
    }

    @Override
    public void sellItem() {
        sell.Selling();
    }

    @Override
    public void devolution() {
        devolution.devolution();
    }

    @Override
    public void productAsk() throws SQLException {
        ask.Asking();
    }
    
}
