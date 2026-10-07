import java.util.Scanner;

// Programa que demuestra el uso de clases Envoltorio (Wrappers) en Java.
// Combinatoria realizada: Wrappers (Integer, Character, Double, Boolean) + Conversiones
// de tipo + Análisis de caracteres.

public class Programa4Wrappers {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Uso de Integer Wrapper
        System.out.print("Ingrese un número entero: ");

        try {
            Integer numero = Integer.valueOf(scanner.nextLine());
            System.out.println("Número ingresado: " + numero);
            System.out.println("Representación en binario: " + Integer.toBinaryString(numero));

        } catch (NumberFormatException e) {
            System.out.println("Error: No ingresó un entero válido.");
        }

        // Uso de Character Wrapper
        System.out.print("Ingrese un carácter: ");
        String entrada = scanner.nextLine();

        if (!entrada.isEmpty()) {
            Character caracter = Character.valueOf(entrada.charAt(0));
            System.out.println("¿Es letra?: " + Character.isLetter(caracter));
            System.out.println("¿Es dígito?: " + Character.isDigit(caracter));
            System.out.println("En mayúscula: " + Character.toUpperCase(caracter));
        }

        // Uso de Double y Boolean Wrapper
        System.out.println("Constante NaN de Double: " + Double.NaN);
        Boolean esValido = Boolean.valueOf("true");
        System.out.println("Valor booleano: " + esValido);

        scanner.close();
    }
}
