/*
El proposito de este programa es el de mostrar como es que funciona de manera practica la excepcion IOException.
El programa se basa, en que se le va a pedir a el usuario que ingrese el nombre de un archivo y un texto, para posteriormente escribir ese texto en el archivo y volver a leerlo.
Se usa IOException para poder evitar que el programa se detenga cuando ocurre un error de entrada/salida, por ejemplo cuando el usuario ingresa una ruta
que no existe (como carpetaInexistente/datos.txt), un nombre de archivo invalido o un lugar donde no se tienen permisos para escribir.
IOException es la excepcion "padre" de FileNotFoundException, por eso tambien atrapa ese tipo de errores.

Copado Hernandez Luis Felipe
De La Cruz Garcia Juan Antonio 
Rivera Hernandez Luis Angel 
Salvador Casimiro Diego
*/
// Se importan las bibliotecas necesarias para manejar archivos y excepciones de entrada/salida, en este caso, usamos FileWriter para escribir en archivos
// BufferedReader y FileReader para leer archivos, y IOException para manejar errores de entrada/salida.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class pruebaIOException {
    // Declaracion de los atributos, se ponen en privado para que el usuario no pueda acceder a ellos directamente
    private String nombreArchivo;
    private String contenido;

    // Declaracion de los dos constructores, tanto el caso base, asi como el constructor lleno
    public pruebaIOException() {

    }

    public pruebaIOException(String nombreArchivo, String contenido) {
        this.nombreArchivo = nombreArchivo;
        this.contenido = contenido;
    }

    // Metodos setters
    public void setnombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void setcontenido(String contenido) {
        this.contenido = contenido;
    }

    // Metodos getters
    public String getnombreArchivo() {
        return nombreArchivo;
    }

    public String getcontenido() {
        return contenido;
    }

    // Creacion del metodo escribir, aqui es donde vamos a hacer uso de try-catch, el cual nos va a permitir atrapar la excepcion IOException
    // Se usa try-with-resources para que el archivo se cierre automaticamente al terminar
    // El metodo regresa true si se pudo escribir y false si hubo error
    public boolean escribir() {
        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            // FileWriter y write() pueden lanzar IOException si la ruta no existe, no hay permisos o el disco falla
            escritor.write(contenido);
            System.out.println("Texto guardado correctamente en " + nombreArchivo);
            return true;
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: No se pudo escribir en el archivo.");
            System.out.println("Detalle tecnico: " + e.getMessage());
            return false;
        }
    }

    // Creacion del metodo leer, que tambien puede lanzar IOException al abrir o leer el archivo
    public void leer() {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            System.out.println("Al volver a leer el archivo se obtiene: ");
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: No se pudo leer el archivo.");
            System.out.println("Detalle tecnico: " + e.getMessage());
        }
    }

    // Creacion de la clase main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Programa para probar las excepciones");
        System.out.println("Tip: prueba con un nombre valido (datos.txt) y despues con una ruta que no exista (carpetaInexistente/datos.txt).");

        // Ciclo con ayuda de un while, que obliga al usuario a ingresar correctamente la informacion
        while (true) {
            System.out.println("Ingresa el nombre del archivo donde quieres guardar tu texto (o escribe salir para terminar): ");
            String nombre = scanner.nextLine();

            if (nombre.equalsIgnoreCase("salir")) {
                System.out.println("Programa terminado.");
                break;
            }

            System.out.println("Ingresa el texto que quieres guardar: ");
            String texto = scanner.nextLine();

            // Creamos el objeto y operamos (aqui actua la IOException en los metodos)
            pruebaIOException operacion = new pruebaIOException(nombre, texto);

            if (operacion.escribir()) {
                operacion.leer();
                break; // Si se escribio bien, rompe el ciclo y termina
            }
            System.out.println("Intentalo de nuevo con otro nombre de archivo.");
        }

        scanner.close();
    }
}
