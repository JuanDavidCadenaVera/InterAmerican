/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo;

/**
 *
 * @author JUAN DAVID
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Consultar {
    
    String TbP_ID_Personas;
    String TbP_Tipo_Personas;
    String TbP_Tipo_Documento;
    String TbP_Nombre;
    String TbP_Apellido;
    String TbP_Telefono;
    String TbP_Direccion;
    Date TbP_Fecha_Nacimiento;

    public Consultar(String TbP_ID_Personas, String TbP_Tipo_Personas, String TbP_Tipo_Documento,
                     String TbP_Nombre, String TbP_Apellido, String TbP_Telefono,
                     String TbP_Direccion, Date TbP_Fecha_Nacimiento) {
        this.TbP_ID_Personas = TbP_ID_Personas;
        this.TbP_Tipo_Personas = TbP_Tipo_Personas;
        this.TbP_Tipo_Documento = TbP_Tipo_Documento;
        this.TbP_Nombre = TbP_Nombre;
        this.TbP_Apellido = TbP_Apellido;
        this.TbP_Telefono = TbP_Telefono;
        this.TbP_Direccion = TbP_Direccion;
        this.TbP_Fecha_Nacimiento = TbP_Fecha_Nacimiento;
    }

    public static void imprimir() {
        String sql = "SELECT * FROM tb_personas";

        try (Connection conn = new Conexion().conectar()) {
            if (conn == null) {
                System.out.println("La conexión no pudo ser establecida.");
                return;
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Consultar persona = new Consultar(
                    rs.getString("TbP_ID_Personas"),
                    rs.getString("TbP_Tipo_Personas"),
                    rs.getString("TbP_Tipo_Documento"),
                    rs.getString("TbP_Nombre"),
                    rs.getString("TbP_Apellido"),
                    rs.getString("TbP_Telefono"),
                    rs.getString("TbP_Direccion"),
                    rs.getDate("TbP_Fecha_Nacimiento")
                );
                System.out.println("Nombre: " + persona.TbP_Nombre + " - " + " Apellido: " + persona.TbP_Apellido +" - " + " Identificacion: " + persona.TbP_ID_Personas
                        + " - " + " Tipo de Documento:" + " - " +  persona.TbP_Tipo_Documento + " - " + " Teléfono: " + " - " + persona.TbP_Telefono
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());
        }
    }


}
