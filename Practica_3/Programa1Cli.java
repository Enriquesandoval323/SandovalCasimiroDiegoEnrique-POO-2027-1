// Programa que demuestra el uso de Argumentos por Línea de Comandos combinado con
// validación de datos.
// Combinatoria realizada: Argumentos por línea de comandos + Parseo de datos + Control
// de excepciones.

public class Programa1Cli {
    public static void main(String[] args) {

        // Validar que se ingresen pares de datos (Nombre y Calificación)
        if (args.length < 2 || args.length % 2 != 0) {
            System.out.println("Error: Debe ingresar pares de datos [Nombre] [Calificación].");
            return;
        }

        double suma = 0.0;
        int total = args.length / 2;

        // Recorrer los argumentos recibidos desde la terminal
        for (int i = 0; i < args.length; i += 2) {
            String nombre = args[i];

            try {
                // Convertir la calificación a número flotante
                double calificacion = Double.parseDouble(args[i + 1]);
                suma += calificacion;
                System.out.println("Alumno: " + nombre + " | Calificación: " + calificacion);

            } catch (NumberFormatException e) {
                // Manejo de error si la calificación no es un número
                System.out.println("Error: La calificación de " + nombre + " no es válida.");
            }
        }

        // Mostrar el promedio final
        System.out.println("Promedio general: " + (suma / total));
    }
}
