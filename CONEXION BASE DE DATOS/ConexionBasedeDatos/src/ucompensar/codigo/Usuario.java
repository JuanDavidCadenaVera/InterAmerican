/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fabia
 */
public class Usuario {
    private String email;
    private String contraseña;

    // Constructor
    public Usuario(String email, String contraseña) {
        this.email = email;
        this.contraseña = contraseña;
    }

    // Getters y setters
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
    String mensaje = "";
    String sql = "SELECT COUNT(*) AS total FROM tb_usuarios WHERE TbU_Email = ? AND TbU_ID_Contraseña = ?";
    try (Connection conn = new Conexion().conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, email);
        ps.setString(2, contraseña);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int total = rs.getInt("total");
                mensaje = total > 0 ? "El usuario y la contraseña son correctas." : "El usuario y/o la contraseña son incorrectas.";
            }
        }
    } catch (SQLException e) {
        mensaje = "Error al verificar el usuario: " + e.getMessage();
    }
    return mensaje;
}

}

  