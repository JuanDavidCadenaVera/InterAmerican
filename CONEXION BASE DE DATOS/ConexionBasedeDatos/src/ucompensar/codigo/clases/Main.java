/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucompensar.codigo.clases;

/**
 *
 * @author 52130424
 */
public class Main {
    
    
    public static void main(String[] args) {
        x claseX = new x();

    // Ingresar algunas notas de ejemplo
    claseX.ingresarNota("Pepito", 3.5);
    claseX.ingresarNota("Pepito", 4.0); // Agregar otra nota para Pepito
    claseX.ingresarNota("Juanita", 4.2);
    claseX.ingresarNota("María", 3.8);

    // Mostrar las notas de los estudiantes
    String notasEstudiantes = claseX.EstudiantesNotas();
    System.out.println(notasEstudiantes);
    }
    
}
