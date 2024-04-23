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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    String bd = "ingles"; 
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "B0G0TA2005..";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection conn = null;
    
    

    public Conexion(String bd) {
        this.bd = bd;
    }
    
    public Connection conectar() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + bd, user, password);
             System.out.println("Se conecto correctamente a la base de datos " + bd);
    
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No se conecto correctamente a la base de datos " + bd);
}
        return conn;
    }
    
    public void desconectar(){
        try {
            conn.close();
            System.out.println("Se deconecto correctamente la base de datos " + bd);
        } catch (SQLException ex) {
            System.out.println("No se desconecto la base de datos: " + bd);
        }
    }
    
    
}

    
