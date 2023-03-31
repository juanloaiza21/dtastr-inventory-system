package db;
import java.sql.*;

public class Conection {
    
    protected final String url, username, password;
    private Connection conn = null;

    Conection(String url, String username, String password) {
        //TODO configurar como variables de entorno
        this.url = url ;//"jdbc:mysql://localhost:3306/DTAPROYECT";
        this.username = username; //"root";
        this.password = password; //"PCTdkx58";
    }

    /**
     * Generates the connection to the db
     * @throws SQLException
     */
    public void connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, username, password); 
            System.out.println("Succesfully connected");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    /**
     * Returns the connection object
     * @return connection
     */
    public Connection conectionGetter() {
        return conn;
    }
}
