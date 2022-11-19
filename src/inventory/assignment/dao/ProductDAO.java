/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.assignment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dungnh
 */
public class ProductDAO {

    private int id;
    private String sku;
    private String name;
    private int supplierId;
    private String supplierName;
    private double price;
    private int quantity;

    public ProductDAO() {

    }

    public ProductDAO(int id, String sku, String name, int supplierId, double price, int quantity) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.supplierId = supplierId;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDAO(String sku, String name, int supplierId, double price, int quantity) {
        this.sku = sku;
        this.name = name;
        this.supplierId = supplierId;
        this.price = price;
        this.quantity = quantity;
    }

    public void parse(ResultSet rs) {
        try {
            this.setId(rs.getInt(1));
            this.setName(rs.getString(2));
            this.setSku(rs.getString(3));
            this.setSupplierId(rs.getInt(4));
            this.setSupplierName(rs.getString(5));
            this.setPrice(rs.getDouble(6));
            this.setQuantity(rs.getInt(7));
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
