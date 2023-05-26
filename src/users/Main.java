package users;
import java.sql.SQLException;
import java.util.*;

/**
 * Console manager to users module;
 * @author Juan Loaiza
 * @version 1.0
 */
public class Main {
    
    private UserFun user;
    private Boolean logged;
    private Boolean typeUser;
    private String email;

    public Main(){
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
     * @param idUser
     * @param name
     * @param email
     * @param cellPhone
     * @param typeUser Must be "SELLER" or "USER"
     * @param password
     * @throws SQLException
     */
    public void createUser(String idUser, String name, String email, String cellphone, String typeUser, String password){
        try {
            String[] data = new String[] {idUser, name.toUpperCase(), email, cellphone, typeUser.toUpperCase(), password};
            if (data[4].equals("SELLER") || data[4].equals("USER")) {
                user.createUser(data);
            } else {
                throw new IllegalArgumentException("The type of user must be 'SELLER' or 'USER'");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
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
            user.login(email, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
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

    
}
