import java.util.Scanner;

// Programa que demuestra el uso de StringBuilder para la construcción eficiente de texto.
// Combinatoria realizada: StringBuilder + Control de flujo iterativo + Validación de entradas.

public class Programa3StringBuilder {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Creación del objeto StringBuilder para acumular el reporte
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE PRODUCTOS ===\n");

        System.out.print("¿Cuántos productos desea registrar?: ");
        int cantidad = 0;

        try {
            cantidad = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Se registrará 1 producto por defecto.");
            cantidad = 1;
        }

        // Bucle para concatenar productos al StringBuilder
        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Nombre del producto " + i + ": ");
            String producto = scanner.nextLine();
            reporte.append("Producto ").append(i).append(": ").append(producto).append("\n");
        }

        // Impresión del reporte completo
        System.out.println("\n" + reporte.toString());

        scanner.close();
    }
}
