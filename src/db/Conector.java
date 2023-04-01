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
                productParse(result);
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
                productParse(result);
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
                productParse(result);
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
        queryGen("INSERT INTO ", insertFields);
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
    public void update(LinkedList<Object> data, String[] updateFields, int id) {
        PreparedStatement instruction;
        int result;
        queryGenUpdate("UPDATE ", updateFields);
        try {
            instruction = conn.prepareStatement(query);
            for (int i = 0; i < data.size() + 1; i++) {
                if(i+1 == data.size()+1) {
                    instruction.setInt(i+1, (int) id);
                } else {
                    instruction.setString(i+1, (String) data.get(i));
                }
            }
            result = instruction.executeUpdate();
            if(result>0)System.out.println("Succesfully updated");
            if (result<=0) throw new Exception("Error inserting");
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteOne(int id) {
        PreparedStatement instruction;
        query = "DELETE FROM " + table + " WHERE id = ?;";
        try {
            instruction = conn.prepareStatement(query);
            instruction.setInt(1, id);
            int result = instruction.executeUpdate();
            if(result>0)System.out.println("Succesfully deleted");
            if (result<=0) throw new Exception("Error inserting");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        PreparedStatement instruction;
        query = "DELETE FROM " + table + ";";
        try {
            instruction = conn.prepareStatement(query);
            int result = instruction.executeUpdate();
            if(result>0)System.out.println("Succesfully deleted");
            if (result<=0) throw new Exception("Error inserting");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Conector con = new Conector("jdbc:mysql://localhost:3306/DTAPROYECT", "root", "PCTdkx58");
        try {
            /* insert test
            LinkedList<Object> data = new LinkedList<>();
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
            /*Update test 
            LinkedList<Object> data = new LinkedList<>();
            String[] dataTest = new String[] {"nombre"};
            data.add("UpdateJuan");
            con.connect();
            con.update(data, dataTest, 54);
            con.getOne(54);
            */
            /*Delete test
            con.connect();
            con.deleteOne(54);
            con.getOne(54);
            */
            /*Delete all test 
            con.connect();
            con.deleteAll();
            con.getAll();
            */
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Parses the result of the query
     * @param result
     */
    private void productParse(ResultSet result){
        //TODO parse PRODUCTS as userParse
        throw new UnsupportedOperationException("Unimplemented method 'batchInsert'");
    } 

    /**
     * Parses the result of the query
     * @param result
     */
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

    /**
     * Generates the insert query for the database
     * @param firstStatement
     * @param insertFields
     */
    private void queryGen(String firstStatement, String[] insertFields){
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
        query = firstStatement + table + fields.toString() + " VALUES " + questionMarks.toString() + ";";
    }

    /**
     * Generates the update query for the database
     * @param firstStatement
     * @param insertFields
     * @param id
     */
    private void queryGenUpdate(String firstStatement, String[] insertFields){
        StringBuilder fields = new StringBuilder();
        for (int i = 0; i < insertFields.length; i++) {
            fields.append(insertFields[i]+" = ?");
            if (i != insertFields.length - 1) {
                fields.append(", ");
            }
        }
        query = firstStatement +table + " SET "+ fields.toString() +" WHERE id=?"+ ";";
    }

}
