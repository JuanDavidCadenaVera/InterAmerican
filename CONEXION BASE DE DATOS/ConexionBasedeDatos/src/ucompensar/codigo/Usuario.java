/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author fabia
 */
public class Usuario {

    private String email;
    private String contraseña;

    public Usuario(String email, String contraseña) {
        this.email = email;
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String verificarCredenciales() {
        Connection conn ;
    try {
        Conexion conexion = new Conexion();
        conn = conexion.conectar();

        String sql = "SELECT tp.TbTP_Tipo_Persona FROM tb_usuarios AS u JOIN tb_tipo_persona AS tp ON u.TbU_Tipo_Persona = tp.TbTP_ID_Tipo_Persona WHERE u.TbU_Email = ? AND u.TbU_ID_Contraseña = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, contraseña);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getString("TbTP_Tipo_Persona");
        }
    } catch (SQLException e) {
        
    }
    return null;
    }
}
