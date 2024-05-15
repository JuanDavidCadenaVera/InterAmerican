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
import java.util.ArrayList;

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
        String sql = "SELECT " +
                     "c.TbCo_ID_Curso AS ID_Curso, " +
                     "CONCAT(e.TbP_Nombre, ' ', e.TbP_Apellido) AS Nombre_Estudiante, " +
                     "c.TbCo_ID_Nivel AS ID_Nivel, " +
                     "h.TbH_Dia_Semana AS Dia_Semana, " +
                     "TIME_FORMAT(h.TbH_Hora_Inicio, '%H:%i') AS Hora_Inicio, " +
                     "TIME_FORMAT(h.TbH_Hora_Final, '%H:%i') AS Hora_Final, " +
                     "c.TbCo_ID_Contrato AS ID_Contrato, " +
                     "CONCAT(p_profesor.TbP_Nombre, ' ', p_profesor.TbP_Apellido) AS Nombre_Profesor " +
                     "FROM " +
                     "tb_curso c " +
                     "INNER JOIN " +
                     "tb_contrato co ON c.TbCo_ID_Contrato = co.TbC_ID_Contrato " +
                     "INNER JOIN " +
                     "tb_matricula m ON c.TbCo_ID_Matricula = m.TbM_ID_Matricula " +
                     "INNER JOIN " +
                     "tb_personas e ON m.TbM_ID_Persona = e.TbP_ID_Personas " +
                     "INNER JOIN " +
                     "tb_personas p_profesor ON co.TbC_ID_Personas = p_profesor.TbP_ID_Personas " +
                     "INNER JOIN " +
                     "tb_horario h ON c.TbCo_ID_Horario = h.TbH_ID_Horario " +
                     "WHERE " +
                     "p_profesor.TbP_Direccion_Email = ?";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, getEmail()); // Reemplaza getEmail() con el correo electrónico del profesor

        rs = stmt.executeQuery();

        if (rs.next()) {
            estudiantes.append("Estudiantes inscritos en los cursos del profesor:\n\n");
            do {
                String idCurso = rs.getString("ID_Curso");
                String nombreEstudiante = rs.getString("Nombre_Estudiante");
                String idNivel = rs.getString("ID_Nivel");
                String diaSemana = rs.getString("Dia_Semana");
                String horaInicio = rs.getString("Hora_Inicio");
                String horaFinal = rs.getString("Hora_Final");
                String idContrato = rs.getString("ID_Contrato");
                String nombreProfesor = rs.getString("Nombre_Profesor");
                
                // Agrega la información al StringBuilder
                estudiantes.append("ID del Curso: ").append(idCurso).append("\n");
                estudiantes.append("Nombre del Estudiante: ").append(nombreEstudiante).append("\n");
                estudiantes.append("ID del Nivel: ").append(idNivel).append("\n");
                estudiantes.append("Día de la semana: ").append(diaSemana).append("\n");
                estudiantes.append("Hora de inicio: ").append(horaInicio).append("\n");
                estudiantes.append("Hora final: ").append(horaFinal).append("\n");
                estudiantes.append("ID del Contrato: ").append(idContrato).append("\n");
                estudiantes.append("Nombre del Profesor: ").append(nombreProfesor).append("\n\n");
            } while (rs.next());
        } else {
            estudiantes.append("No se encontraron estudiantes inscritos en los cursos del profesor.");
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar estudiantes inscritos en los cursos del profesor: " + e.getMessage());
        estudiantes.append("Error al consultar estudiantes inscritos en los cursos del profesor: ").append(e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return estudiantes.toString();
}
   
    
    public String[] obtenerNombresEstudiantes() {
    ArrayList<String> nombres = new ArrayList<>();
    ResultSet rs = null;
    PreparedStatement stmt = null;
    try {
        String sql = "SELECT CONCAT(e.TbP_Nombre, ' ', e.TbP_Apellido) AS Nombre_Estudiante " +
                     "FROM tb_curso c " +
                     "INNER JOIN tb_contrato co ON c.TbCo_ID_Contrato = co.TbC_ID_Contrato " +
                     "INNER JOIN tb_matricula m ON c.TbCo_ID_Matricula = m.TbM_ID_Matricula " +
                     "INNER JOIN tb_personas e ON m.TbM_ID_Persona = e.TbP_ID_Personas " +
                     "INNER JOIN tb_personas p_profesor ON co.TbC_ID_Personas = p_profesor.TbP_ID_Personas " +
                     "INNER JOIN tb_horario h ON c.TbCo_ID_Horario = h.TbH_ID_Horario " +
                     "WHERE p_profesor.TbP_Direccion_Email = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, getEmail()); // Reemplaza getEmail() con el correo electrónico del profesor
        rs = stmt.executeQuery();
        while (rs.next()) {
            nombres.add(rs.getString("Nombre_Estudiante"));
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar nombres de estudiantes: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return nombres.toArray(new String[0]);
}

public void ingresarNota(String nombreEstudiante, double nota) {
        PreparedStatement stmt = null;
        try {
            // Obtener el ID de matrícula del estudiante
            String idMatricula = obtenerIdMatricula(nombreEstudiante);

            // Obtener un ID de curso válido
            String idCurso = obtenerIdCursoValido();

            // Consulta SQL para insertar la nota
            String sql = "INSERT INTO tb_notas (TbN_ID_Nota, TbN_ID_Curso, TbN_ID_Matricula) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, nota);
            stmt.setString(2, idCurso);
            stmt.setString(3, idMatricula);

            // Ejecutar la consulta
            stmt.executeUpdate();

            System.out.println("Nota ingresada correctamente para " + nombreEstudiante);
        } catch (SQLException e) {
            System.err.println("Error al ingresar la nota: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
    }

    private String obtenerIdMatricula(String nombreEstudiante) {
        String idMatricula = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Consulta para obtener el ID de matrícula basado en el nombre del estudiante
            String sql = "SELECT TbM_ID_Matricula FROM tb_matricula " +
                         "JOIN tb_personas ON tb_matricula.TbM_ID_Persona = tb_personas.TbP_ID_Personas " +
                         "WHERE tb_personas.TbP_Nombre = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreEstudiante);
            rs = stmt.executeQuery();

            if (rs.next()) {
                idMatricula = rs.getString("TbM_ID_Matricula");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el ID de matrícula: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return idMatricula;
    }

    private String obtenerIdCursoValido() {
        String idCurso = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Consulta para obtener un ID de curso válido
            String sql = "SELECT TbCo_ID_Curso FROM tb_curso LIMIT 1";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                idCurso = rs.getString("TbCo_ID_Curso");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el ID del curso: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return idCurso;
    }

    public String EstudiantesActualizados() {
    StringBuilder estudiantes = new StringBuilder();
    ResultSet rs = null;
    PreparedStatement stmt = null;
    try {
        String sql = "SELECT\n" +
        "c.TbCo_ID_Curso AS ID_Curso,\n" +
        "CONCAT(e.TbP_Nombre, ' ', e.TbP_Apellido) AS Nombre_Estudiante,\n" +
        "c.TbCo_ID_Nivel AS ID_Nivel,\n" +
        "h.TbH_Dia_Semana AS Dia_Semana,\n" +
        "TIME_FORMAT(h.TbH_Hora_Inicio, '%H:%i') AS Hora_Inicio,\n" +
        "TIME_FORMAT(h.TbH_Hora_Final, '%H:%i') AS Hora_Final,\n" +
        "c.TbCo_ID_Contrato AS ID_Contrato,\n" +
        "CONCAT(p_profesor.TbP_Nombre, ' ', p_profesor.TbP_Apellido) AS Nombre_Profesor,\n" +
        "n.TbN_ID_Nota AS Nota\n" +
        "FROM\n" +
        "tb_curso c\n" +
        "INNER JOIN tb_contrato co ON c.TbCo_ID_Contrato = co.TbC_ID_Contrato\n" +
        "INNER JOIN tb_matricula m ON c.TbCo_ID_Matricula = m.TbM_ID_Matricula\n" +
        "INNER JOIN tb_personas e ON m.TbM_ID_Persona = e.TbP_ID_Personas\n" +
        "INNER JOIN tb_personas p_profesor ON co.TbC_ID_Personas = p_profesor.TbP_ID_Personas\n" +
        "INNER JOIN tb_horario h ON c.TbCo_ID_Horario = h.TbH_ID_Horario\n" +
        "LEFT JOIN tb_notas n ON m.TbM_ID_Matricula = n.TbN_ID_Matricula AND c.TbCo_ID_Curso = n.TbN_ID_Curso\n" +
        "WHERE\n" +
        "p_profesor.TbP_Direccion_Email = ?;";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, getEmail()); // Reemplaza getEmail() con el correo electrónico del profesor

        rs = stmt.executeQuery();

        if (rs.next()) {
            estudiantes.append("Estudiantes inscritos en los cursos del profesor:\n\n");
            do {
                String idCurso = rs.getString("ID_Curso");
                String nombreEstudiante = rs.getString("Nombre_Estudiante");
                String idNivel = rs.getString("ID_Nivel");
                String diaSemana = rs.getString("Dia_Semana");
                String horaInicio = rs.getString("Hora_Inicio");
                String horaFinal = rs.getString("Hora_Final");
                String idContrato = rs.getString("ID_Contrato");
                String nombreProfesor = rs.getString("Nombre_Profesor");
                String nota = rs.getString("Nota");
                
                // Agrega la información al StringBuilder
                estudiantes.append("ID del Curso: ").append(idCurso).append("\n");
                estudiantes.append("Nombre del Estudiante: ").append(nombreEstudiante).append("\n");
                estudiantes.append("ID del Nivel: ").append(idNivel).append("\n");
                estudiantes.append("Día de la semana: ").append(diaSemana).append("\n");
                estudiantes.append("Hora de inicio: ").append(horaInicio).append("\n");
                estudiantes.append("Hora final: ").append(horaFinal).append("\n");
                estudiantes.append("ID del Contrato: ").append(idContrato).append("\n");
                estudiantes.append("Nombre del Profesor: ").append(nombreProfesor).append("\n");
                estudiantes.append("Nota: ").append(nota).append("\n\n");
            } while (rs.next());
        } else {
            estudiantes.append("No se encontraron estudiantes inscritos en los cursos del profesor.");
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar estudiantes inscritos en los cursos del profesor: " + e.getMessage());
        estudiantes.append("Error al consultar estudiantes inscritos en los cursos del profesor: ").append(e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return estudiantes.toString();
}
    
    public String EstudiantesNotas() {
    StringBuilder estudiantesNotas = new StringBuilder();
    ResultSet rs = null;
    PreparedStatement stmt = null;
    try {
        String sql = "SELECT CONCAT(e.TbP_Nombre, ' ', e.TbP_Apellido) AS Nombre_Estudiante,\n" +
            "c.TbCo_ID_Curso AS ID_Curso,\n" +
            "n.TbN_ID_Nota AS Nota \n" +
            "FROM tb_curso c \n" +
            "INNER JOIN tb_contrato co ON c.TbCo_ID_Contrato = co.TbC_ID_Contrato \n" +
            "INNER JOIN tb_matricula m ON c.TbCo_ID_Matricula = m.TbM_ID_Matricula \n" +
            "INNER JOIN tb_personas e ON m.TbM_ID_Persona = e.TbP_ID_Personas \n" +
            "INNER JOIN tb_personas p_profesor ON co.TbC_ID_Personas = p_profesor.TbP_ID_Personas \n" +
            "LEFT JOIN tb_notas n ON m.TbM_ID_Matricula = n.TbN_ID_Matricula AND c.TbCo_ID_Curso = n.TbN_ID_Curso \n" +
            "WHERE p_profesor.TbP_Direccion_Email = 'fabian13426@gmail.com'";

        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();

        if (rs.next()) {
            estudiantesNotas.append("Estudiantes y sus notas en los cursos del profesor:\n\n");
            do {
                String nombreEstudiante = rs.getString("Nombre_Estudiante");
                String idCurso = rs.getString("ID_Curso");
                String nota = rs.getString("Nota");

                // Agrega la información al StringBuilder
                estudiantesNotas.append("Nombre del Estudiante: ").append(nombreEstudiante).append("\n");
                estudiantesNotas.append("ID del Curso: ").append(idCurso).append("\n");
                estudiantesNotas.append("Nota: ").append(nota).append("\n\n");
            } while (rs.next());
        } else {
            estudiantesNotas.append("No se encontraron estudiantes y sus notas en los cursos del profesor.");
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar estudiantes y sus notas en los cursos del profesor: " + e.getMessage());
        estudiantesNotas.append("Error al consultar estudiantes y sus notas en los cursos del profesor: ").append(e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return estudiantesNotas.toString();
            
        }    
}





