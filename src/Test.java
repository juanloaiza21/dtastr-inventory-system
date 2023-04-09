import java.util.*;
import java.lang.*;
import java.sql.*;
import db.Conector;
import item.*;
import users.*;

public class Test {
    
    private Conector conector;
    private  User user;
    private Item item;
    private int id, stock;
    private String name;
    private double price;

    public Test() throws SQLException {
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        user = new User();
        id = stock = 0;
        name = "name" + 0;
        price = 0.0;
        item = new Item(id, name, price, stock);
    }

    public void testLogin(int n) throws SQLException {
        String[] users = new String[2];
        String[] password = new String[2];
        users[0] = "usuario1@example.com";
        users[1] = "usuario4@example.com";
        password[0] = "password123";
        password[1] = "1234abcd";
        long time = 0;
        int i = 0;
        long resutl = 0;
        try {
            for (i = 1; i <= n; i++) {
                long auxtime = -System.nanoTime();
                if (i%2==2) user.login(users[0], password[0]);
                else user.login(users[1], password[1]);
                auxtime += System.nanoTime();
                time += auxtime;
            }
            resutl = time/n;
            System.out.println("The average time for login is: " + resutl + " nanoseconds" + " in " + i + " iterations");
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
            resutl = time/n;
            System.out.println("The average time for login is: " + resutl + " nanoseconds" + " in " + i + " iterations");
        }
    }
        

    public static void main(String[] args) {
        try {
            Test test = new Test();
            test.testLogin(1000000);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
       
}
