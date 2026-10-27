/*
De La Cruz Garcia Juan
Copado Hernandez Luis
Rivera Hernandez Luis
Sandoval Casimiro Diego

En esta parte del codigo implementamos la clase Prestamo, la cual establece una relacion
con las clases Usuario y Libro. Permite gestionar las fechas del prestamo y el estado activo o inactivo del libro.
*/
public class Prestamo {
    // Declaracion de los atributos
    private Usuario usuario;
    private Libro libro;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private boolean activo;

    // Metodos setters
    public void setFechaPrestamo(String fechaPrestamo){
        this.fechaPrestamo=fechaPrestamo;
    }

    public void setFechaDevolucion(String fechaDevolucion){
        this.fechaDevolucion=fechaDevolucion;
    }

    public void setActivo(boolean activo){
        this.activo=activo;
    }

    // Metodos getters
    public String getFechaPrestamo(){
        return fechaPrestamo;
    }

    public String getFechaDevolucion(){
        return fechaDevolucion;
    }

    public boolean getActivo(){
        return activo;
    }

    public Libro getLibro(){
        return libro;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    // Constructor vacio
    public Prestamo(){}

    // Constructor con todos los parametros
    public Prestamo(Usuario usuario, Libro libro, String fechaPrestamo, String fechaDevolucion, boolean activo){
        this.usuario=usuario;
        this.libro=libro;
        this.fechaPrestamo=fechaPrestamo;
        this.fechaDevolucion=fechaDevolucion;
        this.activo=activo;
    }

    // Al crear el objeto todavia no hemos realizado el prestamo, por eso inicia en false
    public Prestamo(Usuario usuario, Libro libro){
        this.usuario=usuario;
        this.libro=libro;
        this.activo=false;
    }

    // El prestamo se comunica con Libro y Usuario, como se muestra en el PDF
    public void iniciarPrestamo(){
        if(activo){
            System.out.println("Este prestamo ya se encuentra activo.");
            return;
        }
        if(usuario==null || libro==null || !usuario.getActivo() || !libro.estaDisponible()){
            System.out.println("No se puede iniciar el prestamo.");
            return;
        }
        libro.prestar();
        usuario.solicitarPrestamo();
        this.activo=true;
        if(fechaPrestamo!=null){
            System.out.println("Fecha de prestamo: " + fechaPrestamo);
        }
        System.out.println("Libro prestado: " + libro);
        if(fechaDevolucion!=null){
            System.out.println("El dia limite para hacer la devolucion del libro sera el dia: " + fechaDevolucion);
        }
    }

    // Al finalizar se regresa el libro y se disminuye el contador del usuario
    public void finalizarPrestamo(){
        if(!activo){
            System.out.println("Este prestamo ya esta inactivo.");
            return;
        }
        libro.devolver();
        usuario.devolverLibro();
        this.activo=false;
        System.out.println("El prestamo del libro ha sido finalizado");
    }

    public boolean estaActivo(){
        return activo;
    }
}