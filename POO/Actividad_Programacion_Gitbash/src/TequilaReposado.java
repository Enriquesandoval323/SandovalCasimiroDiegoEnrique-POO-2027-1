// En este se crea la clase TequilaReposado que se hereda de la clase Tequila
// En este ya ocupo super para mandarle los datos al constructor de la clase padre

public class TequilaReposado extends Tequila {

    // Atributo que solo tiene el tequila reposado
    private int mesesReposo;

    // Constructor
    public TequilaReposado(String marca, double precio, int mesesReposo) {

        // Toma la marca y el precio y se los va a mandar a el constructor de la clase padre
        super(marca, precio);

        this.mesesReposo = mesesReposo;
    }

    // Getter de los meses de reposo
    public int getMesesReposo() {
        return mesesReposo;
    }

    // Se muestra los datos del tequila reposado
    public void mostrarDatosReposado() {
        System.out.println("Marca: " + marca);
        System.out.println("Precio: $" + precio);
        System.out.println("Meses de reposo: " + mesesReposo);
    }
}