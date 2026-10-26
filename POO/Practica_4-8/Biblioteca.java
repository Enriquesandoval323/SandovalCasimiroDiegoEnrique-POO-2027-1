import java.util.ArrayList;

/*
De La Cruz Garcia Juan
Copado Hernandez Luis
Rivera Hernandez Luis
Sandoval Casimiro Diego

Clase principal que administra a toda la biblioteca (libros, usuarios, empleados y préstamos), para esto, necesitamos crear listas (arreglos) dinamicas
para ello, es que necesitamos integrar a la bibliteca java.util.ArrayList, la cual nos va a ayudar a poder hacer la implementación de estas dentro del programa
*/
public class Biblioteca {

    // Listas dinámicas para almacenar la información de forma flexible
    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Bibliotecario> empleados;
    private ArrayList<Prestamo> prestamos;

    // Constructor: Inicializa las listas vacías al crear la biblioteca
    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    // Gestión de Libros

    // Agrega un libro nuevo al final de la lista
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    // Elimina un libro buscando por su código ISBN
    public void eliminarLibro(String isbn) {
        libros.removeIf(l -> l.getIsbn().equals(isbn));
    }

    // Busca y devuelve un libro usando su ISBN (retorna null si no lo encuentra)
    public Libro buscarLibro(String isbn) {
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }

    // Muestra todos los libros del catálogo y su disponibilidad
    public void mostrarCatalogo() {
        System.out.println("CATÁLOGO DE LIBROS");
        for (Libro l : libros) {
            System.out.println(l + " | Disponible: " + l.estaDisponible());
        }
    }

    // Gestión de Usuarios

    // Registra un nuevo usuario en la lista
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Busca un usuario usando su ID numérico
    public Usuario buscarUsuario(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    // Muestra la lista de todos los usuarios registrados
    public void mostrarUsuarios() {
        System.out.println("LISTA DE USUARIOS");
        for (Usuario u : usuarios) {
            u.mostrarInformacion();
        }
    }

    // --- Gestión de Préstamos ---

    // Realiza un préstamo si el libro está disponible
    public void realizarPrestamo(Usuario usuario, Libro libro, String fechaPrestamo, String fechaDevolucion) {
        // Verifica si el libro se puede prestar
        if (libro.estaDisponible()) {
            libro.prestar(); // Marca el libro como prestado
            usuario.setlibrosPrestados(usuario.getlibrosPrestados() + 1); // Suma un libro al usuario

            // Crea el registro del préstamo con sus fechas y lo guarda en la lista
            Prestamo nuevoPrestamo = new Prestamo(usuario, libro);
            nuevoPrestamo.setFechaPrestamo(fechaPrestamo);
            nuevoPrestamo.setFechaDevolucion(fechaDevolucion);
            prestamos.add(nuevoPrestamo);

            System.out.println("¡Préstamo realizado con éxito para " + usuario.getNombre() + "!");
        } else {
            System.out.println("Lo sentimos, el libro '" + libro.getTitulo() + "' no se encuentra disponible.");
        }
    }
    public void devolverLibroPrestamo(String isbn, int idUsuario) {
        // Buscar si existe un préstamo activo que coincida con el libro y el usuario
        for (Prestamo p : prestamos) {
            if (p.getLibro().getIsbn().equals(isbn) && p.getUsuario().getId() == idUsuario && p.estaActivo()) {
                p.getLibro().devolver(); // El libro vuelve a estar disponible
                p.getUsuario().setlibrosPrestados(p.getUsuario().getlibrosPrestados() - 1); // Se descuenta al usuario
                p.finalizarPrestamo(); // El préstamo se marca como inactivo
                System.out.println("¡Devolución registrada con éxito!");
                return;
            }
        }
        System.out.println("No se encontró un préstamo activo con esos datos.");
    }
}