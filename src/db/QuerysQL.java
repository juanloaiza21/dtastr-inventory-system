package db;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Author
 * Juan Loaiza
 * @implNote
 * Class that implements the Query interface and helps throught the connection to the database  
 */
public class QuerysQL extends Conection implements Query {
    
    private String query, table;
    private Connection conn;
    
    /**
     * Constructor
     * @param url db url
     * @param username username on the db, default is root
     * @param password password on the db, default is author one
     */
    QuerysQL(String url, String username, String password){
        super(url, username, password);
        conn = this.conectionGetter();
        query = "SELECT * FROM ";
        table = "USERS";
    }

    @Override
    public void getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void getOne(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }

    @Override
    public void insert(LinkedList<Object> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
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

}
