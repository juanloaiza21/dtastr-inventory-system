package users;
import java.sql.SQLException;
import java.util.*;

/**
 * Console manager to users module;
 * @author Juan Loaiza
 * @version 1.0
 */
public class Main {
    
    Users user;

    Main(){
        user = new User();
    }

    /**
     * Create an user
     */
    private void createUser(){
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
        sc.close();
        user.createUser(data);
    }

    /**
     * Get an user
     */
    private void getUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of the user ");
        int id = sc.nextInt();
        sc.close();
        try {
           user.getOneUser(id);
        } catch (Exception e) {
            System.err.println("The id must be a number");
        }
    }

    /**
     * Update an user
     */
    private void updateUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of the user ");
    }

    /**
     * Update the password of an user
     */
    private void updatePass(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of the user ");
    }

    /*
     * Bool that represents the login an user
    */
    private Boolean login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the email of the user ");
    }


    /**
     * Delete an user
     */
    private void deleteUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of the user ");
    }

    /**
     * Get all users
     * @throws SQLException
     */
    private void getAll() throws SQLException{
        user.getAllUsers(); 
    }


    public static void main(String[] args) throws SQLException {
        Main main = new Main();
        /* 
        main.createUser();
        main.getUser();
        */
        main.getAll();
    }
}
