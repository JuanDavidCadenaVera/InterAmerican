/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ucompensar.codigo.Conexion;



/**
 *
 * @author 52130424
 */
public class Administrador extends Personas {
    Conexion conexion = new Conexion();
    Connection conn = conexion.conectar();
    

    public Administrador(String email, String contraseña) {
        super(email, null);
    }

    @Override
    public String Consultar() {
        StringBuilder datosEstudiante = new StringBuilder();
        ResultSet rs = null;
        PreparedStatement stmt = null;
    try {
        String sql = "SELECT p.TbP_Nombre, p.TbP_Apellido, p.TbP_Tipo_Documento, p.TbP_ID_Personas, " +
                     "p.TbP_Direccion_Email, p.TbP_Direccion, p.TbP_Fecha_Nacimiento, tp.TbTP_Tipo_Persona " +
                     "FROM tb_personas p " +
                     "JOIN tb_tipo_documento td ON p.TbP_Tipo_Documento = td.TbTD_ID_Tipo_Documento " +
                     "JOIN tb_tipo_persona tp ON p.TbP_Tipo_Personas = tp.TbTP_ID_Tipo_Persona " +
                     "WHERE p.TbP_Direccion_Email = ?";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, getEmail()); 
        rs = stmt.executeQuery();

        if (rs.next()) {
            datosEstudiante.append("Nombre: ").append(rs.getString("TbP_Nombre")).append("\n")
                           .append("Apellido: ").append(rs.getString("TbP_Apellido")).append("\n")
                           .append("Tipo de Documento: ").append(rs.getString("TbP_Tipo_Documento")).append("\n")
                           .append("ID: ").append(rs.getString("TbP_ID_Personas")).append("\n")
                           .append("Email: ").append(rs.getString("TbP_Direccion_Email")).append("\n")
                           .append("Dirección: ").append(rs.getString("TbP_Direccion")).append("\n")
                           .append("Fecha de Nacimiento: ").append(rs.getDate("TbP_Fecha_Nacimiento")).append("\n")
                           .append("Tipo de Persona: ").append(rs.getString("TbTP_Tipo_Persona")).append("\n");
        } else {
            datosEstudiante.append("No se encontraron datos para el email: ").append(getEmail());
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar datos del estudiante: " + e.getMessage());
        datosEstudiante.append("Error al consultar datos: ").append(e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return datosEstudiante.toString();
    }
    
    public Map<String, String> obtenerTipoCedula() {
    Map<String, String> tipoCedulas = new HashMap<>();
    ResultSet rs = null;
    PreparedStatement stmt = null;
    try {
        String sql = "SELECT TbTP_Tipo_Documento , TbTD_ID_Tipo_Documento " +
                     "FROM tb_tipo_documento";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            tipoCedulas.put(rs.getString("TbTP_Tipo_Documento"), rs.getString("TbTD_ID_Tipo_Documento"));
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar tipos de cédula: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return tipoCedulas;
}
    
    public String subirEstudiante(String identificacion, String tipoDocumento, String nombre, String apellido, 
       String telefono, String email, String direccion, String fechaNacimiento) {
    StringBuilder datosEstudiante = new StringBuilder();
    PreparedStatement stmt = null;
    try {
        String sql = "INSERT INTO tb_personas (TbP_ID_Personas, TbP_Tipo_Personas, TbP_Tipo_Documento, "
                + "TbP_Nombre, TbP_Apellido, TbP_Telefono, TbP_Direccion_Email, TbP_Direccion, "
                + "TbP_Fecha_Nacimiento) VALUES (?, 'TP-1', ?, ?, ?, ?, ?, ?, ?)";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, identificacion);
        stmt.setString(2, tipoDocumento);
        stmt.setString(3, nombre);
        stmt.setString(4, apellido);
        stmt.setString(5, telefono);
        stmt.setString(6, email);
        stmt.setString(7, direccion);
        
        // Convertir la fecha de String a java.sql.Date
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = format.parse(fechaNacimiento);
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        stmt.setDate(8, sqlDate);

        int rowsInserted = stmt.executeUpdate();
        if (rowsInserted > 0) {
            datosEstudiante.append("Estudiante ingresado exitosamente.");
        } else {
            datosEstudiante.append("No se pudo ingresar el estudiante.");
        }
    } catch (SQLException | ParseException e) {
        System.err.println("Error al insertar datos del estudiante: " + e.getMessage());
        datosEstudiante.append("Error al insertar datos: ").append(e.getMessage());
    } finally {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return datosEstudiante.toString();
}
    
    public String subirProfesor(String identificacion, String tipoDocumento, String nombre, String apellido, 
       String telefono, String email, String direccion, String fechaNacimiento) {
    StringBuilder datosEstudiante = new StringBuilder();
    PreparedStatement stmt = null;
    try {
        String sql = "INSERT INTO tb_personas (TbP_ID_Personas, TbP_Tipo_Personas, TbP_Tipo_Documento, "
                + "TbP_Nombre, TbP_Apellido, TbP_Telefono, TbP_Direccion_Email, TbP_Direccion, "
                + "TbP_Fecha_Nacimiento) VALUES (?, 'TP-2', ?, ?, ?, ?, ?, ?, ?)";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, identificacion);
        stmt.setString(2, tipoDocumento);
        stmt.setString(3, nombre);
        stmt.setString(4, apellido);
        stmt.setString(5, telefono);
        stmt.setString(6, email);
        stmt.setString(7, direccion);
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = format.parse(fechaNacimiento);
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        stmt.setDate(8, sqlDate);

        int rowsInserted = stmt.executeUpdate();
        if (rowsInserted > 0) {
            datosEstudiante.append("Estudiante ingresado exitosamente.");
        } else {
            datosEstudiante.append("No se pudo ingresar el estudiante.");
        }
    } catch (SQLException | ParseException e) {
        System.err.println("Error al insertar datos del estudiante: " + e.getMessage());
        datosEstudiante.append("Error al insertar datos: ").append(e.getMessage());
    } finally {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return datosEstudiante.toString();
}


    
}
