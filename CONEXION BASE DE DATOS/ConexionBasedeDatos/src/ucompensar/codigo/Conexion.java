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
    private String password = "B0G0TA2005..";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn = null;



    public Connection conectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                return conn; 
            }
            Class.forName(driver); 
            conn = DriverManager.getConnection(url + bd, user, password);
            System.out.println("Se conectó correctamente a la base de datos " + bd);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No se conectó correctamente a la base de datos " + bd + ": " + e.getMessage());
        }
        return conn;
    }

    public void desconectar() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Se desconectó correctamente de la base de datos " + bd);
            } catch (SQLException ex) {
                System.out.println("No se desconectó la base de datos: " + bd + ": " + ex.getMessage());
            }
        }
    }
}

    
