public class Computadora {

    // los atributos de la computadora, estos se utilizan para guardar
    // la informacion que va a tener cada objeto que creemos
    String cpu;
    int ram;
    int almacenamiento;
    boolean tieneGpu;
    int fuenteEnergia;

    // un constructor vacio para primero crear el objeto y luego le asignamos los valores mediante los setters
    public Computadora() {
    }

    // definimos el constructor completo, que permite crear una computadora para luego
    // mandar todos sus datos desde el inicio
    public Computadora(String cpu, int ram, int almacenamiento, boolean tieneGpu, int fuenteEnergia) {
        this.cpu = cpu;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.tieneGpu = tieneGpu;
        this.fuenteEnergia = fuenteEnergia;
    }

    // definimos los setters que los utilizamos para asignar o modificar los valores
    // de los atributos de cada computadora

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setAlmacenamiento(int almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public void setTieneGpu(boolean tieneGpu) {
        this.tieneGpu = tieneGpu;
    }

    public void setFuenteEnergia(int fuenteEnergia) {
        this.fuenteEnergia = fuenteEnergia;
    }

    // definimos los getters, estos nos permiten obtener los valores
    // que se encuentran guardados dentro de cada objeto creado

    public String getCpu() {
        return cpu;
    }

    public int getRam() {
        return ram;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public boolean getTieneGpu() {
        return tieneGpu;
    }

    public int getFuenteEnergia() {
        return fuenteEnergia;
    }
}