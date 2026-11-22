/*
El proposito de este programa es representar una reservacion de un salon de fiestas.
Se almacenan el cliente, el inicio y el fin del evento. La clase valida los datos
y permite comprobar si una nueva reservacion ocupa parte de un horario existente.

Integrantes del equipo:
Copado Hernandez Luis Felipe
De La Cruz Garcia Juan Antonio
Rivera Hernandez Luis Angel
Salvador Casimiro Diego
*/
// Se utiliza LocalDateTime para trabajar con fecha y hora en una misma variable.
import java.time.LocalDateTime;

// Esta clase representa la informacion basica de un evento reservado.
public class Reservacion{
    // Los atributos son privados para que otras clases no los modifiquen directamente.
    //Atributos
    private String nombreCliente;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    // El constructor recibe el cliente, el comienzo del evento y su duracion en horas.
    // Antes de asignar los datos, comprueba que se puedan utilizar correctamente.
    //Constructor
    public Reservacion(String nombreCliente, LocalDateTime fechaInicio, int duracionHoras){
        // Se rechazan nombres vacios y caracteres que alterarian el formato del archivo.
        if(nombreCliente==null || nombreCliente.trim().isEmpty() || nombreCliente.contains("|") || nombreCliente.contains("\n") || nombreCliente.contains("\r"))
        {   throw new IllegalArgumentException("Nombre de cliente invalido.");  }
        // El evento debe comenzar en una hora exacta, sin minutos ni segundos adicionales.
        if(fechaInicio==null || fechaInicio.getMinute()!=0 || fechaInicio.getSecond() != 0 || fechaInicio.getNano()!=0)
        {   throw new IllegalArgumentException("La reservacion debe comenzar en una hora exacta."); }
        // Se permite reservar entre 1 y 24 horas, de acuerdo con las reglas del programa.
        if(duracionHoras<1 || duracionHoras>24)
        {   throw new IllegalArgumentException("La duracion debe ser de 1 a 24 horas.");    }

        // Una vez validada la informacion, se asignan los atributos del objeto.
        this.nombreCliente = nombreCliente.trim();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaInicio.plusHours(duracionHoras);
    }

    // Los getters permiten consultar datos de la reservacion sin acceder a sus atributos.
    // Metodos getters
    public String getNombreCliente(){   return nombreCliente;   }
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
    // La duracion se obtiene calculando las horas entre inicio y fin.
    public int getDuracionHoras() {
        return (int) java.time.Duration.between(fechaInicio, fechaFin).toHours();
    }
    // El metodo devuelve true si los intervalos comparten parte de su tiempo.
    // Dos eventos que se tocan justo en el fin e inicio no se consideran traslapados.
    // Comprueba si dos intervalos de tiempo se cruzan
    // Se reciben los extremos del intervalo que queremos comprobar.
    public boolean hayConflicto(LocalDateTime inicio, LocalDateTime fin) {
        // Solo hay conflicto cuando el nuevo inicio es anterior al fin guardado
        // y el nuevo fin es posterior al inicio guardado.
        if (inicio.isBefore(fechaFin) && fin.isAfter(fechaInicio)) {
            return true;
        }
        return false;
    }

    // Muestra en consola los datos de un evento registrado.
    public void mostrarInformacion() {
        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Inicio: " + fechaInicio.toLocalDate() + " " + fechaInicio.toLocalTime());
        System.out.println("Fin: " + fechaFin.toLocalDate() + " " + fechaFin.toLocalTime());
        System.out.println("Duracion: " + getDuracionHoras() + " horas");
    }
}
