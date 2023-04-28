package item;
import java.util.*;

import db.Conector;

import java.sql.*;

/**
 * @author john pastor
 * @version 1.0
 * updated 1.0 [28/04/2023]
 * @Updated_by Juan Loaiza
 */
public class ItemA {
    
    protected int id;
    protected String name;
    protected double price;
    protected int stock;
    Conector conector;

    public ItemA(int id, String name, double price, int stock) throws SQLException {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
