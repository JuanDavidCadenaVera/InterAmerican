package ucompensar.codigo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author JUAN DAVID
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String bd ="ingles"; 
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password = "InterAmerican2024";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn ;



    public Connection conectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                return conn; 
            }
            Class.forName(driver); 
            conn = DriverManager.getConnection(url + bd, user, password);
            System.out.println("");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}

    
