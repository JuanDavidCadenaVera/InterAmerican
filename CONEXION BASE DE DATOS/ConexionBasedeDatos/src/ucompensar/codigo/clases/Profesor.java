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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 52130424
 */
public class Profesor extends Personas implements CargaDocente, EstudiantesP {

    Conexion conexion = new Conexion();
    Connection conn = conexion.conectar();
    private Map<String, List<Double>> notasEstudiantes = new HashMap<>();

    public Profesor(String email, String contraseña) {
        super(email, contraseña);
    }

    @Override
    public String Consultar() {
        StringBuilder datosProfesor = new StringBuilder();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT p.TbP_Nombre, p.TbP_Apellido, p.TbP_Tipo_Documento, p.TbP_ID_Personas, "
                    + "p.TbP_Direccion_Email, p.TbP_Direccion, p.TbP_Fecha_Nacimiento, tp.TbTP_Tipo_Persona "
                    + "FROM tb_personas p "
                    + "JOIN tb_tipo_documento td ON p.TbP_Tipo_Documento = td.TbTD_ID_Tipo_Documento "
                    + "JOIN tb_tipo_persona tp ON p.TbP_Tipo_Personas = tp.TbTP_ID_Tipo_Persona "
                    + "WHERE p.TbP_Direccion_Email = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, getEmail());
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
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
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
            String sql = "SELECT "
                    + "cd.TbCD_Horas_Trabajadas AS horas_trabajadas, "
                    + "c.TbC_Fecha AS fecha_ingreso_empresa "
                    + "FROM "
                    + "tb_carga_docente cd "
                    + "JOIN "
                    + "tb_contrato c ON cd.TbCD_Contrato = c.TbC_ID_Contrato "
                    + "JOIN "
                    + "tb_personas p ON c.TbC_ID_Personas = p.TbP_ID_Personas "
                    + "WHERE "
                    + "p.TbP_Direccion_Email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, getEmail());
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
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
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
            String sql = "SELECT "
                    + "c.TbCo_ID_Curso AS ID_Curso, "
                    + "CONCAT(e.TbP_Nombre, ' ', e.TbP_Apellido) AS Nombre_Estudiante, "
                    + "c.TbCo_ID_Nivel AS ID_Nivel, "
                    + "h.TbH_Dia_Semana AS Dia_Semana, "
                    + "TIME_FORMAT(h.TbH_Hora_Inicio, '%H:%i') AS Hora_Inicio, "
                    + "TIME_FORMAT(h.TbH_Hora_Final, '%H:%i') AS Hora_Final, "
                    + "c.TbCo_ID_Contrato AS ID_Contrato, "
                    + "CONCAT(p_profesor.TbP_Nombre, ' ', p_profesor.TbP_Apellido) AS Nombre_Profesor "
                    + "FROM "
                    + "tb_curso c "
                    + "INNER JOIN "
                    + "tb_contrato co ON c.TbCo_ID_Contrato = co.TbC_ID_Contrato "
                    + "INNER JOIN "
                    + "tb_matricula m ON c.TbCo_ID_Matricula = m.TbM_ID_Matricula "
                    + "INNER JOIN "
                    + "tb_personas e ON m.TbM_ID_Persona = e.TbP_ID_Personas "
                    + "INNER JOIN "
                    + "tb_personas p_profesor ON co.TbC_ID_Personas = p_profesor.TbP_ID_Personas "
                    + "INNER JOIN "
                    + "tb_horario h ON c.TbCo_ID_Horario = h.TbH_ID_Horario "
                    + "WHERE "
                    + "p_profesor.TbP_Direccion_Email = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, getEmail());

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
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
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
            String sql = "SELECT CONCAT(e.TbP_Nombre, ' ', e.TbP_Apellido) AS Nombre_Estudiante "
                    + "FROM tb_curso c "
                    + "INNER JOIN tb_contrato co ON c.TbCo_ID_Contrato = co.TbC_ID_Contrato "
                    + "INNER JOIN tb_matricula m ON c.TbCo_ID_Matricula = m.TbM_ID_Matricula "
                    + "INNER JOIN tb_personas e ON m.TbM_ID_Persona = e.TbP_ID_Personas "
                    + "INNER JOIN tb_personas p_profesor ON co.TbC_ID_Personas = p_profesor.TbP_ID_Personas "
                    + "INNER JOIN tb_horario h ON c.TbCo_ID_Horario = h.TbH_ID_Horario "
                    + "WHERE p_profesor.TbP_Direccion_Email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, getEmail());
            rs = stmt.executeQuery();
            while (rs.next()) {
                nombres.add(rs.getString("Nombre_Estudiante"));
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar nombres de estudiantes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }
        return nombres.toArray(new String[0]);
    }

