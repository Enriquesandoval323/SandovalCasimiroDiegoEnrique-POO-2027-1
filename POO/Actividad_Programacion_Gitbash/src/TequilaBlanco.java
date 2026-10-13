// Para este codigo se crea la clase TequilaBlanco que hereda de Tequila
// Es en este donde se ocupa la herencia simple

public class TequilaBlanco extends Tequila {

    // Atributo que solo el tequila blanco va a tener
    private int porcentajeAlcohol;

    // Constructor
    public TequilaBlanco(String marca, double precio, int porcentajeAlcohol) {
        this.marca = marca;
        this.precio = precio;
        this.porcentajeAlcohol = porcentajeAlcohol;
    }

    // Getter del porcentaje de alcohol
    public int getPorcentajeAlcohol() {
        return porcentajeAlcohol;
    }

    // Muestra los datos del tequila blanco
    public void mostrarDatosBlanco() {
        System.out.println("Marca: " + marca);
        System.out.println("Precio: $" + precio);
        System.out.println("Porcentaje de alcohol: " + porcentajeAlcohol + "%");
    }
}