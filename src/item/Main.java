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
    public void addProduct(String emailVendedor) throws SQLException {
        ask.Asking(emailVendedor);
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
                updateProductStockSQL(data,id)
                System.out.println("Updated correctly");
                break;
            } catch (InputMismatchException | SQLException e) {
                System.err.println(e.getMessage());
                sc.nextLine();
            }

        }
    }
    public void updateProductStockSQL(LinkedList<Integer> data,int id) {
        try {
            conector.connect();
            conector.updateInt(data, new String[] { "stock" }, id);

        } catch (InputMismatchException | SQLException e) {
            System.err.println(e.getMessage());
            sc.nextLine();
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
        Scanner scan = new Scanner(System.in);
        Boolean controller = true;
        while (controller) {
            try {
                Item it = new Item(0, null, 0, 0);
                LinkedList<Item> itemsList = it.getItems();
                System.out.println("Name of the item do u wanna buy: ");
                String name = scan.nextLine();
                System.out.println("How many: ");
                int Amount = scan.nextInt();    
                Item itemToSell = it.getItem(itemsList, name);
                if (itemToSell != null) {
                    if (itemToSell.getStock() >= Amount) {
                        itemToSell.setStock(itemToSell.getStock() - Amount);
                        updateProductStockSQL(data,itemToSell.getId())
                        System.out.println("Product selled: " + itemToSell.getName());
                        System.out.println("Thanks for your purchase");
                        controller = false;
                    } else {
                        System.out.println("There are not enough stock! ");
                        controller = false;
                    }
                } else {
                    System.out.println("Item not Found!");
                    controller = false;
                }
            } catch (InputMismatchException | SQLException e) {
                System.err.println(e.getMessage());
                scan.nextLine();
            }
        }
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
