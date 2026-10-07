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
    private String numeroCelular;
    private boolean activo;
    // metodos setters 
    public void setlibrosPrestados(int librosPrestados){
        this.librosPrestados=librosPrestados;
    }
    
     public void setnumeroCelular (String numeroCelular){
        this.numeroCelular=numeroCelular;
    }
    
     public void setActivo (boolean activo){
        this.activo=activo;
    }
    
    public int getlibrosPrestados(){
        return librosPrestados;
    }
    //metodos getters 
    public String getnumeroCelular(){
        return numeroCelular;
    }
    
    public boolean getActivo (){
        return activo;
    }
    // Aqui se declaran a los constructores, en donde se usa super para poder llamar los constructores de la clase padre persona
    public Usuario(){
        super();
        this.librosPrestados=0;
        this.activo=true;
    }
    
    public Usuario(int id, String nombre, String correo, String numeroCelular){
        super(id, nombre, correo);
        this.numeroCelular=numeroCelular;
        this.librosPrestados=0;
        this.activo=true;
        
    }
    //Aqui lo usamos para poder sobreescribir la informacion que vayamos agregando, mas que nada esto se usara mas adelante 
    //pero se debe de usar de manera obligatoria, por ser la clase hija 
    @Override
    public void mostrarInformacion(){
        System.out.println("ID: "+ id);
        System.out.println("Nombre "+ nombre);
        System.out.println("Correo "+ correo);
        System.out.println("Telefono o numero de celular "+ numeroCelular);
        System.out.println("Numero de libros prestados "+ librosPrestados);
        System.out.println("Esta activo "+ activo);
    }
}
