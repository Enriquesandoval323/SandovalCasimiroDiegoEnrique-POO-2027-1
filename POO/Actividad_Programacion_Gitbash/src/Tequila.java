// En este código se crea la clase padre Tequila
// Guarda los datos generales de un tequila para posteriormente se muestre su informscion

public class Tequila {

    // Atributos para las clases hijas
    protected String marca;
    protected double precio;

    // Constructor vacío
    public Tequila() {
    }

    // Constructor que recibe la marca y el precio
    public Tequila(String marca, double precio) {
        this.marca = marca;
        this.precio = precio;
    }

    // Metodo para mostrar la información del tequila
    public void mostrarInformacion() {
        System.out.println("Marca: " + marca);
        System.out.println("Precio: $" + precio);
    }
}