    public void ingresarNota(String nombreEstudiante, double nota) {
        List<Double> notas = notasEstudiantes.getOrDefault(nombreEstudiante, new ArrayList<>());
        notas.add(nota);
        notasEstudiantes.put(nombreEstudiante, notas);
    }

    public String EstudiantesNotas() {
        StringBuilder estudiantesNotas = new StringBuilder();
        estudiantesNotas.append("Notas de los estudiantes:\n\n");

        for (Map.Entry<String, List<Double>> entry : notasEstudiantes.entrySet()) {
            String nombreEstudiante = entry.getKey();
            List<Double> notas = entry.getValue();

            for (Double nota : notas) {
                estudiantesNotas.append("Nombre: ").append(nombreEstudiante).append("\n");
                estudiantesNotas.append("Nota: ").append(nota).append("\n\n");
            }
        }

        return estudiantesNotas.toString();
    }

    public Map<String, Map<String, String>> ids() {
        Map<String, Map<String, String>> idsEstudiantes = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : notasEstudiantes.entrySet()) {
            String nombreEstudiante = entry.getKey();
            Map<String, String> ids = new HashMap<>();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                String[] nombres = nombreEstudiante.split(" ");
                if (nombres.length < 2) {
                    System.out.println("El nombre debe contener al menos un nombre y un apellido.");
                    continue;
                }
                String nombre = nombres[0];
                String apellido = nombres[1];

                String sqlPersona = "SELECT TbP_ID_Personas FROM tb_personas WHERE TbP_Nombre = ? AND TbP_Apellido = ?";
                stmt = conn.prepareStatement(sqlPersona);
                stmt.setString(1, nombre);
                stmt.setString(2, apellido);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    ids.put("idPersona", rs.getString("TbP_ID_Personas"));
                } else {
                    System.out.println("No se encontró la persona con nombre y apellido: " + nombreEstudiante);
                    continue;
                }

                String sqlMatricula = "SELECT TbM_ID_Matricula FROM tb_matricula WHERE TbM_ID_Persona = ?";
                stmt = conn.prepareStatement(sqlMatricula);
                stmt.setString(1, ids.get("idPersona"));
                rs = stmt.executeQuery();

                if (rs.next()) {
                    ids.put("idMatricula", rs.getString("TbM_ID_Matricula"));
                } else {
                    System.out.println("No se encontró la matrícula para la persona con nombre y apellido: " + nombreEstudiante);
                    continue;
                }

                String sqlCurso = "SELECT TbCo_ID_Curso FROM tb_curso WHERE TbCo_ID_Matricula = ?";
                stmt = conn.prepareStatement(sqlCurso);
                stmt.setString(1, ids.get("idMatricula"));
                rs = stmt.executeQuery();

