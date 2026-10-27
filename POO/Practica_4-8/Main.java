/*
De La Cruz Garcia Juan
Copado Hernandez Luis
Rivera Hernandez Luis
Sandoval Casimiro Diego

Este codigo tiene como proposito, el crear el menu interactivo con el usuario, en donde este ya ingrese la informacion tanto de el libro, como sus datos, asi como tambien
el poder solicitar un prestamo, además de poder gestionar la visualización, como lo seria, poder ver los usuarios registardos, asi como tambien los libros que se tienen

*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca miBiblioteca = new Biblioteca();

        int opcion = 0;

        do {
            System.out.println("SISTEMA DE GESTIÓN DE BIBLIOTECA");
            System.out.println("1. Registrar Libro");
            System.out.println("2. Registrar Usuario");
            System.out.println("3. Mostrar Catálogo de Libros");
            System.out.println("4. Mostrar Lista de Usuarios");
            System.out.println("5. Realizar Préstamo de Libro");
            System.out.println("6. Realizar la devolución del libro");
            System.out.println("7. Eliminar Libro del Catálogo");
            System.out.println("8. Mostrar Préstamos");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");

            // Validación a prueba de errores si ingresan letras en lugar de números
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                // Esta parte se encarga de que si el usuario ingresa texto, y NumberFormatException es el error que se muestra dentro de java si es que no se ingresa
                // un numero en esta parte, por lo que catch se encarga de interceptarlo e indicarle al usuario que debe de ingresar un numero
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingresa un número válido del menú.");
                continue; // Regresa al inicio del menú sin romper el programa
            }
            // SwitchCase con las opciones de el menu
            switch (opcion) {
                case 1:
                    System.out.println("\nREGISTRAR NUEVO LIBRO");
                    System.out.print("Ingresa el ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Ingresa el Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingresa el Autor: ");
                    String autor = scanner.nextLine();
                    // aqui hacemos una validacion para que el año, sea uno valido y evitar errores
                    int anio = 0;
                    // se usa un ciclo while para mantener al usuario ahi hasta q ingrese los datos correctos
                    while (true) {
                        // Intenta convertir el texto ingresado (scanner.nextLine()) a un número entero. Si la conversión es exitosa, se le permite al usuario avanzar
                        try {
                            System.out.print("Ingresa el Año de Publicación: ");
                            anio = Integer.parseInt(scanner.nextLine());
                            break;
                            // Si el usuario no ingresa un numero (osea ingreasa texto), captura el error de formato NumberFormatException, para evitar que el usuario avanze hasta que se ingrese un año valido
                        } catch (NumberFormatException e) {
                            System.out.println("El año debe ser un número entero. Inténtalo de nuevo.");
                        }
                    }
                    // Verificamos que ese ISBN no este registrado para evitar libros repetidos
                    if(miBiblioteca.buscarLibro(isbn)!=null){
                        System.out.println("Ya existe un libro con ese ISBN.");
                    } else {
                        // Se crea un nuevo objeto llamado libro, con todos los datos que se proporcionaron
                        Libro nuevoLibro = new Libro(isbn, titulo, autor, anio);
                        miBiblioteca.agregarLibro(nuevoLibro);
                        System.out.println("¡Libro registrado con éxito!");
                    }
                    break;

                case 2:
                    // Para realizar el registro de un usuario primero se le pide el id
                    System.out.println("\nREGISTRAR NUEVO USUARIO");
                    int idUsuario = 0;
                    while (true) {
                        // Intenta convertir el texto ingresado (scanner.nextLine()) a un número entero. Si la conversión es exitosa, se le permite al usuario avanzar
                        try {
                            System.out.print("Ingresa el ID del usuario (número): ");
                            idUsuario = Integer.parseInt(scanner.nextLine());
                            break;
                            // Si el usuario no ingresa un numero (osea ingreasa texto), captura el error de formato NumberFormatException, para evitar que el usuario avanze hasta que se ingrese un año valido
                        } catch (NumberFormatException e) {
                            System.out.println("El ID debe ser numérico. Inténtalo de nuevo.");
                        }
                    }
                    // Ahora ingresamos los demas datos
                    System.out.print("Ingresa el Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingresa el Correo: ");
                    String correo = scanner.nextLine();
                    System.out.print("Ingresa el Teléfono celular: ");
                    String telefono = scanner.nextLine();

                    // Se crea el usuario solo si el ID no esta registrado
                    if(miBiblioteca.buscarUsuario(idUsuario)!=null){
                        System.out.println("Ya existe un usuario con ese ID.");
                    } else {
                        Usuario nuevoUsuario = new Usuario(idUsuario, nombre, correo, telefono);
                        miBiblioteca.registrarUsuario(nuevoUsuario);
                        System.out.println("¡Usuario registrado con éxito!");
                    }
                    break;

                case 3:
                    // Aqui mostramos los libros que se hayan ya registarado
                    System.out.println("\n");
                    miBiblioteca.mostrarCatalogo();
                    break;

                case 4:
                    // Aqui mostramos a los usuario que se registaron
                    System.out.println("\n");
                    miBiblioteca.mostrarUsuarios();
                    break;

                case 5:
                    // Esta parte es la que se encarga de hacer un prestamo
                    System.out.println("\nREALIZAR PRÉSTAMO");
                    System.out.print("Ingresa el ISBN del libro a prestar: ");
                    String isbnPrestamo = scanner.nextLine();
                    // Se manda a llamar al libro desde su lista
                    Libro libroEncontrado = miBiblioteca.buscarLibro(isbnPrestamo);
                    // Esta parte se encarga de primero ver si el libro existe
                    if (libroEncontrado != null) {
                        // Si existe se procede a pedir el ID
                        int idBuscar = 0;
                        // Intenta convertir el texto ingresado (scanner.nextLine()) a un número entero. Si la conversión es exitosa, se le permite al usuario avanzar
                        try {
                            System.out.print("Ingresa el ID del usuario que solicita el libro: ");
                            idBuscar = Integer.parseInt(scanner.nextLine());
                            // Si el usuario no ingresa un numero (osea ingreasa texto), captura el error de formato NumberFormatException, para evitar que el usuario avanze hasta que se ingrese un ID correcto
                        } catch (NumberFormatException e) {
                            System.out.println("ID inválido.");
                            break;
                        }
                        // Se manda a llamar a la lista donde se encuentran los usuarios
                        Usuario usuarioEncontrado = miBiblioteca.buscarUsuario(idBuscar);
                        // Si el usuario existe
                        if (usuarioEncontrado != null) {
                            // Ya aquie se hace el registro del prestamo en donde se establece la fecha en que inicia y en la que debe de terminar el prestamo
                            System.out.print("Ingresa la fecha de préstamo (ej: 2026-10-24): ");
                            String fPrestamo = scanner.nextLine();
                            System.out.print("Ingresa la fecha de devolución límite (ej: 2026-11-07): ");
                            String fDevolucion = scanner.nextLine();
                            miBiblioteca.realizarPrestamo(usuarioEncontrado, libroEncontrado, fPrestamo, fDevolucion);
                            // En caso de que el Id no se encuentre
                        } else {
                            System.out.println("Usuario no encontrado en el sistema.");
                        }
                        // En caso de que no se encuentre el libro que se quiere sacar
                    } else {
                        System.out.println("Libro no encontrado en el catálogo.");
                    }
                    break;

                case 6:
                    // gestion d ela devolucion de libros
                    System.out.println("\nREGISTRAR DEVOLUCIÓN ");
                    System.out.print("Ingresa el ISBN del libro que se va a devolver: ");
                    String isbnDevolucion = scanner.nextLine();
                    Libro libroDevolver = miBiblioteca.buscarLibro(isbnDevolucion);

                    if (libroDevolver != null) {
                        int idUsuarioDev = 0;
                        // Intenta convertir el texto ingresado (scanner.nextLine()) a un número entero. Si la conversión es exitosa, se le permite al usuario avanzar
                        try {
                            System.out.print("Ingresa el ID del usuario que devuelve el libro: ");
                            idUsuarioDev = Integer.parseInt(scanner.nextLine());
                            // Si el usuario no ingresa un numero (osea ingreasa texto), captura el error de formato NumberFormatException, para evitar que el usuario avanze hasta que se ingrese un ID correcto
                        } catch (NumberFormatException e) {
                            System.out.println("ID inválido.");
                            break;
                        }
                        Usuario usuarioDevolver = miBiblioteca.buscarUsuario(idUsuarioDev);
                        if (usuarioDevolver != null) {
                            // Se busca el prestamo que ya se habia guardado, sin crear otro
                            miBiblioteca.devolverLibroPrestamo(isbnDevolucion, idUsuarioDev);
                        } else {
                            System.out.println("Usuario no encontrado.");
                        }
                    } else {
                        System.out.println("Libro no encontrado en el catálogo.");
                    }
                    break;
                // La opcion para eliminar un libro
                case 7:
                    System.out.println("\nELIMINAR LIBRO");
                    System.out.print("Ingresa el ISBN del libro que deseas eliminar: ");
                    String isbnEliminar = scanner.nextLine();

                    // Verificamos si el libro existe antes de borrarlo
                    Libro libroAEliminar = miBiblioteca.buscarLibro(isbnEliminar);
                    if(libroAEliminar!=null){
                        if(!libroAEliminar.estaDisponible()){
                            System.out.println("No se puede eliminar un libro que esta prestado.");
                        } else {
                            miBiblioteca.eliminarLibro(isbnEliminar);
                            System.out.println("¡Libro eliminado del catálogo con éxito!");
                        }
                    } else {
                        System.out.println("No se encontró ningún libro con ese ISBN.");
                    }
                    break;
                case 8:
                    // Nueva opcion para ver todos los prestamos y su estado
                    miBiblioteca.mostrarPrestamos();
                    break;
                case 9:
                    System.out.println("Saliendo del programa");
                    break;
                // En caso de que no se seleccione una opcion valida
                default:
                    System.out.println("Opción inválida. Elige un número del 1 al 9.");
            }

        } while (opcion != 9);

        scanner.close();
    }
}