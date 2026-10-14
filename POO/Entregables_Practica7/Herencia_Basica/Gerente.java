public class Gerente extends Empleado {

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
}
