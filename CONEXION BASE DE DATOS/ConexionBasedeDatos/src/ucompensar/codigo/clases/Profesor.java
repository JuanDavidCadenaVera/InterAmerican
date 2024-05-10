/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import ucompensar.codigo.Conexion;

/**
 *
 * @author 52130424
 */
public class Profesor extends Personas implements Horario, CargaDocente{
     Conexion conexion = new Conexion();
     Connection conn = conexion.conectar();
        
    private String tipoPersona = "TP-2";

    public Profesor(String tipoPersona ,String identificacion, String tipoDocumento, String nombre, String apellido, String email, String direccion, Date fechaNacimiento) {
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
            stmt.setString(1, getIdentificacion());  
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
    public void carga(String TbCD_ID_Detalles, String TbCD_Contrato, String TbCD_Horas_Trabajadas, String TbCD_Dia_Semana) {
        try {
            String sql = "SELECT cd.TbCD_ID_Detalles, cd.TbCD_Contrato, cd.TbCD_Horas_Trabajadas, cd.TbCD_Dia_Semana " +
                         "FROM tb_carga_docente cd " +
                         "JOIN tb_contrato c ON cd.TbCD_Contrato = c.TbC_ID_Contrato " +
                         "WHERE c.TbC_ID_Personas = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, getIdentificacion());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String idDetalles = rs.getString("TbCD_ID_Detalles");
                String contrato = rs.getString("TbCD_Contrato");
                int horasTrabajadas = rs.getInt("TbCD_Horas_Trabajadas");
                String diaSemana = rs.getString("TbCD_Dia_Semana");

                System.out.println("Carga Docente: \n" +
                                   "ID Detalles: " + idDetalles + "\n" +
                                   "Contrato: " + contrato + "\n" +
                                   "Horas Trabajadas: " + horasTrabajadas + "\n" +
                                   "Día de la Semana: " + diaSemana);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error al obtener la carga docente del profesor: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}
