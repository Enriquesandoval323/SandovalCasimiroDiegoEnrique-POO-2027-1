public class Hilo implements Runnable {
    public void run() {
        for(int i = 0 ; i < 5 ; i++) {
            System.out.println("Iteracion " + (i+1) + " de " +
                Thread.currentThread().getName());
        }
        System.out.println("Termina el " +
            Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new Thread(new Hilo (), "Primer hilo").start();
        new Thread(new Hilo (), "Segundo hilo").start();
        System.out.println("Termina el hilo principal");
    }
}
