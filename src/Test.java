import java.util.*;
import java.lang.*;
import java.util.*;
import java.sql.*;
import db.Conector;
import item.*;
import users.*;
import util.Datos;

public class Test {
    
    private Conector conector;
    private  User user;
    private Datos datos = new Datos();
    private Item[] item;

    public Test() throws SQLException {
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        user = new User();
        item = datos.items;
    }

    public void TestInsertItems(int n) throws SQLException {
        Queue<Item> data = new LinkedList<>();
        int counter = 0;
        for (int i = 0; i < n-1; i++) {
            data.add(item[counter]);
            if (counter < 999)counter++;
            else counter = 0;
        }
        Long time = -System.nanoTime();
        AskProducts ask = new AskProducts();
        ask.adding(data);
        time += System.nanoTime();
        System.out.println("The time for insert " + n + " items is: " + time + " nanoseconds");
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

    public void testInsertUser(int n) throws SQLException {
        long time = -System.nanoTime();
        for (int i = 0; i < n; i++) {
            user.createUser(new String[] {Integer.toString(i), Integer.toString(i), Integer.toString(i), Integer.toString(i), "USER", Integer.toString(i)});
        }
        time += System.nanoTime();
        System.out.println("The time for insert " + n + " users is: " + time + " nanoseconds");

    }
        

    public static void main(String[] args) {
        /*
         * Test de login, el parametro es el numero de loginsq ue se busca hacer
         *        try {
            Test test = new Test();
            test.testLogin(1000000);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
         */

         /*
          * 
          * Test de insercion de items, el parametro es el numero de items que se quiere insertar
          try {
            Test test = new Test();
            test.TestInsertItems(2500);
         } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
         }
          */

          /*
           * Test insercion usuarios
           */

           try {
            Test test = new Test();
            test.testInsertUser(10000);
           } catch (Exception e) {
            // TODO: handle exception
           }
    }
       
}
