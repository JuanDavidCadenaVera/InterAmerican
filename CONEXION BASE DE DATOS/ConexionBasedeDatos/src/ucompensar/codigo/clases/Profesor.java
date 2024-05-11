/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo.clases;

import ucompensar.codigo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 52130424
 */
public class Profesor extends Personas implements CargaDocente,EstudiantesP{
    Conexion conexion = new Conexion();
    Connection conn = conexion.conectar();
    
    private String tipoPersona = "TP-2";

    public Profesor(String email, String contraseña) {
        super(email, contraseña);
    }


    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public String Consultar() {
         StringBuilder datosProfesor = new StringBuilder();
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
        stmt.setString(1, getEmail());  // Asegúrate de que getEmail() devuelve el email correcto
        rs = stmt.executeQuery();

        if (rs.next()) {
            datosProfesor.append("Nombre: ").append(rs.getString("TbP_Nombre")).append("\n")
                           .append("Apellido: ").append(rs.getString("TbP_Apellido")).append("\n")
                           .append("Tipo de Documento: ").append(rs.getString("TbP_Tipo_Documento")).append("\n")
                           .append("ID: ").append(rs.getString("TbP_ID_Personas")).append("\n")
                           .append("Email: ").append(rs.getString("TbP_Direccion_Email")).append("\n")
                           .append("Dirección: ").append(rs.getString("TbP_Direccion")).append("\n")
                           .append("Fecha de Nacimiento: ").append(rs.getDate("TbP_Fecha_Nacimiento")).append("\n")
                           .append("Tipo de Persona: ").append(rs.getString("TbTP_Tipo_Persona")).append("\n");
        } else {
            datosProfesor.append("No se encontraron datos para el email: ").append(getEmail());
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar datos del estudiante: " + e.getMessage());
        datosProfesor.append("Error al consultar datos: ").append(e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return datosProfesor.toString();
    }

    
    @Override
    public String carga() {
        StringBuilder cargaLaboral = new StringBuilder();
    ResultSet rs = null;
    PreparedStatement stmt = null;
    try {
        String sql = "SELECT " +
             "cd.TbCD_Horas_Trabajadas AS horas_trabajadas, " +
             "c.TbC_Fecha AS fecha_ingreso_empresa " +
             "FROM " +
             "tb_carga_docente cd " +
             "JOIN " +
             "tb_contrato c ON cd.TbCD_Contrato = c.TbC_ID_Contrato " +
             "JOIN " +
             "tb_personas p ON c.TbC_ID_Personas = p.TbP_ID_Personas " +
             "WHERE " +
             "p.TbP_Direccion_Email = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, getEmail()); // Asegúrate de que getEmail() devuelve el email correcto
        rs = stmt.executeQuery();

        if (rs.next()) {
            int horasTrabajadas = rs.getInt("horas_trabajadas");
            String fechaIngreso = rs.getString("fecha_ingreso_empresa");

            cargaLaboral.append("Horas trabajadas: ").append(horasTrabajadas).append(" horas\n")
                        .append("Fecha de ingreso a la empresa: ").append(fechaIngreso);
        } else {
            cargaLaboral.append("No se encontraron datos de carga laboral para el email: ").append(getEmail());
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar datos de carga laboral del docente: " + e.getMessage());
        cargaLaboral.append("Error al consultar datos de carga laboral: ").append(e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return cargaLaboral.toString();
    }

    @Override
    public String Estudiantes() {
    StringBuilder estudiantes = new StringBuilder();
    ResultSet rs = null;
    PreparedStatement stmt = null;
    try {
        String sql = "SELECT DISTINCT m.TbM_ID_Persona, p.TbP_Nombre, p.TbP_Apellido " +
                     "FROM tb_matricula m " +
                     "JOIN tb_horario h ON m.TbM_ID_Programa = h.TbH_ID_Horario " +
                     "JOIN tb_personas p ON m.TbM_ID_Persona = p.TbP_ID_Personas " +
                     "WHERE h.TbH_Dia_Semana = ?";

        stmt = conn.prepareStatement(sql);
        // Aquí deberías establecer el día de la semana en el que el profesor quiere ver a sus estudiantes
        stmt.setString(1, "Lunes"); // Ejemplo: "Lunes", "Martes", etc.

        rs = stmt.executeQuery();

        if (rs.next()) {
            estudiantes.append("Estudiantes con clase el mismo día:\n");
            do {
                String nombre = rs.getString("TbP_Nombre");
                String apellido = rs.getString("TbP_Apellido");
                estudiantes.append(nombre).append(" ").append(apellido).append("\n");
            } while (rs.next());
        } else {
            estudiantes.append("No se encontraron estudiantes con clase el mismo día.");
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar estudiantes con clase el mismo día: " + e.getMessage());
        estudiantes.append("Error al consultar estudiantes: ").append(e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return estudiantes.toString();
}}


