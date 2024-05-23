package ucompensar.codigo.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class x {
    // Mapa para almacenar las notas de los estudiantes
    private Map<String, List<Double>> notasEstudiantes = new HashMap<>();

    public void ingresarNota(String nombreEstudiante, double nota) {
        // Supongamos que obtenerIdMatricula y obtenerIdCursoValido son métodos que ya tienes implementados
        String idMatricula = obtenerIdMatricula(nombreEstudiante);
        String idCurso = obtenerIdCursoValido();

        // Obtener la lista de notas del estudiante, o crear una nueva si no existe
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

            estudiantesNotas.append("Nombre del estudiante: ").append(nombreEstudiante).append("\n");
            estudiantesNotas.append("Notas: ");

            for (Double nota : notas) {
                estudiantesNotas.append(nota).append(" ");
            }

            estudiantesNotas.append("\n\n");
        }

        return estudiantesNotas.toString();
    }

    // Supongamos que estos son los métodos que faltan
    private String obtenerIdMatricula(String nombreEstudiante) {
        // Implementación para obtener el ID de matrícula
        return "";
    }

    private String obtenerIdCursoValido() {
        // Implementación para obtener un ID de curso válido
        return "";
    }
}
