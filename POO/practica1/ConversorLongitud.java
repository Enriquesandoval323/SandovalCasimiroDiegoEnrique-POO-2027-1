import java.util.Scanner;

public class ConversorLongitud {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa la distancia en metros (m): ");
        double metros = scanner.nextDouble();

        double centimetros = metros * 100;
        double milimetros = metros * 1000;

        System.out.println("\n--- Resultados de la Conversion ---");
        System.out.println("Metros ingresados: " + metros + " m");
        System.out.println("Equivalente en centimetros: " + centimetros + " cm");
        System.out.println("Equivalente en milimetros: " + milimetros + " mm");

        scanner.close();
    }
}
