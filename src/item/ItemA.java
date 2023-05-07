package item;
import java.util.*;

import db.Conector;

import java.sql.*;

/**
 * @author john pastor
 * @version 1.0
 * updated 1.0 [6/05/2023]
 * @Updated_by Brallan mendoza, Juan Loaiza
 */
public class ItemA {
    
    protected int id;
    protected String name;
    protected double price;
    protected int stock;
    protected String seller;
    Conector conector;

    public ItemA(int id, String name, double price, int stock, String seller) throws SQLException {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.seller = seller;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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
