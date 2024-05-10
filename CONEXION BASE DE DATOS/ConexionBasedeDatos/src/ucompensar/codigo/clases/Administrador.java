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
public class Administrador extends Personas {
    private String tipoPersona = "TP-3";
    
    public Administrador(String tipoPersona, String identificacion, String tipoDocumento, String nombre, String apellido, String email, String direccion, Date fechaNacimiento) {
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    
}
