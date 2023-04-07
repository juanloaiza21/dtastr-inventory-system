package db;
import java.sql.SQLException;
import java.util.*;

/**
 * Query interfaces to know wich sequencial structures must be implemented on project
 * @Author
 * Juan Loaiza
 * @implNote This class has as default the table you set on constructor, it will change on functions params just for that one instance
 */
public interface Query {
    String query = null;
    String table = null;
    public static final int UNIQUE_CONSTRAINT_VIOLATED = 1062;
    
    /**
     * Get all data in a table throught the console
     * @implNote This method has as default the table you set on constructor
     */
    void getAll();

    /**
     * Set a new table as default
     * @param table
     */
    void setTable(String table);

    /**
     * Get one element in a table throught the console
     * @param id int. The id you are watching for
     * @implNote This method has as default the table you set on constructor
     */
    void getOne(int id);

    /**
     * Get one element in a table throught the console
     * @param field string. The field you are looking for
     * @value value. The value on filtering
     * @implNote This method has as default the table you set on constructor
     */
    String getOne(String fieldFilter, String value, String fieldResult);

    /**
     * Get one element in a table throught the console
     * @param id int. The id you are watching for
     * @param table String. Another table, this will not set default table
     */
    void getOne(int id, String table);

    /**
     * Insert a new element into a table JUST ONE
     * @param data LinkedList<Object> 
     * @param insertFields String[] 
     * @implNote first data on linked list must be the int id of the entity
     */
    void insert(LinkedList<Object> data, String[] insertFields);
    
    /**
     * Insert a batch of new elements into a table
     * @param data LinkedList <Object[]> 
     */
    void batchInsert(LinkedList<Object[]> data) throws SQLException;

    void update(LinkedList<Object> data, String[] updateFields, int id);
    
    /**
     * Delete an element in a table
     * @param id int
     */
    void deleteOne(int id);
    
    /**
     * Delete all elements in a table
     */
    void deleteAll();
}
