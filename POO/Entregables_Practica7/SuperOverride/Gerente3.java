public class Gerente3 extends Empleado3 {

    private int presupuesto;

    public Gerente3(String nombre, int sueldo,
                    int numEmpleado, int presupuesto) {

        super(nombre, sueldo, numEmpleado);

        this.presupuesto = presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getPresupuesto() {
        return this.presupuesto;
    }

    void asignarPresupuesto(int p) {
        presupuesto = p;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nPresupuesto: " + this.presupuesto;
    }
}