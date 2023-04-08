package item;

import java.sql.*;
import java.util.*;
import db.Conector;

/**
 * Class AskProducts
 * @author john pastor
 *
 */

public class AskProducts {
    private Conector conector;

    public AskProducts() {
        //conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "alejo2425");
        conector = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        conector.setTable("PRODUCTS");
    }
    
    public void Asking() throws SQLException {
        Scanner scan = new Scanner(System.in);
        Queue<Item> data = new LinkedList<>();
        Boolean controller = true;
        while (controller) {
            try {
                    System.out.println("do u wanna ask a product: 1. yes, 2. no");
                    int flag = scan.nextInt();
                    if (flag == 2) {
                        controller = false;
                    } else if(flag == 1){
                        System.out.println("name:");
                        String name = scan.next();
                        scan.nextLine();
                        System.out.println("how much stock: ");
                        int stock = scan.nextInt();
                        data.add(new Item(0, name, 0, stock));
                        adding(data);
                        scan.nextLine();
                        controller = false;
                    }
                    else {
                        System.out.println("Incorrect Option");
                    }
            } catch (InputMismatchException e) {
                // TODO: handle exception
                System.err.println("Must be int");
                scan.nextLine();
            }
        }

    }

    private void adding(Queue<Item> data) throws SQLException {
        conector.connect();
        for (Item item : data) {
            String name = item.getName();
            double price = item.getPrice();
            int stock = item.getStock();
            conector.insertProduct(name, price, stock);

        }
        data.clear();

    }

}
