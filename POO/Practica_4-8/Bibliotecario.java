/*
De La Cruz Garcia Juan
Copado Hernandez Luis
Rivera Hernandez Luis
Sandoval Casimiro Diego

En esta parte del codigo es donde vamos a definir a la clase del Bibliotecario, la cual, asi como lo hizo usuario, le
vamos a definir atributos propios, como tambien va a recibir mediante la herencia, los atributos/constructores de la clase
Persona
*/

/*
en esta parte vamos a declarar los atributos que va a usar Bibliotecario, asi como lo hicimos en la clase Usuario, vamos
a declarar en privado todos los atributo, debido a que, como es una clase hija, no va a ser necesario que estos se
"hereden" a otras clases
*/
public class Bibliotecario extends Persona{
    private String turno;
    private double salario;

    // Declaracion de Setters
    public void setTurno(String turno){
        this.turno=turno;
    }

    public void setSalario(double salario){
        this.salario=salario;
    }

    // Declaracion de Getters
    public String getTurno(){
        return turno;
    }

    public double getSalario(){
        return salario;
    }

    // Declaracion de constructores, usamos super para tomar los atributos de Persona
    public Bibliotecario(){
        super();
        this.turno="Sin asignar";
        this.salario=0;
    }

    public Bibliotecario(int id, String nombre, String correo, String turno, double salario){
        super(id, nombre, correo);
        this.turno=turno;
        this.salario=salario;
    }

    // Estos son los tres metodos que pide el profesor para el bibliotecario
    public void autorizarPrestamo(){
        System.out.println("El bibliotecario "+ nombre +" autoriza el prestamo.");
    }

    public void registrarLibro(){
        System.out.println("El bibliotecario "+ nombre +" registra un libro.");
    }

    public void eliminarLibro(){
        System.out.println("El bibliotecario "+ nombre +" elimina un libro.");
    }

    // Sobrecargamos los metodos para que tambien puedan realizar las acciones en Biblioteca
    public void autorizarPrestamo(Biblioteca biblioteca, Usuario usuario, Libro libro){
        if(biblioteca!=null && usuario!=null && libro!=null && usuario.getActivo() && libro.estaDisponible()){
            autorizarPrestamo();
            biblioteca.realizarPrestamo(usuario, libro);
        } else {
            System.out.println("No se pudo autorizar el prestamo.");
        }
    }

    public void registrarLibro(Biblioteca biblioteca, Libro libro){
        if(biblioteca!=null && libro!=null){
            if(libro.getIsbn()!=null && biblioteca.buscarLibro(libro.getIsbn())!=null){
                System.out.println("Ya existe un libro con ese ISBN.");
            } else {
                biblioteca.agregarLibro(libro);
                registrarLibro();
            }
        }
    }

    public void eliminarLibro(Biblioteca biblioteca, String isbn){
        if(biblioteca!=null && biblioteca.buscarLibro(isbn)!=null){
            biblioteca.eliminarLibro(isbn);
            if(biblioteca.buscarLibro(isbn)==null){
                eliminarLibro();
            }
        }
    }

    // Aqui usamos Override, ya que Persona tiene el metodo abstracto
    @Override
    public void mostrarInformacion(){
        System.out.println("ID: "+ id);
        System.out.println("Nombre " + nombre);
        System.out.println("Correo " + correo);
        System.out.println("Turno del empleado " + turno);
        System.out.println("Salario del empleado " + salario);
    }
}