/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo.clases;

import java.util.Date;

/**
 *
 * @author 52130424
 */
public class Estudiante extends Personas implements Horario, Nivel{
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
   public String Consultar(){
        return "Rol" + tipoPersona + "\n"
                + "Nombre: " + getNombre() + "\n"
                + "Apellido: " + getApellido() + "\n"
                + "Identificacion:( " + getTipoDocumento() + ") " + getIdentificacion() + "\n" 
                + "Email: " + getEmail() + "\n"
                + "Direccion: " + getDireccion() +"\n"
                + "Fecha de Nacimiento: " + getFechaNacimiento()
                ;
    }

    @Override
    public String horario(String ID, String diaSemana, Date horaInicio, Date horaFinal) {
        return "Horario: " + ID + "\n" +
                "Dia: " + diaSemana + "\n" +
                "Hora Inicio: " + horaInicio + "\n"+
                "Hora Final: " + horaFinal       
                 ;
    }

    @Override
    public String nivel(String nivel) {
        return "Nivel: " + nivel;
    }
    
    
    
}