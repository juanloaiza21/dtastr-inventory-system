package db;

import java.sql.Connection;
import java.util.LinkedList;

public class QuerysQL extends Conection implements Query {
    
    private String query, table;
    private Connection conn;

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
