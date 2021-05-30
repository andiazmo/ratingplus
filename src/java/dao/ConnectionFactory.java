/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class ConnectionFactory {
    
//Local
//    private String url = "jdbc:postgresql://localhost:5432/testdb";
//    private String user = "postgres";
//    private String passw = "verde";
//Test
//    private String url = "jdbc:postgresql://localhost:5432/testdb";
//     private String user = "postgres";
//    private String passw = "santanderweb";
        
    private Connection connection;
    
    
    public static Connection getConnection(){
        // Credential
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String passw = "test";
        
        Connection cn = null;
        try {
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection(url,user, passw);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return cn;
    }
    
    public static void main(String[] args) {
        
        Connection connect = ConnectionFactory.getConnection();
        
        System.out.println("connect:   "+connect.toString());
        
    }
    
}
