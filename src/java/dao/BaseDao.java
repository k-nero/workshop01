/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nero
 */
public class BaseDao {

    protected static <T> T mapRow(ResultSet rs, Class<T> clazz) {
        T obj;

        try {
            // 1. Create a new instance of the target class
            obj = clazz.getDeclaredConstructor().newInstance();

            // 2. Get the ResultSet metadata
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);

                try {
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);

                    Object value = rs.getObject(columnName);

                    field.set(obj, value);

                } catch (NoSuchFieldException e) {
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException | SQLException e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }

    protected static <T> List<T> mapToList(ResultSet rs, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(mapRow(rs, clazz));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    protected static <T> String selectFrom(Class<T> clazz) {
        String sql = "select ";
        try {

            for (int i = 0; i <= clazz.getDeclaredFields().length; i++) {
                sql += clazz.getDeclaredFields()[i].getName();
                if (i != clazz.getDeclaredFields().length - 1) {
                    sql += ", ";
                }
            }

            return sql;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
