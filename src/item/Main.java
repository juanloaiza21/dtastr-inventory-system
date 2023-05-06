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
    public Main (String seller) throws SQLException{
        ask = new AskProducts(seller);
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
        Scanner sc = new Scanner(System.in);
        LinkedList<Integer> data = new LinkedList<>();
        while (true) {
            try {
                System.out.println("Enter the product id to update stock");
                int id = sc.nextInt();
                System.out.println("Enter the new stock");
                data.add(sc.nextInt());
                conector.connect();
                conector.updateInt(data, new String[] { "stock" }, id);
                System.out.println("Updated correctly");
                break;
            } catch (InputMismatchException | SQLException e) {
                System.err.println(e.getMessage());
                sc.nextLine();
            }

        }
    }

    @Override
    public void updateProductPrice() {
        Scanner sc = new Scanner(System.in);
        LinkedList<Integer> data = new LinkedList<>();
        while (true) {
            try {
                System.out.println("Enter the product id to update stock");
                int id = sc.nextInt();
                System.out.println("Enter the new price");
                data.add(sc.nextInt());
                conector.connect();
                conector.updateInt(data, new String[] { "price" }, id);
                System.out.println("Updated correctly");
                break;
            } catch (InputMismatchException | SQLException e) {
                System.err.println(e.getMessage());
                sc.nextLine();
            }

        }
    }

    @Override
    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Enter the product id to delete");
                int id = sc.nextInt();
                conector.connect();
                conector.deleteOne(id);
                break;
            } catch (InputMismatchException  | SQLException e) {
                // TODO: handle exception
                System.err.println(e.getMessage());
                sc.nextLine();
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

    public void getAll() throws SQLException {
        conector.connect();
        conector.getAll();
    }
    
}
