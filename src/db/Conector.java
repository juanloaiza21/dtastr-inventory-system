package db;
import java.sql.*;
import java.util.LinkedList;


/**
 * Class that generates the connection to the db
 * @author Juan Loaiza
 */
public class Conector implements Query {
    
    private String query, table;
    
    protected final String url, username, password;
    private Connection conn = null;

    /**
     * Constructor
     * @param url db url
     * @param username username on the db, default is root
     * @param password password on the db, default is author one
     */
    Conector(String url, String username, String password) {
        //TODO configurar como variables de entorno
        this.url = url ;//"jdbc:mysql://localhost:3306/DTAPROYECT";
        this.username = username; //"root";
        this.password = password; //"PCTdkx58";
        table = "USERS";
    }

    /**
     * Generates the connection to the db
     * @throws SQLException
     */
    public void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, username, password); 
            System.out.println("Succesfully connected");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    @Override
    public void setTable(String table) {
        this.table = table;
    }
    
    //TODO parse PRODUCTS
    @Override
    public void getAll() {
        query = "SELECT * FROM ";
        PreparedStatement instruction;
        ResultSet result;
        try {
           instruction= conn.prepareStatement(query+ " " + table+";");
           result = instruction.executeQuery();
           if (table == "USERS") {
                userParse(result);
           } else if(table == "PRODUCTS"){
                //TODO parse PRODUCTS
           } 
           else {
                System.out.println("No data");
           }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }



    @Override
    public void getOne(int id) {
        query = "SELECT * FROM ";
        PreparedStatement instruction;
        ResultSet result;
        try {
            instruction = conn.prepareStatement(query + " " + table + " WHERE id = ?;");
            instruction.setInt(1, id);
            result = instruction.executeQuery();
            if (table == "USERS") {
                userParse(result);
            } else if(table == "PRODUCTS"){
                //TODO parse PRODUCTS
            } 
            else {
                System.out.println("No data");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void getOne(int id, String table) {
        query = "SELECT * FROM ";
        PreparedStatement instruction;
        ResultSet result;
        try {
            instruction = conn.prepareStatement(query + " " + table + " WHERE id = ?;");
            instruction.setInt(1, id);
            result = instruction.executeQuery();
            if (table == "USERS") {
                userParse(result);
            } else if(table == "PRODUCTS"){
                //TODO parse PRODUCTS
            } 
            else {
                System.out.println("No data");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void insert(LinkedList<Object> data, String[] insertFields) {
        PreparedStatement instruction;
        int result;
        queryGen(insertFields);
        try {
            instruction = conn.prepareStatement(query);
            for (int i = 0; i < data.size(); i++) {
                if(i == 0) {
                    instruction.setInt(i+1, (int) data.get(i));
                } else {
                    instruction.setString(i+1, (String) data.get(i));
                }
            }
            result = instruction.executeUpdate();
            if(result>0)System.out.println("Succesfully inserted");
            if (result<=0) throw new Exception("Error inserting");
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void batchInsert(LinkedList<Object[]> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'batchInsert'");
    }

    @Override
    public void update(String[] data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteOne(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOne'");
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    public static void main(String[] args) {
        Conector con = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        try {
            LinkedList<Object> data = new LinkedList<>();
            /* 
            data.add(54);
            data.add("Juan");
            data.add("juan400reyesloazia@gmail.com");
            data.add("555-1234");
            data.add("user");
            data.add("123456");
            con.connect();
            con.insert(data, new String[] {"id", "nombre", "email", "cellphone", "rol", "pass"});
            con.getOne(54);
            */
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void userParse(ResultSet result) {
        try {
            while (result.next()) {
                System.out.println(result.getInt("id") + ": " + result.getString("nombre") + " " + result.getString("email") + " " + result.getString("rol"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("No more data");
    }

    private void queryGen(String[] insertFields){
        StringBuilder fields = new StringBuilder();
        StringBuilder questionMarks = new StringBuilder();
        fields.append("(");
        questionMarks.append("(");
        for (int i = 0; i < insertFields.length; i++) {
            questionMarks.append("?");
            fields.append(insertFields[i]);
            if (i != insertFields.length - 1) {
                fields.append(", ");
                questionMarks.append(",");
            }
        }
        fields.append(")");
        questionMarks.append(")");
        query = "INSERT INTO " + table + fields.toString() + " VALUES " + questionMarks.toString() + ";";
    }

}