                if (rs.next()) {
                    ids.put("idCurso", rs.getString("TbCo_ID_Curso"));
                } else {
                    System.out.println("No se encontró el curso para la matrícula de la persona con nombre y apellido: " + nombreEstudiante);
                }
                idsEstudiantes.put(nombreEstudiante, ids);
            } catch (SQLException e) {
                System.out.println("Error al obtener los IDs para la persona con nombre y apellido " + nombreEstudiante + ": " + e.getMessage());
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException ex) {
                    System.err.println("Error al cerrar recursos: " + ex.getMessage());
                }
            }
        }
        return idsEstudiantes;
    }

    public void actualizarNota() {
        for (Map.Entry<String, List<Double>> entry : notasEstudiantes.entrySet()) {
            String nombreEstudiante = entry.getKey();
            List<Double> notas = entry.getValue();

            Map<String, String> ids = ids().get(nombreEstudiante);

            if (ids == null || ids.get("idMatricula") == null || ids.get("idCurso") == null) {
                System.out.println("No se pudo obtener la matrícula o el curso para " + nombreEstudiante);
                continue;
            }

            for (Double nota : notas) {
                String query = "UPDATE tb_notas SET TbN_ID_Nota = ? WHERE TbN_ID_Matricula = ? AND TbN_ID_Curso = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setDouble(1, nota);
                    pstmt.setString(2, ids.get("idMatricula"));
                    pstmt.setString(3, ids.get("idCurso"));

                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Nota actualizada correctamente para " + nombreEstudiante);
                    } else {
                        System.out.println("No se encontró una nota para actualizar para " + nombreEstudiante);
                    }
                } catch (SQLException e) {
                    System.out.println("Error al actualizar la nota para " + nombreEstudiante + ": " + e.getMessage());
                }
            }
        }
    }

    public String EstudiantesActualizados() {
        StringBuilder estudiantes = new StringBuilder();
        ResultSet rs;
        PreparedStatement stmt;
        try {
            String sql = "SELECT\n"
                    + "CONCAT(e.TbP_Nombre, ' ', e.TbP_Apellido) AS Nombre_Estudiante,\n"
                    + "n.TbN_ID_Nota AS Nota\n"
                    + "FROM\n"
                    + "tb_curso c\n"
                    + "INNER JOIN tb_contrato co ON c.TbCo_ID_Contrato = co.TbC_ID_Contrato\n"
                    + "INNER JOIN tb_matricula m ON c.TbCo_ID_Matricula = m.TbM_ID_Matricula\n"
                    + "INNER JOIN tb_personas e ON m.TbM_ID_Persona = e.TbP_ID_Personas\n"
                    + "INNER JOIN tb_personas p_profesor ON co.TbC_ID_Personas = p_profesor.TbP_ID_Personas\n"
                    + "LEFT JOIN tb_notas n ON m.TbM_ID_Matricula = n.TbN_ID_Matricula AND c.TbCo_ID_Curso = n.TbN_ID_Curso\n"
                    + "WHERE\n"
                    + "p_profesor.TbP_Direccion_Email = ?;";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, getEmail());

            rs = stmt.executeQuery();

            if (rs.next()) {
                estudiantes.append("Estudiantes inscritos en los cursos del profesor:\n\n");
                do {
                    String nombreEstudiante = rs.getString("Nombre_Estudiante");
                    String nota = rs.getString("Nota");

                    estudiantes.append("Nombre del Estudiante: ").append(nombreEstudiante).append("\n");
                    estudiantes.append("Nota: ").append(nota).append("\n\n");
                } while (rs.next());
            } else {
                estudiantes.append("No se encontraron estudiantes inscritos en los cursos del profesor.");
            }
        } catch (SQLException e) {
        }
        return estudiantes.toString();
    }

    // Nuevo método para listar todos los profesores
    public static List<String> listarTodosLosProfesores() {
    List<String> profesores = new ArrayList<>();
    Conexion conexion = new Conexion();
    Connection conn = conexion.conectar();
    ResultSet rs = null;
    PreparedStatement stmt = null;

    try {
        String sql = "SELECT P.TbP_Nombre, P.TbP_Apellido, P.TbP_Direccion_Email, " +
                     "YEAR(CURRENT_DATE) - YEAR(P.TbP_Fecha_Nacimiento) AS Edad, " +
                     "P.TbP_Tipo_Documento, P.TbP_ID_Personas, C.TbC_ID_Contrato, " +
                     "C.TbC_Salario_Hora, P.TbP_Direccion, P.TbP_Telefono " +
                     "FROM tb_personas P " +
                     "JOIN tb_contrato C ON P.TbP_ID_Personas = C.TbC_ID_Personas " +
                     "WHERE P.TbP_Tipo_Personas = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, "TP-2"); // Tipo de persona 'TP-2' para profesores
        rs = stmt.executeQuery();

        while (rs.next()) {
            String nombre = rs.getString("TbP_Nombre");
            String apellido = rs.getString("TbP_Apellido");
            String email = rs.getString("TbP_Direccion_Email");
            int edad = rs.getInt("Edad");
            String tipoDocumento = rs.getString("TbP_Tipo_Documento");
            String cedula = rs.getString("TbP_ID_Personas");
            String idContrato = rs.getString("TbC_ID_Contrato");
            int salarioHora = rs.getInt("TbC_Salario_Hora");
            String direccion = rs.getString("TbP_Direccion");
            String telefono = rs.getString("TbP_Telefono");
            // Agregar información al formato deseado
            String infoProfesor = "Nombre: " + nombre + apellido + "\n" +
                                  "Correo: " + email + "\n" +
                                  "Edad: " + edad + "\n" +
                                  "Tipo de Documento: " + tipoDocumento + "\n" +
                                  "Número de Documento:  " + cedula + "\n" +
                                  "ID Contrato: " + idContrato + "\n" +
                                  "Salario por Hora: " + salarioHora + "\n" +
                                  "Dirección: " + direccion + "\n" +
                                  "Teléfono: " + telefono + "\n";
            profesores.add(infoProfesor);
        }
    } catch (SQLException e) {
        System.err.println("Error al listar los profesores: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }

    return profesores;
}


}
