/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author admin
 */
public class UserDAO {
    
    private static final String LOGIN = "SELECT userID, password, fullName, role FROM tblUsers WHERE userID = ? AND password = ?";
    private static final String GET_USER = "SELECT userID, password, fullName, role FROM tblUsers WHERE userID = ?";
    private static final String INSERT_USER = "INSERT INTO tblUsers (userID, password, fullName, role) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE tblUsers SET password = ?, fullName = ?, role = ? WHERE userID = ?";
    private static final String DELETE_USER = "DELETE FROM tblUsers WHERE userID = ?";
    
    public UserDTO checkLogin(String userID, int password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setInt(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    int role = rs.getInt("role");
                    user = new UserDTO(userID, password, fullName, role);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return user;
    }
    
    public UserDTO getUser(String userID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int password = rs.getInt("password");
                    String fullName = rs.getString("fullName");
                    int role = rs.getInt("role");
                    user = new UserDTO(userID, password, fullName, role);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return user;
    }
    
    public boolean createUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_USER);
                ptm.setString(1, user.getUserID());
                ptm.setInt(2, user.getPassword());
                ptm.setString(3, user.getFullName());
                ptm.setInt(4, user.getRole());
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
    
    public boolean updateUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_USER);
                ptm.setInt(1, user.getPassword());
                ptm.setString(2, user.getFullName());
                ptm.setInt(3, user.getRole());
                ptm.setString(4, user.getUserID());
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
    
    public boolean deleteUser(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_USER);
                ptm.setString(1, userID);
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
