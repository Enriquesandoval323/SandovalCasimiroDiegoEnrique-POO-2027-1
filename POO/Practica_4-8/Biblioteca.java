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
    public Biblioteca(){
        this.libros=new ArrayList<>();
        this.usuarios=new ArrayList<>();
        this.empleados=new ArrayList<>();
        this.prestamos=new ArrayList<>();
    }

    // Gestion de Libros

    // Agrega un libro nuevo al final de la lista
    public void agregarLibro(Libro libro){
        if(libro!=null){
            // Verificamos que no haya otro libro con el mismo ISBN
            if(libro.getIsbn()!=null && buscarLibro(libro.getIsbn())!=null){
                System.out.println("Ya existe un libro con ese ISBN.");
                return;
            }
            libros.add(libro);
        }
    }

    // Elimina un libro buscando por su codigo ISBN
    public void eliminarLibro(String isbn){
        Libro libro=buscarLibro(isbn);
        if(libro==null){
            System.out.println("No se encontro un libro con ese ISBN.");
        } else if(!libro.estaDisponible()){
            System.out.println("No se puede eliminar un libro que esta prestado.");
        } else {
            libros.remove(libro);
        }
    }

    // Busca y devuelve un libro usando su ISBN (retorna null si no lo encuentra)
    public Libro buscarLibro(String isbn){
        if(isbn==null){
            return null;
        }
        for(Libro l : libros){
            if(isbn.equals(l.getIsbn())){
                return l;
            }
        }
        return null;
    }

    // Ejemplo de sobrecarga que aparece en las instrucciones
    public Libro buscarLibro(String titulo, String autor){
        for(Libro l : libros){
            if(titulo!=null && autor!=null && titulo.equals(l.getTitulo()) && autor.equals(l.getAutor())){
                return l;
            }
        }
        return null;
    }

    // Muestra todos los libros del catalogo y su disponibilidad
    public void mostrarCatalogo(){
        System.out.println("CATÁLOGO DE LIBROS");
        for(Libro l : libros){
            System.out.println(l + " | Disponible: " + l.estaDisponible());
        }
    }

    // Gestion de Usuarios

    // Registra un nuevo usuario en la lista
    public void registrarUsuario(Usuario usuario){
        if(usuario!=null){
            if(buscarUsuario(usuario.getId())!=null){
                System.out.println("Ya existe un usuario con ese ID.");
                return;
            }
            usuarios.add(usuario);
        }
    }

    // Busca un usuario usando su ID numerico
    public Usuario buscarUsuario(int id){
        for(Usuario u : usuarios){
            if(u.getId()==id){
                return u;
            }
        }
        return null;
    }

    // Muestra la lista de todos los usuarios registrados
    public void mostrarUsuarios(){
        System.out.println("LISTA DE USUARIOS");
        for(Usuario u : usuarios){
            u.mostrarInformacion();
        }
    }

    // Gestion de Bibliotecarios
    public void registrarBibliotecario(Bibliotecario empleado){
        if(empleado!=null){
            empleados.add(empleado);
        }
    }

    public void mostrarBibliotecarios(){
        System.out.println("LISTA DE BIBLIOTECARIOS");
        for(Bibliotecario b : empleados){
            b.mostrarInformacion();
        }
    }

    // Gestion de Prestamos

    // Firma de dos parametros que pide el profesor
    public void realizarPrestamo(Usuario usuario, Libro libro){
        realizarPrestamo(usuario, libro, null, null);
    }

    // Conservamos la sobrecarga con fechas que utilizamos desde Main
    public void realizarPrestamo(Usuario usuario, Libro libro, String fechaPrestamo, String fechaDevolucion){
        if(usuario==null || libro==null){
            System.out.println("No se puede realizar el prestamo con estos datos.");
            return;
        }
        if(buscarUsuario(usuario.getId())!=usuario || buscarLibro(libro.getIsbn())!=libro){
            System.out.println("El usuario o el libro no estan registrados en esta biblioteca.");
            return;
        }
        if(!usuario.getActivo()){
            System.out.println("No se puede realizar el prestamo porque el usuario esta inactivo.");
            return;
        }
        if(!libro.estaDisponible()){
            System.out.println("Lo sentimos, el libro '" + libro.getTitulo() + "' no se encuentra disponible.");
            return;
        }

        // Prestamo llama a Libro y Usuario, para no contar el prestamo dos veces
        Prestamo nuevoPrestamo=new Prestamo(usuario, libro);
        nuevoPrestamo.setFechaPrestamo(fechaPrestamo);
        nuevoPrestamo.setFechaDevolucion(fechaDevolucion);
        nuevoPrestamo.iniciarPrestamo();
        if(nuevoPrestamo.estaActivo()){
            prestamos.add(nuevoPrestamo);
            System.out.println("¡Préstamo realizado con éxito para " + usuario.getNombre() + "!");
        }
    }

    // Firma que pide el profesor para devolver un objeto Prestamo
    public void devolverLibro(Prestamo prestamo){
        if(prestamo!=null && prestamos.contains(prestamo) && prestamo.estaActivo()){
            prestamo.finalizarPrestamo();
            System.out.println("¡Devolución registrada con éxito!");
        } else {
            System.out.println("No se encontro un prestamo activo con esos datos.");
        }
    }

    // Conservamos este metodo para devolver por ISBN e ID desde nuestro menu
    public void devolverLibroPrestamo(String isbn, int idUsuario){
        for(Prestamo p : prestamos){
            if(p.getLibro()!=null && p.getUsuario()!=null && isbn!=null &&
                    isbn.equals(p.getLibro().getIsbn()) && p.getUsuario().getId()==idUsuario && p.estaActivo()){
                devolverLibro(p);
                return;
            }
        }
        System.out.println("No se encontró un préstamo activo con esos datos.");
    }

    // Muestra todos los prestamos que se han registrado y su estado
    public void mostrarPrestamos(){
        System.out.println("LISTA DE PRESTAMOS");
        if(prestamos.isEmpty()){
            System.out.println("Todavia no hay prestamos registrados.");
        }
        for(Prestamo p : prestamos){
            System.out.println("Usuario: " + p.getUsuario().getNombre());
            System.out.println("Libro: " + p.getLibro().getTitulo());
            System.out.println("Fecha de prestamo: " + p.getFechaPrestamo());
            System.out.println("Fecha de devolucion: " + p.getFechaDevolucion());
            System.out.println("Prestamo activo: " + p.estaActivo());
            System.out.println("----------------------");
        }
    }
}