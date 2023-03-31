package db;
import java.sql.*;
import java.util.*;

public class Conection {
    
    protected final String url, username, password;

    public Conection() {
        //TODO configurar como variables de entorno
        url = "jdbc:mysql://localhost:3306/DTAPROYECT";
        username = "root";
        password = "PCTdkx58";
    }

    /**
     * Generates the connection to the db
     * @throws SQLException
     */
    public void connect() throws SQLException {
        try {
          DriverManager.getConnection(url, username, password); 
          System.out.println("Succesfully connected");  
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
