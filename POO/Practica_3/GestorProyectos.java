import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class GestorProyectos {

    // Lista dinámica encapsulada para almacenar las cadenas de texto
    private final List<String> modulos = new ArrayList<>();

    public static void main(String[] args) {
        GestorProyectos gestor = new GestorProyectos();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Registro Dinámico de Módulos ---");

        // Registro controlado de dos elementos
        gestor.agregarModuloModuloSeguro(scanner);
        gestor.agregarModuloModuloSeguro(scanner);

        // Consulta de elementos por índice con validación de límites
        gestor.mostrarModuloPorIndice(scanner);
    }

    /**
     * Agrega un módulo a la lista asegurando que no esté vacío ni duplicado.
     */
    public void agregarModuloModuloSeguro(Scanner scanner) {
        while (true) {
            System.out.print("Nombre del nuevo módulo: ");
            String nombre = scanner.nextLine().trim();

            // Validar que la cadena no contenga solo espacios o esté vacía
            if (nombre.isEmpty()) {
                System.out.println("-> Error: El nombre no puede ser una cadena vacía.");
                continue;
            }

            // Evitar elementos duplicados en la lista dinámica
            if (modulos.contains(nombre)) {
                System.out.println("-> Error: El módulo ya se encuentra registrado.");
                continue;
            }

            // Inserción segura al final del ArrayList
            modulos.add(nombre);
            System.out.println("-> Módulo registrado correctamente. Total: " + modulos.size());
            break; // Sale del ciclo al completar la adición con éxito
        }
    }

    /**
     * Permite consultar un elemento del ArrayList por su posición
     * evitando IndexOutOfBoundsException y NumberFormatException.
     */
    public void mostrarModuloPorIndice(Scanner scanner) {

        // Verificación previa de que la lista contiene elementos
        if (modulos.isEmpty()) {
            System.out.println("No hay módulos para consultar.");
            return;
        }

        while (true) {
            System.out.print("Ingrese el índice a consultar (0 a " + (modulos.size() - 1) + "): ");
            String entrada = scanner.nextLine().trim();

            try {
                // Conversión manual para evitar que un Scanner lance excepciones si meten letras
                int indice = Integer.parseInt(entrada);

                // Control explícito de rangos para evitar acceder a posiciones inexistentes
                if (indice < 0 || indice >= modulos.size()) {
                    System.out.println("-> Error: Índice fuera de rango.");
                    continue;
                }

                // Acceso directo por índice en tiempo O(1)
                System.out.println("Módulo encontrado: " + modulos.get(indice));
                break;

            } catch (NumberFormatException e) {
                // Maneja el caso en que el usuario ingrese texto en lugar de números enteros
                System.out.println("-> Error: El índice debe ser un número entero.");
            }
        }
    }
}
