import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

public class PlanificadorTareas {

    // Se usa la interfaz Deque implementada por LinkedList para comportamiento de cola (FIFO)
    private final Deque<String> colaAtencion = new LinkedList<>();

    public static void main(String[] args) {
        PlanificadorTareas planificador = new PlanificadorTareas();
        Scanner scanner = new Scanner(System.in);

        // Agrega dos tareas ingresadas por el usuario a la cola
        planificador.encolarTarea(scanner);
        planificador.encolarTarea(scanner);

        System.out.println("--- Procesando Cola ---");

        // Extracción ordenada de los elementos ingresados (FIFO)
        planificador.procesarSiguiente();
        planificador.procesarSiguiente();

        // Intento de extracción defensiva sobre la cola completamente vacía
        planificador.procesarSiguiente();
    }

    /**
     * Agrega un nuevo elemento al final de la estructura LinkedList.
     */
    public void encolarTarea(Scanner scanner) {
        System.out.print("Ingrese descripción de la tarea: ");
        String tarea = scanner.nextLine().trim();

        // Filtro de cadenas vacías
        if (tarea.isEmpty()) {
            System.out.println("-> Tarea descartada por estar vacía.");
            return;
        }

        // Inserción eficiente O(1) al final de la LinkedList mediante la interfaz Deque
        colaAtencion.addLast(tarea);
        System.out.println("-> Tarea añadida a la cola.");
    }

    /**
     * Retira y procesa el primer elemento de la cola.
     * Utiliza pollFirst() y Optional para evitar que falle si la lista está vacía.
     */
    public void procesarSiguiente() {

        // pollFirst() retorna el primer elemento y lo elimina de la cola.
        // Si la cola está vacía, retorna 'null' sin lanzar la excepción NoSuchElementException.
        Optional<String> tarea = Optional.ofNullable(colaAtencion.pollFirst());

        // Manejo defensivo mediante el contenedor Optional
        if (tarea.isPresent()) {
            System.out.println("Ejecutando: " + tarea.get());
        } else {
            System.out.println("-> Estado: No hay tareas pendientes en la cola.");
        }
    }
}
