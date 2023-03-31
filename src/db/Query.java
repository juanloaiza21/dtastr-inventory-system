package db;
import java.util.*;

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
     * @param LinkedList<Object> data
     */
    void insert(LinkedList<Object> data);
    /**
     * Insert a batch of new elements into a table
     * @param LinkedList<Object[]> data
     */
    void batchInsert(LinkedList<Object[]> data);
    /**
     * Update an element in a table
     * @param String[] data
     */
    void update(String[] data);
    /**
     * Delete an element in a table
     * @param int id
     */
    void deleteOne(int id);
    /**
     * Delete all elements in a table
     */
    void deleteAll();
}
