/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author admin
 */
public class MobileDAO {
    
    private static final String GET_ALL_MOBILES = "SELECT mobileID, description, price, mobileName, yearOfProduction, quantity, notSale FROM tblMobiles";
    private static final String GET_MOBILE = "SELECT mobileID, description, price, mobileName, yearOfProduction, quantity, notSale FROM tblMobiles WHERE mobileID = ?";
    private static final String SEARCH_MOBILES = "SELECT mobileID, description, price, mobileName, yearOfProduction, quantity, notSale FROM tblMobiles WHERE mobileName LIKE ? AND price >= ? AND price <= ?";
    private static final String SEARCH_BY_ID_OR_NAME = "SELECT mobileID, description, price, mobileName, yearOfProduction, quantity, notSale FROM tblMobiles WHERE mobileID LIKE ? OR mobileName LIKE ?";
    private static final String SEARCH_BY_PRICE_RANGE = "SELECT mobileID, description, price, mobileName, yearOfProduction, quantity, notSale FROM tblMobiles WHERE price >= ? AND price <= ? AND notSale = 0";
    private static final String INSERT_MOBILE = "INSERT INTO tblMobiles (mobileID, description, price, mobileName, yearOfProduction, quantity, notSale) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_MOBILE = "UPDATE tblMobiles SET description = ?, price = ?, mobileName = ?, yearOfProduction = ?, quantity = ?, notSale = ? WHERE mobileID = ?";
    private static final String DELETE_MOBILE = "DELETE FROM tblMobiles WHERE mobileID = ?";
    private static final String UPDATE_QUANTITY = "UPDATE tblMobiles SET quantity = ? WHERE mobileID = ?";
    
    public List<MobileDTO> getAllMobiles() throws SQLException {
        List<MobileDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_MOBILES);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String mobileID = rs.getString("mobileID");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    list.add(new MobileDTO(mobileID, description, price, mobileName, yearOfProduction, quantity, notSale));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return list;
    }
    
    public MobileDTO getMobile(String mobileID) throws SQLException {
        MobileDTO mobile = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_MOBILE);
                ptm.setString(1, mobileID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    mobile = new MobileDTO(mobileID, description, price, mobileName, yearOfProduction, quantity, notSale);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return mobile;
    }
    
    public List<MobileDTO> searchMobiles(String mobileName, float minPrice, float maxPrice) throws SQLException {
        List<MobileDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_MOBILES);
                ptm.setString(1, "%" + mobileName + "%");
                ptm.setFloat(2, minPrice);
                ptm.setFloat(3, maxPrice);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String mobileID = rs.getString("mobileID");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String name = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    list.add(new MobileDTO(mobileID, description, price, name, yearOfProduction, quantity, notSale));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return list;
    }
    
    /**
     * Search mobile by ID or name (for Staff)
     * @param searchValue - can be mobileID or mobileName
     * @return List of matching mobiles
     * @throws SQLException
     */
    public List<MobileDTO> searchMobileByIdOrName(String searchValue) throws SQLException {
        List<MobileDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_ID_OR_NAME);
                String searchPattern = "%" + searchValue + "%";
                ptm.setString(1, searchPattern);
                ptm.setString(2, searchPattern);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String mobileID = rs.getString("mobileID");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    list.add(new MobileDTO(mobileID, description, price, mobileName, yearOfProduction, quantity, notSale));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return list;
    }
    
    /**
     * Search mobiles by price range (for User)
     * Only returns mobiles that are available for sale (notSale = false)
     * @param minPrice - minimum price
     * @param maxPrice - maximum price
     * @return List of matching mobiles
     * @throws SQLException
     */
    public List<MobileDTO> searchMobilesByPriceRange(float minPrice, float maxPrice) throws SQLException {
        List<MobileDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_PRICE_RANGE);
                ptm.setFloat(1, minPrice);
                ptm.setFloat(2, maxPrice);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String mobileID = rs.getString("mobileID");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    list.add(new MobileDTO(mobileID, description, price, mobileName, yearOfProduction, quantity, notSale));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return list;
    }
    
    public boolean createMobile(MobileDTO mobile) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_MOBILE);
                ptm.setString(1, mobile.getMobileID());
                ptm.setString(2, mobile.getDescription());
                ptm.setFloat(3, mobile.getPrice());
                ptm.setString(4, mobile.getMobileName());
                ptm.setInt(5, mobile.getYearOfProduction());
                ptm.setInt(6, mobile.getQuantity());
                ptm.setBoolean(7, mobile.isNotSale());
                check = ptm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }
    
    public boolean updateMobile(MobileDTO mobile) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_MOBILE);
                ptm.setString(1, mobile.getDescription());
                ptm.setFloat(2, mobile.getPrice());
                ptm.setString(3, mobile.getMobileName());
                ptm.setInt(4, mobile.getYearOfProduction());
                ptm.setInt(5, mobile.getQuantity());
                ptm.setBoolean(6, mobile.isNotSale());
                ptm.setString(7, mobile.getMobileID());
                check = ptm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }
    
    public boolean deleteMobile(String mobileID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_MOBILE);
                ptm.setString(1, mobileID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }
    
    public boolean updateQuantity(String mobileID, int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY);
                ptm.setInt(1, quantity);
                ptm.setString(2, mobileID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }
}
