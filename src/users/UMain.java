package users;
import java.sql.SQLException;
import java.util.*;
import item.IMain;
import Interfaz.*;

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
            } else{
                System.out.println("Welcome to user menu!");
                UserGUI useGUI = new UserGUI(this);
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
