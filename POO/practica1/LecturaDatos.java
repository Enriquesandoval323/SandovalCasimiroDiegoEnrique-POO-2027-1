import java.util.Scanner;

public class LecturaDatos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingresa tu edad: ");
        int edad = scanner.nextInt();

        System.out.println("Datos Registrados de usuario");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");

        scanner.close();
    }
}
