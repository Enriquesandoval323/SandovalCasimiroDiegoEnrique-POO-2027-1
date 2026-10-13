/*
De La Cruz Garcia Juan
Copado Hernandez Luis
Rivera Hernandez Luis
Sandoval Casimiro Diego

En esta parte del código lo que buscamos es crear la clase Libro, en este caso se utiliza
el modificador private en los atributos de la clase para aplicar el principio de encapsulamiento.

Esto protege los datos internos del libro, como el ISBN, título, autor, año y disponibilidad,
evitando que sean modificados directamente desde el exterior, lo cual obliga a gestionarlos
de forma segura mediante los métodos de acceso getters y setters.
*/

public class Libro {

    // Aquí realizamos la declaración de los atributos que vamos a emplear para nuestra clase Libro
    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean disponible;
    private static int totalLibros = 0;

    /*
    Aquí se hacen las declaraciones de los constructores.
    En este caso son tres: uno vacío, uno con dos atributos
    y otro con todos los atributos necesarios.
    */

    public Libro() {
    }

    public Libro(String autor, String titulo) {
        this.autor = autor;
        this.titulo = titulo;
    }

    public Libro(String isbn, String autor, String titulo, int anioPublicacion) {
        this.isbn = isbn;
        this.autor = autor;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
        totalLibros++;
    }

    // Método para consultar si el libro está disponible
    public boolean estaDisponible() {
        return this.disponible;
    }

    // Declaración de los métodos setters
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    // Declaración de los métodos getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }
}