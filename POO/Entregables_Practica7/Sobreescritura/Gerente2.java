public class Gerente2 extends Empleado2 {

    private int presupuesto;

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