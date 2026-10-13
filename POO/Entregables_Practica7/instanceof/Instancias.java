public class Instancias {

    public static void main(String[] args) {

        Gerente gerente = new Gerente();

        // instanceof permite validar si un objeto
        // es de un tipo específico
        if (gerente instanceof Gerente) {
            System.out.println("Instancia de Gerente.");
        }

        // Como Gerente hereda de Empleado también es de tipo Empleado
        if (gerente instanceof Empleado) {
            System.out.println("Instancia de Empleado.");
        }

        // Se verifica que cualquier objeto es una instancia de Object
        if (gerente instanceof Object) {
            System.out.println("Instancia de Object.");
        }
    }
}