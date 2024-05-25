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
public class Estudiante extends Personas implements Horario, Nivel{
    Conexion conexion = new Conexion();
    Connection conn = conexion.conectar();
    

    public Estudiante(String email, String contraseña) {
        super(email, contraseña);
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

    @Override
    public String horario() {
    StringBuilder sb = new StringBuilder();
    try {
        String sql = "SELECT DISTINCT h.TbH_ID_Horario, h.TbH_Dia_Semana, h.TbH_Hora_Inicio, h.TbH_Hora_Final, " +
                     "c.TbCo_ID_Curso, cn.TbCN_Nombre_Curso " +
                     "FROM tb_horario h " +
                     "JOIN tb_nivel_matricula nm ON h.TbH_ID_Horario = nm.TbNM_ID_Horario " +
                     "JOIN tb_curso c ON nm.TbNM_ID_Curso = c.TbCo_ID_Curso " +
                     "JOIN tb_curso_nombre cn ON c.TbCo_ID_Curso = cn.TbCN_ID_Curso " +
                     "JOIN tb_matricula m ON nm.TbNM_ID_Matricula = m.TbM_ID_Matricula " +
                     "JOIN tb_personas p ON m.TbM_ID_Persona = p.TbP_ID_Personas " +
                     "JOIN tb_usuarios u ON p.TbP_ID_Personas = u.TbU_ID_Contraseña " +
                     "WHERE u.TbU_Email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, getEmail());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            sb.append("Día de la Semana: ").append(rs.getString("TbH_Dia_Semana")).append("\n")
              .append("Hora Inicio Clase: ").append(rs.getTime("TbH_Hora_Inicio")).append("\n")
              .append("Hora Finalizacion Clase: ").append(rs.getTime("TbH_Hora_Final")).append("\n")
              .append("ID del Curso: ").append(rs.getString("TbCo_ID_Curso")).append("\n")
              .append("Nombre del Curso: ").append(rs.getString("TbCN_Nombre_Curso")).append("\n\n");
        }
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        System.err.println("Error al obtener el horario del usuario: " + e.getMessage());
        sb.append("Error al cargar horarios: ").append(e.getMessage());
    }
    return sb.toString();
}

    @Override
    public String nivel() {
    StringBuilder sb = new StringBuilder();
    try {
        String sql = "SELECT n.TbN_ID_Nivel, n.TbN_Duracion_Horas, no.TbN_ID_Nota " +
                     "FROM tb_nivel n " +
                     "JOIN tb_nivel_matricula nm ON n.TbN_ID_Nivel = nm.TbNM_ID_Nivel " +
                     "JOIN tb_matricula m ON nm.TbNM_ID_Matricula = m.TbM_ID_Matricula " +
                     "JOIN tb_personas p ON m.TbM_ID_Persona = p.TbP_ID_Personas " +
                     "JOIN tb_usuarios u ON p.TbP_ID_Personas = u.TbU_ID_Contraseña " +
                     "JOIN tb_notas no ON m.TbM_ID_Matricula = no.TbN_ID_Matricula " +
                     "WHERE u.TbU_Email = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, getEmail());
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String nivelID = rs.getString("TbN_ID_Nivel");
            String duracionHoras = rs.getString("TbN_Duracion_Horas");
            float nota = rs.getFloat("TbN_ID_Nota");

            sb.append("Nivel: ").append(nivelID).append("\n")
              .append("Duración: ").append(duracionHoras).append("\n")
              .append("Nota: ").append(nota).append("\n");
        } else {
            sb.append("No se encontraron datos de nivel para el ID de estudiante: ").append(getIdentificacion()).append("\n");
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        System.err.println("Error al obtener el nivel del estudiante: " + e.getMessage());
        sb.append("Error al obtener el nivel del estudiante: ").append(e.getMessage()).append("\n");
    }
    return sb.toString();
}
}
