import java.sql.SQLException;
import java.util.*;
import users.Main;
import item.*;

/**
 * @author Juan David Loaiza Reyes
 * @version 0.1
 */
public class App {
    
    public static void main(String[] args) throws SQLException{
        Main main = new Main();
        item.Main iMain = new item.Main();
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        /*
         * User must be loged in to use the service
         */
        while (!main.getLogged()) {
            System.out.println("Welcome to inventory system 0.1 (Alpha)");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            try {
                int selector = sc.nextInt();
                switch (selector) {
                    case 1:
                        main.login();
                        if (!main.getLogged()){
                            System.out.println("Incorrect email or password");
                            Thread.sleep(1000);
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                        }
                        else {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                        }
                    break;
                    case 2:
                        main.createUser();
                        Thread.sleep(1000);
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    break;
                    default:
                        System.out.println("Incorrect option: " + sc.nextInt());
                    break;
                }
            } catch (InputMismatchException | InterruptedException e){
                System.err.println("The option must be a int");
                sc.nextLine();
            } 
        }
        int selector = 0;
        if (main.getTypeUser()) {
            //TODO seller menu
            System.out.println("Welcome to seller menu!");
            while (true) {
                System.out.println("");
                System.out.println("1. Add product");
                System.out.println("2. Update product stock");
                System.out.println("3. Update product price");
                System.out.println("4. Delete product");
                System.out.println("5. Get products");
                System.out.println("6. Exit");
                try {
                    selector = sc.nextInt();
                    switch (selector) {
                        case 1:
                            iMain.addProduct(main.getEmail());
                        break;
                        case 2:
                            iMain.updateProductStock();
                        break;
                        case 3:
                            iMain.updateProductPrice();
                        break;
                        case 4:
                            iMain.deleteProduct();
                        break;
                        case 5:
                            iMain.getAll();
                        break;
                        case 6:
                            System.exit(0);
                        break;
                        default:
                            System.out.println("Incorrect option: " + sc.nextInt());
                        break;
                    }
                } catch (InputMismatchException | UnsupportedOperationException e){
                    System.err.println("The option must be a int");
                    sc.nextLine();
                }
            }
        } else{
            System.out.println("Welcome to user menu!");
            /*
             */
            while (true) {
                System.out.println("");
                System.out.println("1. Buy product");
                System.out.println("2. Make a devolution");
                System.out.println("3. Ask for a product");
                System.out.println("4. Get products");
                System.out.println("5. Exit");
                try {
                    selector = sc.nextInt();
                    switch (selector) {
                        case 1:
                            iMain.sellItem();
                        break;
                        case 2:
                            iMain.devolution();
                        break;
                        case 3:
                            iMain.productAsk();
                        break;
                        case 4:
                            iMain.getAll();
                         break;
                        case 5:
                            System.exit(0);
                        break;
                        default:
                            System.out.println("Incorrect option: " + sc.nextInt());
                        break;
                }
            } catch (InputMismatchException e){
                System.err.println("The option must be a int");
                sc.nextLine();
            }
            }
        }
    }
}
