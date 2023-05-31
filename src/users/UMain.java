package users;
import java.sql.SQLException;
import java.util.*;

import interfaz.*;
import item.IMain;

/**
 * Console manager to users module;
 * @author Juan Loaiza
 * @version 1.0
 */
public class UMain {
    
    private UserFun user;
    private Boolean logged;
    private Boolean typeUser;
    private String email;
    private IMain iMain;

    public UMain(){
        user = new UserFun();
        logged = user.getLogged();
        typeUser = user.getTypeUser();
        email = "";
    }

    public String getEmail() {
        return email;
    }
    
    public Boolean getLogged() {
        return logged;
    }
    
    public Boolean getTypeUser() {
        return typeUser;
    }

    /**
     * Create an user
     */
    public void createUser(){
        try {
            Scanner sc = new Scanner(System.in);
            Boolean validator = true;
            String[] data = new String[6];
            System.out.println("Enter the id of the user ");
            while (validator) {
                try {
                    data[0] = Integer.toString(sc.nextInt());
                    validator = false;
                } catch (InputMismatchException e) {
                    System.err.println("The id must be a number");
                    sc.nextLine();
                }
            }
            validator = true;
            System.out.println("Enter the name of the user ");
            sc.nextLine();
            data[1] = sc.nextLine();
            System.out.println("Enter the email of the user ");
            data[2] = sc.nextLine();
            System.out.println("Enter the cellphone of the user ");
            data[3] = sc.nextLine();
            System.out.println("Enter 1 if the user its seller, enter 2 if its an normal user");
            while (validator) {
                try {
                    int aux = sc.nextInt();
                    if (aux==1) data[4] = "SELLER";
                    else if (aux==2) data[4] = "USER";
                    validator = false;
                } catch (InputMismatchException e) {
                    System.out.println("Wrong input, must be 1 or 2 ");
                    sc.nextLine();
                }
            }
            sc.nextLine();
            System.out.println("Enter the password of the user ");
            data[5] = sc.nextLine();
            user.createUser(data);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Get an user
     */
    public void getUser(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the id of the user ");
            int id = sc.nextInt();
            try {
               user.getOneUser(id);
            } catch (Exception e) {
                System.err.println("The id must be a number");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Update an user
     * @implNote Create an implementation if its necesary
     */
    private void updateUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Update the password of an user
     */
    public void updatePass(){
        try {
        String[] data = new String[2];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the email of the user ");
        String email = sc.nextLine();
            data[0] = user.getByEmail(email);
            System.out.println("Enter the new password ");
            data[1] = sc.nextLine();
            user.updateUser(data, true);
        } catch (NumberFormatException | SQLException e) {
            // TODO Auto-generated catch block
            System.err.println(e.getMessage());
        }
    }

    /*
     * Bool that represents the login an user
    */
    public void login(String email,String pass) {
        try {
            this.email = email;
            user.login(email, pass);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        typeUser = user.getTypeUser();
        logged = user.getLogged();
    }


    /**
     * Delete an user
     * @throws SQLException
     */
    public void deleteUser() throws SQLException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the id of the user ");
            user.deleteUser(sc.nextInt());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Get all users
     * @throws SQLException
     */
    public void getAll() throws SQLException{
        user.getAllUsers(); 
    }

    public void startlog(String main){
        int selector = 0;
        iMain = new IMain(main);
        Scanner sc= new Scanner(System.in);
        if (getTypeUser()) {
            //TODO seller menu
            System.out.println("Welcome to seller menu!");
            SellerGUI sellGUI = new SellerGUI(this);
            /*  while (true) {
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
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }*/
        } else{
            System.out.println("Welcome to user menu!");
            UserGUI useGUI = new UserGUI(this);
            /*
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
                             String name = sc.nextLine();
                             iMain.getitemByName(name); 
                             Thread.sleep(1000);
                         } catch (InputMismatchException | UnsupportedOperationException | InterruptedException e){
                             System.err.println("The option must be a int");
                             sc.nextLine();
                         }  
                         break;   
                         case 6:
                         try {System.out.println("Enter the stock of the product");
                             int name = sc.nextInt();
                             iMain.getItemByStock(name);
                             Thread.sleep(1000);
                         } catch (InputMismatchException | UnsupportedOperationException | InterruptedException e){
                             System.err.println("The option must be a int");
                             sc.nextLine();
                         }  
                         break; 
                         case 7:
                         try {System.out.println("Enter the Price of the product");
                             Double name = sc.nextDouble();
                             iMain.getItemByPrice(name);
                             Thread.sleep(1000);
                         } catch (InputMismatchException | UnsupportedOperationException | InterruptedException e){
                             System.err.println("The option must be a int");
                             sc.nextLine();
                         }  
                         break;
                         case 8:
                         try {System.out.println("Enter the price of the product");
                             Double name = sc.nextDouble();
                             iMain.getItemByPriceLess(name);
                             Thread.sleep(1000);
                         } catch (InputMismatchException | UnsupportedOperationException | InterruptedException e){
                             System.err.println("The option must be a int");
                             sc.nextLine();
                         }  
                         break;   
                         case 9:
                         try {System.out.println("Enter the Stock of the product");
                             int name = sc.nextInt();
                             iMain.getItemByStockLess(name);
                             Thread.sleep(1000);
                         } catch (InputMismatchException | UnsupportedOperationException | InterruptedException e){
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
            } catch (InputMismatchException | SQLException e){
                System.err.println("The option must be a int");
                sc.nextLine();
            }
            }
             */
        }
    }

    
}
