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
        Connection conn = null;
    try {
        // Establecer conexión a la base de datos usando la clase Conexion
        Conexion conexion = new Conexion();
        conn = conexion.conectar();

        // Preparar la consulta SQL para verificar las credenciales
        String sql = "SELECT tp.TbTP_Tipo_Persona FROM tb_usuarios AS u JOIN tb_tipo_persona AS tp ON u.TbU_Tipo_Persona = tp.TbTP_ID_Tipo_Persona WHERE u.TbU_Email = ? AND u.TbU_ID_Contraseña = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, this.email);
        stmt.setString(2, this.contraseña);

        // Ejecutar la consulta
        ResultSet rs = stmt.executeQuery();

        // Verificar si se encontró algún resultado
        if (rs.next()) {
            // Obtener el tipo de usuario y devolverlo
            return rs.getString("TbTP_Tipo_Persona");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al verificar las credenciales: " + e.getMessage());
    } finally {
        // Asegurarse de cerrar la conexión
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    // Si no se encontraron coincidencias, devolver null
    return null;
}
}
