package ucompensar.codigo;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author JUAN DAVID
 */
public class Main {
    public static void main(String[] args) {
        Conexion c = new Conexion ();
        c.conectar();
        Consultar.imprimir();
        }
    }
