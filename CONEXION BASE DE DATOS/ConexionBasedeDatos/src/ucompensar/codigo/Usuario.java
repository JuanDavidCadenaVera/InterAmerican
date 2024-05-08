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

public boolean verificarUsuario(Connection conn) {
        boolean existeUsuario = false;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT COUNT(*) AS total FROM tb_usuarios WHERE TbU_Email = ? AND TbU_ID_Contraseña = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                existeUsuario = total > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el usuario: " + e.getMessage());
        } finally {
            // Cierre de recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar los recursos: " + ex.getMessage());
            }
        }

        return existeUsuario;
    }
}