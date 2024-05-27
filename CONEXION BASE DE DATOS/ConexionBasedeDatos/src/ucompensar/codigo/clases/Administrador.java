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
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    
    public Map<String, String> obtenerNombresEstudiantes() {
    Map<String, String> nombres = new LinkedHashMap<>();
    ResultSet rs = null;
    PreparedStatement stmt = null;
    try {
        String sql = "SELECT CONCAT(TbP_Nombre,' ', TbP_Apellido) AS Nombre_Completo, TbP_ID_Personas, TbP_Tipo_Personas" +
"                        FROM tb_personas" +
"                        WHERE TbP_Tipo_Personas = 'TP-1' order by Nombre_Completo ASC";

        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            nombres.put(rs.getString("Nombre_Completo"), rs.getString("TbP_ID_Personas"));
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar tipos de nombre: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return nombres;
}
    
    public Map<String, String> obtenerNombresProfesores() {
    Map<String, String> nombres = new LinkedHashMap<>();
    ResultSet rs = null;
    PreparedStatement stmt = null;
    try {
        String sql = "SELECT CONCAT(TbP_Nombre,' ', TbP_Apellido) AS Nombre_Completo, TbP_ID_Personas, TbP_Tipo_Personas" +
"                        FROM tb_personas" +
"                        WHERE TbP_Tipo_Personas = 'TP-2' order by Nombre_Completo ASC";

        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            nombres.put(rs.getString("Nombre_Completo"), rs.getString("TbP_ID_Personas"));
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar tipos de nombre: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return nombres;
}
    
    public Map<String, String> obtenerPrograma() {
    Map<String, String> tipoPograma = new HashMap<>();
    ResultSet rs = null;
    PreparedStatement stmt = null;
    try {
        String sql = "SELECT TbPr_Nombre_Programa , TbPr_ID_Programa " +
                     "FROM tb_programa";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            tipoPograma.put(rs.getString("TbPr_Nombre_Programa"), rs.getString("TbPr_ID_Programa"));
        }
    } catch (SQLException e) {
        System.err.println("Error al cargar tipos de programa: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return tipoPograma;
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
    
   public String obtenerInformacionEliminarEstudiante(String identificacion) {
    StringBuilder informacion = new StringBuilder();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        String sql = "SELECT DISTINCT " +
                "p.TbP_Nombre AS Nombre, " +
                "p.TbP_Apellido AS Apellido, " +
                "td.TbTP_Tipo_Documento AS Tipo_Cedula, " +
                "p.TbP_ID_Personas AS Numero_ID, " +
                "p.TbP_Telefono AS Numero_Telefono, " +
                "tp.TbTP_Tipo_Persona AS Tipo_Persona, " +
                "p.TbP_Direccion AS Direccion, " +
                "YEAR(CURRENT_DATE) - YEAR(p.TbP_Fecha_Nacimiento) AS Edad, " +
                "p.TbP_Direccion_Email AS Correo, " +
                "m.TbM_ID_Matricula AS Matricula, " +
                "cn.TbCN_Nombre_Curso AS Curso, " +
                "ROUND(AVG(n.TbN_ID_Nota), 1) AS Promedio_Nota, " +
                "pr.TbPr_Nombre_Programa AS Programa " +
                "FROM tb_personas p " +
                "LEFT JOIN tb_tipo_documento td ON p.TbP_Tipo_Documento = td.TbTD_ID_Tipo_Documento " +
                "LEFT JOIN tb_tipo_persona tp ON p.TbP_Tipo_Personas = tp.TbTP_ID_Tipo_Persona " +
                "LEFT JOIN tb_matricula m ON p.TbP_ID_Personas = m.TbM_ID_Persona " +
                "LEFT JOIN tb_programa pr ON m.TbM_ID_Programa = pr.TbPr_ID_Programa " +
                "LEFT JOIN tb_curso c ON m.TbM_ID_Matricula = c.TbCo_ID_Matricula " +
                "LEFT JOIN tb_curso_nombre cn ON c.TbCo_ID_Curso = cn.TbCN_ID_Curso " +
                "LEFT JOIN tb_notas n ON c.TbCo_ID_Curso = n.TbN_ID_Curso " +
                "WHERE tp.TbTP_Tipo_Persona = 'Estudiante' AND p.TbP_ID_Personas = ? " +
                "GROUP BY " +
                "p.TbP_Nombre, p.TbP_Apellido, td.TbTP_Tipo_Documento, p.TbP_ID_Personas, " +
                "p.TbP_Telefono, tp.TbTP_Tipo_Persona, p.TbP_Direccion, p.TbP_Fecha_Nacimiento, " +
                "p.TbP_Direccion_Email, m.TbM_ID_Matricula, cn.TbCN_Nombre_Curso, pr.TbPr_Nombre_Programa";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, identificacion);

        rs = stmt.executeQuery();
        while (rs.next()) {
            informacion.append("Nombre: ").append(rs.getString("Nombre")).append("\n");
            informacion.append("Apellido: ").append(rs.getString("Apellido")).append("\n");
            informacion.append("Tipo de Cedula: ").append(rs.getString("Tipo_Cedula")).append("\n");
            informacion.append("Numero ID: ").append(rs.getString("Numero_ID")).append("\n");
            informacion.append("Telefono: ").append(rs.getString("Numero_Telefono")).append("\n");
            informacion.append("Tipo de Persona: ").append(rs.getString("Tipo_Persona")).append("\n");
            informacion.append("Direccion: ").append(rs.getString("Direccion")).append("\n");
            informacion.append("Edad: ").append(rs.getInt("Edad")).append("\n");
            informacion.append("Correo: ").append(rs.getString("Correo")).append("\n");
            informacion.append("Matricula: ").append(rs.getString("Matricula")).append("\n");
            informacion.append("Curso: ").append(rs.getString("Curso")).append("\n");
            informacion.append("Promedio de Nota: ").append(rs.getDouble("Promedio_Nota")).append("\n");
            informacion.append("Programa: ").append(rs.getString("Programa")).append("\n");
        }
    } catch (SQLException e) {
        System.err.println("Error al consultar datos: " + e.getMessage());
        informacion.append("Error al consultar datos: ").append(e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return informacion.toString();
    }
   
   public String obtenerInformacionEliminarProfesor(String identificacion) {
    StringBuilder informacion = new StringBuilder();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        String sql = "SELECT " +
                     "p.TbP_Nombre AS Nombre, " +
                     "p.TbP_Apellido AS Apellido, " +
                     "td.TbTP_Tipo_Documento AS Tipo_Cedula, " +
                     "p.TbP_ID_Personas AS Numero_ID, " +
                     "p.TbP_Telefono AS Numero_Telefono, " +
                     "tp.TbTP_Tipo_Persona AS Tipo_Persona, " +
                     "p.TbP_Direccion AS Direccion, " +
                     "YEAR(CURRENT_DATE) - YEAR(p.TbP_Fecha_Nacimiento) AS Edad, " +
                     "p.TbP_Direccion_Email AS Correo " +
                     "FROM tb_personas p " +
                     "LEFT JOIN tb_tipo_documento td ON p.TbP_Tipo_Documento = td.TbTD_ID_Tipo_Documento " +
                     "LEFT JOIN tb_tipo_persona tp ON p.TbP_Tipo_Personas = tp.TbTP_ID_Tipo_Persona " +
                     "WHERE tp.TbTP_Tipo_Persona = 'Profesor' AND p.TbP_ID_Personas = ?";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, identificacion);

        rs = stmt.executeQuery();
        while (rs.next()) {
            informacion.append("Nombre: ").append(rs.getString("Nombre")).append("\n");
            informacion.append("Apellido: ").append(rs.getString("Apellido")).append("\n");
            informacion.append("Tipo de Cedula: ").append(rs.getString("Tipo_Cedula")).append("\n");
            informacion.append("Numero ID: ").append(rs.getString("Numero_ID")).append("\n");
            informacion.append("Telefono: ").append(rs.getString("Numero_Telefono")).append("\n");
            informacion.append("Tipo de Persona: ").append(rs.getString("Tipo_Persona")).append("\n");
            informacion.append("Direccion: ").append(rs.getString("Direccion")).append("\n");
            informacion.append("Edad: ").append(rs.getInt("Edad")).append("\n");
            informacion.append("Correo: ").append(rs.getString("Correo")).append("\n");
        }
    } catch (SQLException e) {
        System.err.println("Error al consultar datos: " + e.getMessage());
        informacion.append("Error al consultar datos: ").append(e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar recursos: " + ex.getMessage());
        }
    }
    return informacion.toString();
    }

   public void eliminar(String identificacion) {
    try {
        // 1
        String sql0 = "SET SQL_SAFE_UPDATES = 0";
        try (PreparedStatement stmt0 = conn.prepareStatement(sql0)) {
            stmt0.executeUpdate();
        }

        //2
        String sql1 = "DELETE FROM tb_notas WHERE TbN_ID_Curso IN (SELECT TbCo_ID_Curso FROM tb_curso WHERE TbCo_ID_Matricula IN (SELECT TbM_ID_Matricula FROM tb_matricula WHERE TbM_ID_Persona = ?))";
        try (PreparedStatement stmt1 = conn.prepareStatement(sql1)) {
            stmt1.setString(1, identificacion);
            stmt1.executeUpdate();
        }

        //3
        String sql2 = "DELETE FROM tb_curso WHERE TbCo_ID_Matricula IN (SELECT TbM_ID_Matricula FROM tb_matricula WHERE TbM_ID_Persona = ?)";
        try (PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
            stmt2.setString(1, identificacion);
            stmt2.executeUpdate();
        }
        
        // 4
        String sql3 = "DELETE FROM tb_nivel_matricula WHERE TbNM_ID_Curso IN (SELECT TbCo_ID_Curso FROM tb_curso WHERE TbCo_ID_Matricula IN (SELECT TbM_ID_Matricula FROM tb_matricula WHERE TbM_ID_Persona = ?))";
        try (PreparedStatement stmt3 = conn.prepareStatement(sql3)) {
            stmt3.setString(1, identificacion);
            stmt3.executeUpdate();
        }

        // 5
        String sql4 = "DELETE FROM tb_carga_docente WHERE TbCD_Contrato IN (SELECT TbC_ID_Contrato FROM tb_contrato WHERE TbC_ID_Personas = ?)";
        try (PreparedStatement stmt4 = conn.prepareStatement(sql4)) {
            stmt4.setString(1, identificacion);
            stmt4.executeUpdate();
        }

        // 6
        String sql5 = "DELETE FROM tb_usuarios WHERE TbU_ID_Contraseña IN (SELECT TbP_ID_Personas FROM tb_personas WHERE TbP_ID_Personas = ?)";
        try (PreparedStatement stmt5 = conn.prepareStatement(sql5)) {
            stmt5.setString(1, identificacion);
            stmt5.executeUpdate();
        }

        // Eliminar cualquier otro registro relacionado con el estudiante en tb_contrato
        String sql6 = "DELETE FROM tb_contrato WHERE TbC_ID_Personas = ?";
        try (PreparedStatement stmt6 = conn.prepareStatement(sql6)) {
            stmt6.setString(1, identificacion);
            stmt6.executeUpdate();
        }

        // Eliminar el registro del estudiante en tb_personas
        String sql7 = "DELETE FROM tb_matricula WHERE TbM_ID_Persona = ?";
        try (PreparedStatement stmt7 = conn.prepareStatement(sql7)) {
            stmt7.setString(1, identificacion);
            stmt7.executeUpdate();
        }
        String sql8 = "DELETE FROM tb_personas WHERE TbP_ID_Personas = ?";
        try (PreparedStatement stmt8 = conn.prepareStatement(sql8)) {
            stmt8.setString(1, identificacion);
            stmt8.executeUpdate();
        }
        
        String sql9 = "SET SQL_SAFE_UPDATES = 1";
        try (PreparedStatement stmt9 = conn.prepareStatement(sql9)) {
            stmt9.executeUpdate();
        }

        
    } catch (SQLException e) {
        System.err.println("Error al eliminar estudiante: " + e.getMessage());
    }
}



}