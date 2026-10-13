// Ahora creo la clase TequilaAñejo que al igual que las anteriores se hereda de la clase Tequila
// Para este ocupo el Override, el cual funciona para modificar el metodo mostrarInformacion de la clase padre

public class TequilaAñejo extends Tequila {

    // Atributo unico del tequila añejo
    private int añosMaduracion;

    // Constructor
    public TequilaAñejo(String marca, double precio, int añosMaduracion) {
        this.marca = marca;
        this.precio = precio;
        this.añosMaduracion = añosMaduracion;
    }

    // Getter de los años de maduración
    public int getAñosMaduracion() {
        return añosMaduracion;
    }

    // Sobrescribe el metodo que existe en la clase Tequila
    @Override
    public void mostrarInformacion() {
        System.out.println("Marca: " + marca);
        System.out.println("Precio: $" + precio);
        System.out.println("Años de maduración: " + añosMaduracion);
    }
}