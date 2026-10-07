/*
De La Cruz Garcia Juan 
Copado Hernandez Luis 
Rivera Hernandez Luis
Sandoval Casimiro Diego
El objetivo de este codigo es meramente el ver si es que nuestras clases usuario y bibliotecario es que estan 
obteniendo y pueden mostrar la informacion que se necesita, por eso es que construimos los constructores con 
la informacion ya establecida 
*/
public class Main {
    public static void main(String[] args) {
        // Crear un objeto Usuario
        Usuario usuario1 = new Usuario(1, "Juan Perez", "juan@gmail.com", "5512345678");
        
        // Crear un objeto Bibliotecario
        Bibliotecario biblio1 = new Bibliotecario(101, "Ana Gomez", "ana@biblioteca.com", "Matutino", 15000.0);

        // Probar polimorfismo y visualización de datos
        System.out.println("PRUEBA DE CLASES");
        usuario1.mostrarInformacion();
        System.out.println("------------------------");
        biblio1.mostrarInformacion();
    }
}
