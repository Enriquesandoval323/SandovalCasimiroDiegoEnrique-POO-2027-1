/*
De La Cruz Garcia Juan
Copado Hernandez Luis
Rivera Hernandez Luis
Sandoval Casimiro Diego

En esta parte del código implementamos la clase Prestamo, la cual establece una relación
con las clases Usuario y Libro. Permite gestionar las fechas del préstamo y el estado activo o inactivo del libro.
*/
public class Prestamo {

    // Declaración de los atributos
    private Usuario usuario;
    private Libro libro;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private boolean activo;

    // Métodos setters
    public void setFechaPrestamo(String fechaPrestamo){
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(String fechaDevolucion){
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setActivo(boolean activo){
        this.activo = activo;
    }

    // Métodos getters
    public String getFechaPrestamo(){
        return fechaPrestamo;
    }

    public String getFechaDevolucion(){
        return fechaDevolucion;
    }

    public boolean getActivo(){
        return activo;
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    // Constructor vacío por defecto
    public Prestamo() {}

    // Constructor con todos los parametros, incluyendo los que se heredan de las clases libro y usuario
    public Prestamo(Usuario usuario, Libro libro, String fechaPrestamo, String fechaDevolucion, boolean activo){
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.activo = activo;
    }

    public Prestamo(Usuario usuario, Libro libro) {
        this.usuario = usuario;
        this.libro = libro;
        this.activo = true;
    }

    // Método para iniciar el préstamo
    public void iniciarPrestamo() {
        this.activo = true;
        System.out.println("Fecha de prestamo: " + this.fechaPrestamo);
        System.out.println("Libro prestado: " + this.libro);
        System.out.println("El dia limite para hacer la devolucion de el libro: " + this.libro + " sera el dia: " + this.fechaDevolucion);
    }

    // Método para finalizar el préstamo, cambiando el estado a inactivo
    public void finalizarPrestamo() {
        this.activo = false;
        System.out.println("El prestamo del libro ha sido finalizado");
    }

    // Método para consultar si el préstamo se encuentra activo actualmente
    public boolean estaActivo() {
        return activo;
    }
}