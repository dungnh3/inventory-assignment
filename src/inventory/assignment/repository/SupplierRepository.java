/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.assignment.repository;

import inventory.assignment.dao.SupplierDAO;
import static inventory.assignment.repository.Repository.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dungnh
 */
public class SupplierRepository extends Repository {
    public List<SupplierDAO> getListSupplier() {
        String sql = """
                     SELECT * FROM Suppliers;
                     """;
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<SupplierDAO> suppliers = new ArrayList<>();
            while (rs.next()) {
                SupplierDAO s = new SupplierDAO();
                s.parse(rs);
                suppliers.add(s);
            }
            stmt.close();
            conn.close();
            return suppliers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
