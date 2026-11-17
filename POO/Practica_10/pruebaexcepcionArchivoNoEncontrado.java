
/*
El proposito de este programa es el de mostrar como es que funciona de manera practica la excepcion FileNotFoundException.
El programa se basa, en que se le va a pedir a el usuario que ingrese el nombre de un archivo de texto (por ejemplo pruebas.txt), para posteriormente leer e imprimir su contenido.
Se usa FileNotFoundException para poder evitar que el programa se detenga cuando el usuario escribe el nombre de un archivo que no existe o no se puede abrir.
Si el archivo no existe, el programa le avisa al usuario y le vuelve a pedir el nombre, hasta que ingrese uno valido o escriba "salir".

Copado Hernandez Luis Felipe
De La Cruz Garcia Juan Antonio 
Rivera Hernandez Luis Angel 
Salvador Casimiro Diego

*/
// Importamos las blibliotecas necesarias, en donde io.FileNotFoundException es la que nos permite atrapar la excepcion
// y la de io.File es la que nos permite crear un objeto de tipo archivo para poder leerlo
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class pruebaexcepcionArchivoNoEncontrado {
    // Declaracion de los atributos, se ponen en privado para que el usuario no pueda acceder a ellos directamente
    private String nombreArchivo;

    // Declaracion de los dos constructores, tanto el caso base, asi como el constructor lleno
    public pruebaexcepcionArchivoNoEncontrado() {

    }

    public pruebaexcepcionArchivoNoEncontrado(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    // Metodos setters
    public void setnombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    // Metodos getters
    public String getnombreArchivo() {
        return nombreArchivo;
    }

    // Creacion del metodo leerArchivo, aqui es donde vamos a hacer uso de try-catch, el cual nos va a permitir atrapar la excepcion FileNotFoundException
    // El metodo regresa true si el archivo se pudo leer y false si hubo error, esto nos sirve para controlar el ciclo en el main
    public boolean leerArchivo() {
        try {
            // Al crear un Scanner sobre un objeto File, Java lanza FileNotFoundException si el archivo no existe o no se puede abrir
            Scanner lector = new Scanner(new File(nombreArchivo));
            int numeroLinea = 1;

            System.out.println("Contenido del archivo " + nombreArchivo + ":");
            // Mientras haya lineas en el archivo, las leemos e imprimimos con su numero de linea
            while (lector.hasNextLine()) {
                System.out.println(numeroLinea + ": " + lector.nextLine());
                numeroLinea++;
            }
            lector.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontro el archivo \"" + nombreArchivo + "\".");
            return false;
        }
    }

    // Creacion de la clase main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Programa para probar las excepciones");
        System.out.println("Nota: el archivo debe estar en la misma carpeta desde donde ejecutas el programa, o debes escribir la ruta completa.");

        // Ciclo con ayuda de un while, que obliga al usuario a ingresar correctamente la informacion
        while (true) {
            System.out.println("Ingresa el nombre del archivo que quieres leer (o escribe salir para terminar): ");
            String entrada = scanner.nextLine();
            // Verificamos si el usuario quiere salir
            if (entrada.equalsIgnoreCase("salir")) {
                System.out.println("Programa terminado.");
                break;
            }

            // Creamos el objeto y operamos (aqui actua la FileNotFoundException en el metodo)
            pruebaexcepcionArchivoNoEncontrado operacion = new pruebaexcepcionArchivoNoEncontrado(entrada);

            if (operacion.leerArchivo()) {
                break; // Si el archivo se leyo bien, rompe el ciclo y termina
            }
            System.out.println("Verifica que el nombre y la extension sean correctos e intentalo de nuevo.");
        }

        scanner.close();
    }
}