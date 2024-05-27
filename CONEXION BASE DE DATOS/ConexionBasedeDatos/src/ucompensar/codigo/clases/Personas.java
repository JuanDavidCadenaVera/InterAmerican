/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo.clases;

import java.util.Date;
import ucompensar.codigo.Usuario;

/**
 *
 * @author 52130424
 */
public abstract class Personas extends Usuario{
    private String identificacion;
    private String tipoDocumento;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String tipoEstudiante = "TP-1";
    private String tipoProfesor = "TP-2";
    private Date fechaNacimiento;
    
    

     public Personas(String email, String contraseña) {
        super(email, contraseña); 
    }
    
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getTelefono(){
        return telefono;
    }
    
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoEstudiante(){
        return tipoEstudiante;
    }
    
    public void setTipoEstudiante(String tipoEstudiante){
        this.tipoEstudiante = tipoEstudiante;
    }
    
    public String getTipoProfesor(){
        return tipoProfesor;
    }
    
    public void setTipoProfesor(String tipoProfesor){
        this.tipoProfesor = tipoProfesor;
    }
    
    public Date getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
   public abstract String Consultar();
   
}
