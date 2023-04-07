package users;
import java.sql.SQLException;
import java.util.*;

/**
 * Console manager to users module;
 * @author Juan Loaiza
 * @version 1.0
 */
public class Main {
    
    User user;

    Main(){
        user = new User();
    }

    /**
     * Create an user
     */
    public void createUser(){
        try {
            Scanner sc = new Scanner(System.in);
            String[] data = new String[6];
            System.out.println("Enter the id of the user ");
            try {
                data[0] = Integer.toString(sc.nextInt());
            } catch (Exception e) {
                System.err.println("The id must be a number");
            }
            System.out.println("Enter the name of the user ");
            sc.nextLine();
            data[1] = sc.nextLine();
            System.out.println("Enter the email of the user ");
            data[2] = sc.nextLine();
            System.out.println("Enter the cellphone of the user ");
            data[3] = sc.nextLine();
            System.out.println("Enter 1 if the user its seller, enter 2 if its an normal user");
            int aux = sc.nextInt();
            if (aux==1) data[4] = "SELLER";
            else if (aux==2) data[4] = "USER";
            else {
                System.out.println("The role must be 'SELLER' or 'BUYER'");
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
    public Boolean login() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the email of the user ");
            String email = sc.nextLine();
            System.out.println("Enter the password of the user ");
            String pass = sc.nextLine();
            user.login(email, pass);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return user.getLogged();
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

}
