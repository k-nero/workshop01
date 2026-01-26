/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Mobile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import static utils.DbContext.getConnection;

/**
 *
 * @author Nero
 */
public class MobileDao extends BaseDao {

    public List<Mobile> getAllMobiles() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                String sql = "select * from dbo.tbl_Mobile";
                PreparedStatement stm = connection.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    return mapToList(rs, Mobile.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Mobile getMobileById(String mobileId) {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                String sql = "select * from dbo.tbl_Mobile where mobileId=?";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, mobileId);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    Mobile mobile = mapRow(rs, Mobile.class);
                    if (mobile != null) {
                        return mobile;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Mobile> searchByIdOrName(String searchTerm) {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                String sql = "select * from dbo.tbl_Mobile where mobileId LIKE ? OR mobileName LIKE ?";
                PreparedStatement stm = connection.prepareStatement(sql);
                String searchPattern = "%" + searchTerm + "%";
                stm.setString(1, searchPattern);
                stm.setString(2, searchPattern);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    return mapToList(rs, Mobile.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Mobile> searchByPriceRange(float minPrice, float maxPrice) {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                String sql = "select * from dbo.tbl_Mobile where price >= ? AND price <= ? AND notSale = 0";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setFloat(1, minPrice);
                stm.setFloat(2, maxPrice);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    return mapToList(rs, Mobile.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createMobile(Mobile mobile) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                String sql = "insert into dbo.tbl_Mobile (mobileId, description, price, mobileName, yearOfProduction, quantity, notSale) "
                        + "values(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, mobile.getMobileId());
                stm.setString(2, mobile.getDescription());
                stm.setDouble(3, mobile.getPrice());
                stm.setString(4, mobile.getMobileName());
                stm.setInt(5, mobile.getYearOfProduction());
                stm.setInt(6, mobile.getQuantity());
                stm.setBoolean(7, mobile.isNotSale());
                stm.execute();
                return stm.getUpdateCount() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMobile(Mobile mobile) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                String sql = "update dbo.tbl_Mobile set description=?, price=?, quantity=?, notSale=? where mobileId=?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, mobile.getDescription());
                stm.setDouble(2, mobile.getPrice());
                stm.setInt(3, mobile.getQuantity());
                stm.setBoolean(4, mobile.isNotSale());
                stm.setString(5, mobile.getMobileId());
                stm.execute();
                return stm.getUpdateCount() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMobile(String mobileId) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement("delete from dbo.tbl_Mobile where mobileId=?");
                stm.setString(1, mobileId);
                stm.execute();
                return stm.getUpdateCount() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
