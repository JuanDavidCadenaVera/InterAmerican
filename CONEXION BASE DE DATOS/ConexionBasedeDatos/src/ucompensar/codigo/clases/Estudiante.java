/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Time;
import ucompensar.codigo.Conexion;


/**
 *
 * @author 52130424
 */
public class Estudiante extends Personas implements Horario, Nivel{
    Conexion conexion = new Conexion();
    Connection conn = conexion.conectar();
    
    private String tipoPersona = "TP-1";
    
    
    public Estudiante(String identificacion, String tipoDocumento, String nombre, String apellido, String email, String direccion, Date fechaNacimiento, String tipoPersona) {
        super(identificacion, tipoDocumento, nombre, apellido, email, direccion, fechaNacimiento);
        this.tipoPersona = tipoPersona;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public void Consultar() {
        try {
            String sql = "SELECT p.TbP_Nombre, p.TbP_Apellido, p.TbP_Tipo_Documento, p.TbP_ID_Personas, " +
                         "p.TbP_Direccion_Email, p.TbP_Direccion, p.TbP_Fecha_Nacimiento, tp.TbTP_Tipo_Persona " +
                         "FROM tb_personas p " +
                         "JOIN tb_tipo_documento td ON p.TbP_Tipo_Documento = td.TbTD_ID_Tipo_Documento " +
                         "JOIN tb_tipo_persona tp ON p.TbP_Tipo_Personas = tp.TbTP_ID_Tipo_Persona " +
                         "WHERE p.TbP_ID_Personas = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, getIdentificacion());  // Asigna el ID del estudiante al parámetro
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                setNombre(rs.getString("TbP_Nombre"));
                setApellido(rs.getString("TbP_Apellido"));
                setTipoDocumento(rs.getString("TbP_Tipo_Documento"));
                setIdentificacion(rs.getString("TbP_ID_Personas"));
                setEmail(rs.getString("TbP_Direccion_Email"));
                setDireccion(rs.getString("TbP_Direccion"));
                setFechaNacimiento(rs.getDate("TbP_Fecha_Nacimiento"));
                this.tipoPersona = rs.getString("TbTP_Tipo_Persona");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error al cargar datos del estudiante: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public void horario(String TbH_ID_Horario, String TbH_Dia_Semana, Date TbH_Hora_Inicio, Date TbH_Hora_Final) {
        try {
        String sql = "SELECT h.TbH_ID_Horario, h.TbH_Dia_Semana, h.TbH_Hora_Inicio, h.TbH_Hora_Final " +
                     "FROM tb_horario h " +
                     "JOIN tb_nivel_matricula nm ON h.TbH_ID_Horario = nm.TbNM_ID_Horario " +
                     "JOIN tb_matricula m ON nm.TbNM_ID_Matricula = m.TbM_ID_Matricula " +
                     "WHERE m.TbM_ID_Persona = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, getIdentificacion());
        ResultSet rs = stmt.executeQuery();

        // Asumiendo que los tipos de datos son correctos y que solo se trata de un problema en la lectura
        while (rs.next()) {
            String horarioID = rs.getString("TbH_ID_Horario");
            String diaSemana = rs.getString("TbH_Dia_Semana");
            Time horaInicio = rs.getTime("TbH_Hora_Inicio");
            Time horaFinal = rs.getTime("TbH_Hora_Final");

            System.out.println("Horario: " + horarioID + "\n" +
                               "Dia: " + diaSemana + "\n" +
                               "Hora Inicio: " + horaInicio + "\n" + // SimpleDateFormat ya no es necesario aquí
                               "Hora Final: " + horaFinal);
        }
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        System.err.println("Error al obtener el horario del estudiante: " + e.getMessage());
    }
}

    @Override
    public void nivel(String TbN_ID_Nivel) {
        try {
            String sql = "SELECT n.TbN_ID_Nivel, n.TbN_Duracion_Horas " +
                         "FROM tb_nivel n " +
                         "JOIN tb_nivel_matricula nm ON n.TbN_ID_Nivel = nm.TbNM_ID_Nivel " +
                         "JOIN tb_matricula m ON nm.TbNM_ID_Matricula = m.TbM_ID_Matricula " +
                         "WHERE m.TbM_ID_Persona = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, getIdentificacion());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nivelID = rs.getString("TbN_ID_Nivel");
                String duracionHoras = rs.getString("TbN_Duracion_Horas");

                System.out.println("Nivel ID: " + nivelID + "\n" +
                                   "Duración del Nivel (Horas): " + duracionHoras);
            } else {
                System.out.println("No se encontraron datos de nivel para el ID de estudiante: " + getIdentificacion());
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el nivel del estudiante: " + e.getMessage());
        }
    }
}
