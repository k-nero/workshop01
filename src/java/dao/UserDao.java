/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import static utils.DbContext.getConnection;

/**
 *
 * @author Nero
 */
public class UserDao extends BaseDao {

    public User getByUserId(String userId) {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                String sql = "select * from dbo.tbl_User where userId=?";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, userId);
                ResultSet rs = stm.executeQuery();
                if (rs != null && rs.next()) {
                    User userObj = mapRow(rs, User.class);
                    if (userObj != null) {
                        return userObj;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                String sql = "select * from dbo.tbl_User";
                PreparedStatement stm = connection.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                if (rs != null) {
                    return mapToList(rs, User.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
