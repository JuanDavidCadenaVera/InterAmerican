/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo.clases;

import ucompensar.codigo.Conexion;
import java.sql.Connection;
import java.util.Date;

/**
 *
 * @author 52130424
 */
public class Profesor extends Personas implements Horario, CargaDocente{
     Conexion conexion = new Conexion();
     Connection conn = conexion.conectar();
        
    private String tipoPersona = "TP-2";

    public Profesor(String email, String contraseña) {
        super(email, null);
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public String Consultar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String horario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void carga(String TbCD_ID_Detalles, String TbCD_Contrato, String TbCD_Horas_Trabajadas, String TbCD_Dia_Semana) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
    
    