/*
De La Cruz Garcia Juan
Copado Hernandez Luis
Rivera Hernandez Luis
Sandoval Casimiro Diego

En este parte del codigo lo que buscamos es crear la clase libro, en este caso se utiliza el modificador private en los atributos de la clase para aplicar el principio de encapsulamiento.
Esto protege los datos internos del libro (como el ISBN, título, autor, año y disponibilidad), evitandoque sean modificados directamente desde el exterior
lo cual obliga a gestionarlos de forma segura mediante los métodos de acceso (getters y setters)
*/
public class Libro{
    //aqui realizamos la declaracion de los atributos que vamos a emplear para nuestra clase libro
    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean disponible;
    private static int totalLibros=0;
    /* Aquie se hacen las declaraciones de los constructiores, en este caso son tres, uno vacio, uno cargado con dos atributos
     y otro con todos los atributios carghados, mas el el estado del libro (osea si esta disponible o no0)*/
    public Libro (){}
    // Declaración de el constructor vacio
    public Libro(String autor, String titulo){
        this.autor=autor;
        this.titulo=titulo;
    }
    // Declaración de el constructor lleno
    public Libro(String isbn, String autor, String titulo, int anioPublicacion){
        this.isbn=isbn;
        this.autor=autor;
        this.titulo=titulo;
        this.anioPublicacion=anioPublicacion;
        this.disponible=true;
        totalLibros++;
    }
    // declariacion de los metodos setters
    public boolean estaDisponible(){
        return this.disponible;
    }

    public void setIsbn(String isbn){
        this.isbn=isbn;
    }

    public void setTitulo( String titulo){
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
    public void prestar() {
        this.disponible = false;
    }

    public void devolver() {
        this.disponible = true;
    }
    // Este metodo perimite ir sobre escribiendo la informacion de los libros conforme se vayan agregando
    @Override
    public String toString (){
        return titulo + "(Autor: " + autor + ", ISBN: " + isbn + ")";
    }
}