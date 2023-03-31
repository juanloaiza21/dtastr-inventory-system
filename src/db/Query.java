package db;
import java.util.*;


/**
 * @Author
 * Juan Loaiza
 * @implNote
 * Query interfaces to know wich sequencial structures must be implemented on project
 */
public interface Query {
    String query = null;
    String table = null;
    /**
     * Get all data in a table throught the console
     */
    void getAll();
    /**
     * Get one element in a table throught the console
     */
    void getOne(int id);
    /**
     * Insert a new element into a table
     * @param data LinkedList<Object> 
     */
    void insert(LinkedList<Object> data);
    /**
     * Insert a batch of new elements into a table
     * @param data LinkedList <Object[]> 
     */
    void batchInsert(LinkedList<Object[]> data);
    /**
     * Update an element in a table
     * @param data String[] 
     */
    void update(String[] data);
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
