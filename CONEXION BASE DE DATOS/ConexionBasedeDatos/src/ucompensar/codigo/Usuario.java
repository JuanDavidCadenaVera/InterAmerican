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
        Conexion C = new Conexion();
        Connection conn = C.conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (conn != null) {
            try {
                String sql = "SELECT COUNT(*) AS total FROM tb_usuarios WHERE TbU_Email = ? AND TbU_ID_Contraseña = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, contraseña);
                rs = ps.executeQuery();

                if (rs.next()) {
                    int total = rs.getInt("total");
                    if (total > 0) {
                        mensaje = "El usuario y la contraseña son correctas.";
                    } else {
                        mensaje = "El usuario y/o la contraseña son incorrectas.";
                    }
                }
            } catch (SQLException e) {
                mensaje = "Error al verificar el usuario: " + e.getMessage();
            } finally {
                // Cierre de recursos
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    conn.close();
                } catch (SQLException ex) {
                    mensaje = "Error al cerrar los recursos: " + ex.getMessage();
                }
            }
        } else {
            mensaje = "No se pudo establecer la conexión a la base de datos.";
        }

        return mensaje;
    }
}

  