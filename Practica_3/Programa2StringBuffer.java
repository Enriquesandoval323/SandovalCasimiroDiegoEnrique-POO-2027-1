import java.util.Scanner;

// Programa que demuestra el uso de StringBuffer para la manipulación mutable de
// cadenas.
// Combinatoria realizada: StringBuffer + Entrada de datos por consola + Métodos de edición
// de texto.

public class Programa2StringBuffer {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Creación e inicialización del objeto StringBuffer
        StringBuffer bitacora = new StringBuffer("REGISTRO: ");

        // Entrada de datos del usuario
        System.out.print("Ingrese un evento para la bitácora: ");
        String evento = scanner.nextLine();

        // Modificación del contenido dinámicamente con append
        bitacora.append(evento);
        System.out.println("Contenido actual: " + bitacora.toString());

        // Operaciones adicionales con StringBuffer
        bitacora.insert(0, "INICIO -> ");
        System.out.println("Con inserción: " + bitacora.toString());

        bitacora.reverse();
        System.out.println("Texto invertido: " + bitacora.toString());

        scanner.close();
    }
}
