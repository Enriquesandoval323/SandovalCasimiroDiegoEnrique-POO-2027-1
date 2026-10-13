// La finalidad de este código es que calcule el precio de los tequilas comprados
// Aca ya ocupo la sobrecarga por medio de los metodos con el mismo nombre y diferentes parámetros.

public class CalculadoraCompra {

    // Calcula el precio de una sola botella
    public double calcularTotal(double precio) {
        return precio;
    }

    // Calcula el precio de varias botellas
    public double calcularTotal(double precio, int cantidad) {
        return precio * cantidad;
    }
}