/*
De La Cruz Garcia Juan
Copado Hernandez Luis
Rivera Hernandez Luis
Sandoval Casimiro Diego

Esta parte del codigo lo que busca es el crear a la clase Usuario, la cual va a heredar atributos de la clase Persona
que realizamos anteriormente, por lo que vamos a hacer uso de la sobre escritura y obviamente, el uso de la herencia
*/
public class Usuario extends Persona {
    /*Aqui declaramos los atributos propios que va a tener Usuario, por lo cual los podemos dejar en privados, puesto que
    esta es la clase hija, por lo que no va a heredar a otras sus atributos
    */
    private int librosPrestados;
    private String telefonoCelular;
    private boolean activo;

    // metodos setters
    public void setlibrosPrestados(int librosPrestados){
        this.librosPrestados=librosPrestados;
    }

    public void setnumeroCelular(String numeroCelular){
        this.telefonoCelular=numeroCelular;
    }

    public void setActivo(boolean activo){
        this.activo=activo;
    }

    // metodos getters
    public int getlibrosPrestados(){
        return librosPrestados;
    }

    public String getnumeroCelular(){
        return telefonoCelular;
    }

    public boolean getActivo(){
        return activo;
    }

    // Aqui se declaran los constructores, y super llama al constructor de Persona
    public Usuario(){
        super();
        this.librosPrestados=0;
        this.activo=true;
    }

    // Conservamos este constructor porque lo utiliza nuestro Main
    public Usuario(int id, String nombre, String correo, String telefonoCelular){
        super(id, nombre, correo);
        this.telefonoCelular=telefonoCelular;
        this.librosPrestados=0;
        this.activo=true;
    }

    // Sobrecarga con el estado activo que solicita el profesor
    public Usuario(int id, String nombre, String correo, boolean activo){
        super(id, nombre, correo);
        this.librosPrestados=0;
        this.activo=activo;
    }

    // Agregamos el telefono y activo juntos, porque con solo cambiar nombres no se puede sobrecargar
    public Usuario(int id, String nombre, String correo, String telefonoCelular, boolean activo){
        super(id, nombre, correo);
        this.telefonoCelular=telefonoCelular;
        this.librosPrestados=0;
        this.activo=activo;
    }

    // Cuando el usuario solicita un prestamo se aumenta su contador
    public void solicitarPrestamo(){
        if(activo){
            librosPrestados++;
        }
    }

    // Al devolver el libro se disminuye el contador, sin permitir numeros negativos
    public void devolverLibro(){
        if(librosPrestados>0){
            librosPrestados--;
        }
    }

    // Aqui sobreescribimos el metodo abstracto de Persona
    @Override
    public void mostrarInformacion(){
        System.out.println("ID: "+ id);
        System.out.println("Nombre "+ nombre);
        System.out.println("Correo "+ correo);
        System.out.println("Telefono o numero de celular "+ telefonoCelular);
        System.out.println("Numero de libros prestados "+ librosPrestados);
        System.out.println("Esta activo "+ activo);
    }
}