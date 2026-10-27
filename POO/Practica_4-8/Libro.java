/*
De La Cruz Garcia Juan
Copado Hernandez Luis
Rivera Hernandez Luis
Sandoval Casimiro Diego

En este parte del codigo lo que buscamos es crear la clase libro, en este caso se utiliza el modificador private en los atributos de la clase para aplicar el principio de encapsulamiento.
Esto protege los datos internos del libro (como el ISBN, titulo, autor, año y disponibilidad), evitando que sean modificados directamente desde el exterior
lo cual obliga a gestionarlos mediante los metodos de acceso (getters y setters)
*/
public class Libro implements Prestable{
    // aqui realizamos la declaracion de los atributos de la clase libro
    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean disponible;
    private static int totalLibros=0;

    // Constructor vacio. Cada vez que creamos un Libro, sumamos uno al contador
    public Libro(){
        this.disponible=true;
        totalLibros++;
    }

    // Constructor con titulo y autor
    public Libro(String titulo, String autor){
        this();
        this.titulo=titulo;
        this.autor=autor;
    }

    // Constructor lleno. El orden sigue las instrucciones del profesor
    public Libro(String isbn, String titulo, String autor, int anioPublicacion){
        this();
        this.isbn=isbn;
        this.titulo=titulo;
        this.autor=autor;
        this.anioPublicacion=anioPublicacion;
    }

    // metodo para consultar el contador estatico de todos los objetos Libro creados
    public static int getTotalLibros(){
        return totalLibros;
    }

    // declaracion de los metodos setters
    public void setIsbn(String isbn){
        this.isbn=isbn;
    }

    public void setTitulo(String titulo){
        this.titulo=titulo;
    }

    public void setAutor(String autor){
        this.autor=autor;
    }

    public void setAnioPublicacion(int anioPublicacion){
        this.anioPublicacion=anioPublicacion;
    }

    // declaracion de los metodos getters
    public String getIsbn(){
        return isbn;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getAutor(){
        return autor;
    }

    public int getAnioPublicacion(){
        return anioPublicacion;
    }

    public boolean estaDisponible(){
        return disponible;
    }

    // Estos metodos pertenecen tambien a la interfaz Prestable (punto extra)
    @Override
    public void prestar(){
        this.disponible=false;
    }

    @Override
    public void devolver(){
        this.disponible=true;
    }

    // Este metodo permite mostrar la informacion del libro
    @Override
    public String toString(){
        return titulo + " (Autor: " + autor + ", ISBN: " + isbn + ")";
    }
}