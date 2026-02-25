/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Nero
 */
public class DbContext {
     public static Connection getConnection() throws Exception {
        String host="localhost";
        String port="1433";
        String uid="sa";
        String pwd="123456@Admin";
        String db="workshop01";
        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://" +host+":"+port
                 +";databasename="+db+";user="+uid+";password="+pwd+";TrustServerCertificate=True;sendStringAsUnicode=true;";
        forName(driver);
        return DriverManager.getConnection(url);
    }
}
