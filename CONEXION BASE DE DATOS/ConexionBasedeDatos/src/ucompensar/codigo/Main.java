package ucompensar.codigo;
import java.sql.Connection;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author JUAN DAVID
 */
public class Main {
    public static void main(String[] args) {
        Conexion C = new Conexion();
        Connection conn = C.conectar(); 
        Usuario U = new Usuario("cadenaverajuandavid@gmail.com", "1029141151");
        U.verificarUsuario(conn);

    }
}
