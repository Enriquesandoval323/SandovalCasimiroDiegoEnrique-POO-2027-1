/*
De La Cruz Garcia Juan 
Copado Hernandez Luis 
Rivera Hernandez Luis
Sandoval Casimiro Diego

en esta parte del codigo es donde vamos a definir a la clase del Bibliotecario, la cual, asi como lo hizo usuario, le 
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
    
        // declaracion de Setters
        
    public void setTurno(String turno){
        this.turno=turno;
    }

    public void setSalario(double salario){
        this.salario=salario;
    }
        //declaracion de Getters 
    public String getTurno(){
        return turno;
    }
    
    public double getSalario(){
        return salario;
    }
        //declaracion de constructores
    // en esta parte vamos a hacer uso de la palabra reservada super, la cual, vamos a utilizar para poder tomar los constructores 
    // que declaramos en la clase persona 
    public Bibliotecario(){
        super();
        this.turno=turno;
        this.salario=0;
    }
    
    public Bibliotecario(int id, String nombre, String correo, String turno, double salario){
        super (id, nombre, correo);
        this.turno=turno;
        this.salario=0;
    }
    //Aqui lo usamos para poder sobreescribir la informacion que vayamos agregando, mas que nada esto se usara mas adelante 
    //pero se debe de usar de manera obligatoria, por ser la clase hija 
    @Override
    public void mostrarInformacion(){
        System.out.println("ID: "+ id);
        System.out.println("Nombre " + nombre);
        System.out.println("Correo " + correo);
        System.out.println("Turno del empleado " + turno);
        System.out.println("Salario del empelado " + salario);
    }
   
}
