public class Instancias {

    public static void main(String[] args) {

        Gerente gerente = new Gerente();

        if (gerente instanceof Gerente) {
            System.out.println("Instancia de Gerente.");
        }

        if (gerente instanceof Empleado) {
            System.out.println("Instancia de Empleado.");
        }

        if (gerente instanceof Object) {
            System.out.println("Instancia de Object.");
        }
    }
}