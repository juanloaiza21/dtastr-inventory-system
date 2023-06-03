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
        logout();
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
     * @param idUser
     * @param name
     * @param email
     * @param cellPhone
     * @param typeUser Must be "SELLER" or "USER"
     * @param password
     * @throws SQLException
     */
    public Boolean createUser(String idUser, String name, String email, String cellphone, String typeUser, String password){
        try {
            String[] data = new String[] {idUser, name.toUpperCase(), email, cellphone, typeUser.toUpperCase(), password};
            if (data[4].equals("SELLER") || data[4].equals("USER")) {
                if (user.createUser(data)){
                    return true;
                }else{
                    return false;
                }
            } else {
                throw new IllegalArgumentException("The type of user must be 'SELLER' or 'USER'");             
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());            
            return false;
        }
    }

    /**
     * Get an user
     * @param id
     * @throws SQLException
     */
    public User getUser(int id){
        User data = null;
        try {
            data =  user.getOneUser(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return data;
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
     * @param email
     * @param password
     */
    public void updatePass(String email, String password){
        try {
        String[] data = new String[] {user.getByEmail(email), password};
        user.updateUser(data, true);
        } catch (NumberFormatException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Bool that represents the login an user
     * @param email
     * @param password
    */
    public void login(String email, String password) {
        try {
            this.email = email;
            user.login(email, password);
        } catch (Exception e) {
            System.err.println(e.getMessage()+" Umain login");
        }
        typeUser = user.getTypeUser();
        logged = user.getLogged();
    }


    /**
     * Delete an user
     * @throws SQLException
     * @param id
     */
    public void deleteUser(int id) throws SQLException {
        try {
            user.deleteUser(id);
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
        try {
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
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String[][] getProductsByName(String entrada) {
        return iMain.getitemByName(entrada);
    }
    public String[][] getProductsByStock(int entrada) {
        return iMain.getItemByStock(entrada);
    }
    public String[][] getProductsByPrice(Double entrada) {
        return iMain.getItemByPrice(entrada);
    }
    public String[][] getProductsLessThanPrice(Double entrada) {
        return iMain.getItemByPriceLess(entrada);
    }
    public String[][] getProductsLessThanStock(int entrada) {
        return iMain.getItemByStockLess(entrada);
    }
    public String[][] getAllAskedProducts() throws SQLException {
        return iMain.productAsked();
    }

    
    
    public String[][] getAllItems() throws SQLException{
        return iMain.getAll(); 
    }

    public void logout() {
        user = new UserFun();        
        logged = user.getLogged();
        typeUser = user.getTypeUser();
        email = "";        
        LoginGUI l = new LoginGUI(this);
    }

    public Boolean addProduct(String name, String price, String stock) {
        try {
            if(iMain.addProduct(name, Double.valueOf(price),Integer.valueOf( stock),email )){
                return true;
            }
        } catch (NumberFormatException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public Boolean updateProduct(int id,double price , int stock ){
        if((iMain.updateProductPrice(id, price))&&(iMain.updateProductStock(id, stock))){
            return true;
        }return false;


    }

    public Boolean deleteProduct(int id) {
        return iMain.deleteProduct(id);
    }

    public Boolean askProduct(String name) throws SQLException{
        return iMain.productAsk(name); // TODO Auto-generated catch block
    }

    public Boolean buyProduct(int id,int stock){
        return iMain.sellItem(id, stock);
    }


    
}
