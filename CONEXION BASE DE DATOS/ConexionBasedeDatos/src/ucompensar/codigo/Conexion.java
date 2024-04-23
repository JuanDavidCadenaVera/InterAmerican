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
    Connection cx = null;
    
    

    public Conexion(String bd) {
        this.bd = bd;
    }
    
    public Connection conectar() {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + bd, user, password);
             System.out.println("Se conecto a base de datos " + bd);
    
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No se conecto a base de datos " + bd);
}
        return cx;
    }
    
    public void desconectar(){
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

    
