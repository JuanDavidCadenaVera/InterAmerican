/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo;

import java.util.Date;



/**
 *
 * @author JUAN DAVID
 */
public class GestorDeDatos {
    
    private String TbP_ID_Personas;
    private String TbP_Tipo_Personas;
    private String TbP_Tipo_Documento;
    private String TbP_Nombre;
    private String TbP_Apellido;
    private String TbP_Telefono;
    private String TbP_Direccion;
    private Date TbP_Fecha_Nacimiento;

    public GestorDeDatos(String TbP_ID_Personas, String TbP_Tipo_Personas, String TbP_Tipo_Documento, String TbP_Nombre, String TbP_Apellido, String TbP_Telefono, String TbP_Direccion, Date TbP_Fecha_Nacimiento) {
        this.TbP_ID_Personas = TbP_ID_Personas;
        this.TbP_Tipo_Personas = TbP_Tipo_Personas;
        this.TbP_Tipo_Documento = TbP_Tipo_Documento;
        this.TbP_Nombre = TbP_Nombre;
        this.TbP_Apellido = TbP_Apellido;
        this.TbP_Telefono = TbP_Telefono;
        this.TbP_Direccion = TbP_Direccion;
        this.TbP_Fecha_Nacimiento = TbP_Fecha_Nacimiento;
    }

   
    public String getTbP_ID_Personas() {
        return TbP_ID_Personas;
    }

    public void setTbP_ID_Personas(String TbP_ID_Personas) {
        this.TbP_ID_Personas = TbP_ID_Personas;
    }

    public String getTbP_Tipo_Personas() {
        return TbP_Tipo_Personas;
    }

    public void setTbP_Tipo_Personas(String TbP_Tipo_Personas) {
        this.TbP_Tipo_Personas = TbP_Tipo_Personas;
    }

    public String getTbP_Tipo_Documento() {
        return TbP_Tipo_Documento;
    }

    public void setTbP_Tipo_Documento(String TbP_Tipo_Documento) {
        this.TbP_Tipo_Documento = TbP_Tipo_Documento;
    }

    public String getTbP_Nombre() {
        return TbP_Nombre;
    }

    public void setTbP_Nombre(String TbP_Nombre) {
        this.TbP_Nombre = TbP_Nombre;
    }

    public String getTbP_Apellido() {
        return TbP_Apellido;
    }

    public void setTbP_Apellido(String TbP_Apellido) {
        this.TbP_Apellido = TbP_Apellido;
    }

    public String getTbP_Telefono() {
        return TbP_Telefono;
    }

    public void setTbP_Telefono(String TbP_Telefono) {
        this.TbP_Telefono = TbP_Telefono;
    }

    public String getTbP_Direccion() {
        return TbP_Direccion;
    }

    public void setTbP_Direccion(String TbP_Direccion) {
        this.TbP_Direccion = TbP_Direccion;
    }

    public Date getTbP_Fecha_Nacimiento() {
        return TbP_Fecha_Nacimiento;
    }

    public void setTbP_Fecha_Nacimiento(Date TbP_Fecha_Nacimiento) {
        this.TbP_Fecha_Nacimiento = TbP_Fecha_Nacimiento;
    }
    
   public String toString() {
        return "ID Persona: " + TbP_ID_Personas + "\n" +
               "Tipo de Persona: " + TbP_Tipo_Personas + "\n" +
               "Tipo de Documento: " + TbP_Tipo_Documento + "\n" +
               "Nombre: " + TbP_Nombre + "\n" +
               "Apellido: " + TbP_Apellido + "\n" +
               "Teléfono: " + TbP_Telefono + "\n" +
               "Dirección: " + TbP_Direccion + "\n" +
               "Fecha de Nacimiento: " + TbP_Fecha_Nacimiento;
    }
    

   
}
