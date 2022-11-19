/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.assignment.repository;

import inventory.assignment.dao.ProductDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dungnh
 */
public class ProductRepository extends Repository{
    
    public List<ProductDAO> listProduct() {
        String sql = """
                     SELECT p.ID, p.Name, p.Sku, p.SupplierID, s.Name, p.Price, p.Quantity
                     FROM Products p
                              JOIN Suppliers s on s.ID = p.SupplierID
                     WHERE p.IsActive = 1;
                     """;
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<ProductDAO> products = new ArrayList<>();
            while (rs.next()) {
                ProductDAO product = new ProductDAO();
                product.parse(rs);
                products.add(product);
            }
            stmt.close();
            conn.close();
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProductByID(ProductDAO p) {
        String sqlTemplate = """
                     UPDATE Products
                              SET Sku        = ?,
                                  Name       = ?,
                                  SupplierID = ?,
                                  Quantity   = ?,
                                  Price      = ?
                              WHERE ID = ?;
                     """;
        try {
            Connection conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement(sqlTemplate);
            pstmt.setString(1, p.getSku());
            pstmt.setString(2, p.getName());
            pstmt.setInt(3, p.getSupplierId());
            pstmt.setInt(4, p.getQuantity());
            pstmt.setDouble(5, p.getPrice());
            pstmt.setInt(6, p.getId());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<ProductDAO> searchProduct(String searchContent) {
        String sqlTemplate = """
                     SELECT p.ID, p.Name, p.Sku, p.SupplierID, s.Name, p.Price, p.Quantity
                                 FROM Products p
                                          JOIN Suppliers s on s.ID = p.SupplierID
                                 WHERE p.IsActive = true AND (p.Sku LIKE ?
                                    OR p.Name LIKE ?
                                    OR p.Sku LIKE ?
                                    OR p.SupplierID LIKE ?
                                    OR s.Name LIKE ?);
                     """;
        try {
            Connection conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement(sqlTemplate);
            searchContent = "%" + searchContent + "%";
            pstmt.setString(1, searchContent);
            pstmt.setString(2, searchContent);
            pstmt.setString(3, searchContent);
            pstmt.setString(4, searchContent);
            pstmt.setString(5, searchContent);
            
            ResultSet rs = pstmt.executeQuery();
            List<ProductDAO> products = new ArrayList<>();
            while (rs.next()) {
                ProductDAO product = new ProductDAO();
                product.parse(rs);
                products.add(product);
            }
            pstmt.close();
            conn.close();
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void removeProductByID(int id) {
        String sqlTemplate = """
                     UPDATE Products
                              SET IsActive = false
                              WHERE ID = ?;
                     """;
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sqlTemplate);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void createProductByID(ProductDAO p) {
        String sqlTemplate = """
                     INSERT INTO Products(Sku, Name, SupplierID, Quantity, Price)
                              VALUES (?, ?, ?, ?, ?);
                     """;
        try {
            Connection conn = getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement(sqlTemplate);
            pstmt.setString(1, p.getSku());
            pstmt.setString(2, p.getName());
            pstmt.setInt(3, p.getSupplierId());
            pstmt.setInt(4, p.getQuantity());
            pstmt.setDouble(5, p.getPrice());
            pstmt.execute();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
