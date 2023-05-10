import java.sql.SQLException;
import java.util.*;
import users.Main;
import item.*;

/**
 * @author Juan David Loaiza Reyes
 * @version 0.1
 */
public class App {
    
    public static void main(String[] args) throws SQLException, InterruptedException{
        Main main = new Main();
        item.Main iMain;
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
        iMain = new item.Main(main.getEmail());
        if (main.getTypeUser()) {
            //TODO seller menu
            System.out.println("Welcome to seller menu!");
            while (true) {
                System.out.println("");
                System.out.println("1. Add product");
                System.out.println("2. Update product stock");
                System.out.println("3. Update product price");
                System.out.println("4. Delete product");
                System.out.println("5. Get all products");
                System.out.println("6. Get all products that had been asked");
                System.out.println("7. Get products by name");
                System.out.println("8. Get products by Stock");
                System.out.println("9. Get products by price");
                System.out.println("10. Get products with price less than");
                System.out.println("11. get products with stock less than");
                System.out.println("12. Exit");
                try {
                    selector = sc.nextInt();
                    switch (selector) {
                        case 1:
                            iMain.addProduct();
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
                            iMain.productAsked();
                            Thread.sleep(1000);
                        break;
                        case 7:
                        try {
                            System.out.println("Enter the name of the product");
                            sc.nextLine();
                            String name = sc.nextLine();
                            iMain.getitemByName(name); 
                            Thread.sleep(1000);
                        } catch (InputMismatchException | UnsupportedOperationException e){
                            System.err.println("The option must be a int");
                            sc.nextLine();
                        }  
                        break;   
                        case 8:
                        try {System.out.println("Enter the stock of the product");
                            int name = sc.nextInt();
                            iMain.getItemByStock(name);
                            Thread.sleep(1000);
                        } catch (InputMismatchException | UnsupportedOperationException e){
                            System.err.println("The option must be a int");
                            sc.nextLine();
                        }  
                        break; 
                        case 9:
                        try {System.out.println("Enter the Price of the product");
                            Double name = sc.nextDouble();
                            iMain.getItemByPrice(name);
                        } catch (InputMismatchException | UnsupportedOperationException e){
                            System.err.println("The option must be a int");
                            sc.nextLine();
                        }  
                        break;
                        case 10:
                        try {System.out.println("Enter the price of the product");
                            Double name = sc.nextDouble();
                            iMain.getItemByPriceLess(name);
                            Thread.sleep(1000);
                        } catch (InputMismatchException | UnsupportedOperationException e){
                            System.err.println("The option must be a int");
                            sc.nextLine();
                        }  
                        break;   
                        case 11:
                        try {System.out.println("Enter the Stock of the product");
                            int name = sc.nextInt();
                            iMain.getItemByStockLess(name);
                            Thread.sleep(1000);
                        } catch (InputMismatchException | UnsupportedOperationException e){
                            System.err.println("The option must be a int");
                            sc.nextLine();
                        }  
                        break; 
                        case 12:
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
                System.out.println("5. Get products by name");
                System.out.println("6. Get products by Stock");
                System.out.println("7. Get products by price");
                System.out.println("8. Get products with price less than");
                System.out.println("9. get products with stock less than");
                System.out.println("10. Exit");
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
                         try {
                             System.out.println("Enter the name of the product");
                             sc.nextLine();
                             String name = sc.nextLine();
                             iMain.getitemByName(name); 
                             Thread.sleep(1000);
                         } catch (InputMismatchException | UnsupportedOperationException e){
                             System.err.println("The option must be a int");
                             sc.nextLine();
                         }  
                         break;   
                         case 6:
                         try {System.out.println("Enter the stock of the product");
                             int name = sc.nextInt();
                             iMain.getItemByStock(name);
                             Thread.sleep(1000);
                         } catch (InputMismatchException | UnsupportedOperationException e){
                             System.err.println("The option must be a int");
                             sc.nextLine();
                         }  
                         break; 
                         case 7:
                         try {System.out.println("Enter the Price of the product");
                             Double name = sc.nextDouble();
                             iMain.getItemByPrice(name);
                             Thread.sleep(1000);
                         } catch (InputMismatchException | UnsupportedOperationException e){
                             System.err.println("The option must be a int");
                             sc.nextLine();
                         }  
                         break;
                         case 8:
                         try {System.out.println("Enter the price of the product");
                             Double name = sc.nextDouble();
                             iMain.getItemByPriceLess(name);
                             Thread.sleep(1000);
                         } catch (InputMismatchException | UnsupportedOperationException e){
                             System.err.println("The option must be a int");
                             sc.nextLine();
                         }  
                         break;   
                         case 9:
                         try {System.out.println("Enter the Stock of the product");
                             int name = sc.nextInt();
                             iMain.getItemByStockLess(name);
                             Thread.sleep(1000);
                         } catch (InputMismatchException | UnsupportedOperationException e){
                             System.err.println("The option must be a int");
                             sc.nextLine();
                         }  
                         break; 
                        case 10:
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
