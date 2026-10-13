// Con este main el usuario puede ingrese los datos de diferentes tequilas preferidos
// Después crea los objetos y calcula el precio total de la compra.

import java.util.Scanner;

public class MainTequilas {

    public static void main(String[] args) {

        // Scanner para ingresar datos
        Scanner entrada = new Scanner(System.in);


        // Datos del tequila blanco
        System.out.println("=== TEQUILA BLANCO ===");

        System.out.print("Marca: ");
        String marcaBlanco = entrada.nextLine();

        System.out.print("Precio: ");
        double precioBlanco = Double.parseDouble(entrada.nextLine());

        System.out.print("Porcentaje de alcohol: ");
        int alcohol = Integer.parseInt(entrada.nextLine());

        System.out.print("Cantidad de botellas: ");
        int cantidadBlanco = Integer.parseInt(entrada.nextLine());

        // Se crea el objeto de tequila blanco
        TequilaBlanco blanco =
                new TequilaBlanco(marcaBlanco, precioBlanco, alcohol);


        // Datos del tequila reposado
        System.out.println("\n=== TEQUILA REPOSADO ===");

        System.out.print("Marca: ");
        String marcaReposado = entrada.nextLine();

        System.out.print("Precio: ");
        double precioReposado = Double.parseDouble(entrada.nextLine());

        System.out.print("Meses de reposo: ");
        int mesesReposo = Integer.parseInt(entrada.nextLine());

        System.out.print("Cantidad de botellas: ");
        int cantidadReposado = Integer.parseInt(entrada.nextLine());

        // Crea el objeto de tequila reposado
        TequilaReposado reposado =
                new TequilaReposado(marcaReposado, precioReposado, mesesReposo);


        // Datos del tequila añejo
        System.out.println("\n=== TEQUILA AÑEJO ===");

        System.out.print("Marca: ");
        String marcaAñejo = entrada.nextLine();

        System.out.print("Precio: ");
        double precioAñejo = Double.parseDouble(entrada.nextLine());

        System.out.print("Años de maduración: ");
        int añosMaduracion = Integer.parseInt(entrada.nextLine());

        System.out.print("Cantidad de botellas: ");
        int cantidadAñejo = Integer.parseInt(entrada.nextLine());

        // Crea el objeto de tequila añejo
        TequilaAñejo añejo =
                new TequilaAñejo(marcaAñejo, precioAñejo, añosMaduracion);


        // Crea el objeto para calcular la compra
        CalculadoraCompra calculadora = new CalculadoraCompra();

        // Calcula el subtotal de cada tequila
        double totalBlanco =
                calculadora.calcularTotal(precioBlanco, cantidadBlanco);

        double totalReposado =
                calculadora.calcularTotal(precioReposado, cantidadReposado);

        double totalAñejo =
                calculadora.calcularTotal(precioAñejo, cantidadAñejo);

        // Suma los subtotales
        double totalCompra =
                totalBlanco + totalReposado + totalAñejo;


        // Muestra como un ticket de las compras que agrego el usuario
        System.out.println("\n=== RESUMEN DE COMPRA ===");

        System.out.println(marcaBlanco + " Blanco: "
                + cantidadBlanco + " botellas - $" + totalBlanco);

        System.out.println(marcaReposado + " Reposado: "
                + cantidadReposado + " botellas - $" + totalReposado);

        System.out.println(marcaAñejo + " Añejo: "
                + cantidadAñejo + " botellas - $" + totalAñejo);

        System.out.println("\nTotal de la compra: $" + totalCompra);

        entrada.close();
    }
}