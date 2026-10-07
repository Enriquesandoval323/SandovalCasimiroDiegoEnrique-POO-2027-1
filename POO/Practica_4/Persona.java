/*
De La Cruz Garcia Juan 
Copado Hernandez Luis 
Rivera Hernandez Luis
Sandoval Casimiro Diego
Esta es la parte "central" del codigo, ya que esta sera la clase padre tanto para bibliotecarios, asi como para
usuario, en donde vamos a definir los atributos, id, nombre y correo, en donde tambien vamos a definir con abstract
el metodo que las clases hijas vana  estar obligadas a cumplir 
*/
public abstract class Persona{
    protected int id;
    protected String nombre;
    protected String correo;
    // declaracion de los atributos a utilizar 
    public Persona (){}
    public Persona (int id, String nombre, String correo){
        this.id=id;
        this.nombre=nombre;
        this.correo=correo;
    }
    // Metodos Setters 
    public void setId (int id){
        this.id=id;
    }
    
    public void setNombre (String nombre){
        this.nombre=nombre;
    }
    public void setCorreo (String correo){
        this.correo=correo;
    }
    //Metodos Getters
    public int getId(){
        return id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getCorreo(){
        return correo;
    }
    // metodo abstarcto, es el que va a ser obligatorio que lleven las clases hijas 
    public abstract void mostrarInformacion();
}
