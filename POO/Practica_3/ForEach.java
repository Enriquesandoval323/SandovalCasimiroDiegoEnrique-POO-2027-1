import java.util.Scanner;

// Programa que demuestra el uso de ForEach para recorrer un arreglo

public class ForEach {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        int[] calificaciones = new int[5];

        // Solicita las calificaciones al usuario
        for (int i = 0; i < calificaciones.length; i++) {

            System.out.print("Ingresa la calificacion " + (i + 1) + ": ");

            // Valida que el dato ingresado sea un número entero
            if (!entrada.hasNextInt()) {
                System.out.println("Error: Debe ingresar un numero entero.");
                entrada.close();
                return;
            }

            int calificacion = entrada.nextInt();

            // Valida que la calificación esté en el rango de 0 y 10
            if (calificacion < 0 || calificacion > 10) {
                System.out.println("Error: La calificacion debe estar entre 0 y 10.");
                entrada.close();
                return;
            }

            calificaciones[i] = calificacion;
        }

        System.out.println("\nCalificaciones ingresadas:");

        // Recorrer las calificaciones utilizando el ForEach
        for (int calificacion : calificaciones) {
            System.out.println(calificacion);
        }

        entrada.close();
    }
}
    

