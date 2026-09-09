import java.util.Scanner;

// El objetivo de este programa es que se pueda crear un arreglo de 10 computadoras,
// y las primeras 8 las va a crear el usuario y las ultimas 2 computadoras
// se van a crear por medio de el constructor que esta completo

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // se crea un arreglo con espacio para guardar 10 computadoras
        Computadora[] computadoras = new Computadora[10];

        // definimos las variables que vamos a ocupar para guardar los datos
        // que vaya ingresando el usuario
        String cpu;
        int ram;
        int almacenamiento;
        String respuestaGpu;
        boolean tieneGpu;
        int fuenteEnergia;

        int i = 0;

        // aqui mediante un ciclo do-while pedimos los datos
        // de las primeras 8 computadoras
        do {

            System.out.println(" Ingrese los datos para la Computadora [" + (i + 1) + "]");

            System.out.println("Por favor proporcione el CPU: ");
            cpu = sc.nextLine();

            System.out.println("Por favor proporcione la Memoria RAM (GB): ");
            ram = sc.nextInt();

            System.out.println("Por favor proporcione el Almacenamiento (GB): ");
            almacenamiento = sc.nextInt();

            System.out.println("¿Cuenta con gráficos dedicados/GPU? (S/N): ");
            respuestaGpu = sc.next();

            // ahora mediante el if-else checamos la respuesta del usuario
            // y se guarda como true si tiene GPU o false si no tiene
            if (respuestaGpu.equalsIgnoreCase("S")) {
                tieneGpu = true;
            } else {
                tieneGpu = false;
            }

            System.out.println("Por favor proporcione la Fuente de energia (Watts): ");
            fuenteEnergia = sc.nextInt();

            // con este limpiamos el salto de linea que queda luego de usar nextInt
            // para que no cause problemas cuando se vuelva a pedir el CPU
            sc.nextLine();

            // Se crea una computadora utilizando el constructor vacio
            Computadora compu = new Computadora();

            // despues se le van a asignar todos los datos ingresados
            // por el usuario mediante los setters
            compu.setCpu(cpu);
            compu.setRam(ram);
            compu.setAlmacenamiento(almacenamiento);
            compu.setTieneGpu(tieneGpu);
            compu.setFuenteEnergia(fuenteEnergia);

            // Se guarda el objeto que acabamos de crear
            // en la posicion que le corresponde del arreglo
            computadoras[i] = compu;

            i++;

            System.out.println();

        } while (i < 8);

        // las ultimas 2 computadoras se crean utilizando
        // directamente el constructor completo
        System.out.println("Asignando las ultimas 2 computadoras con el constructor completo");

        computadoras[8] = new Computadora("Intel Core i7", 16, 512, true, 650);
        computadoras[9] = new Computadora("AMD Ryzen 5", 8, 256, false, 450);

        // por medio de un ciclo for, se recorre todo el arreglo
        // para mostrar la informacion de las 10 computadoras
        System.out.println("\nLISTA DE COMPUTADORAS");

        for (int j = 0; j < computadoras.length; j++) {

            System.out.println("Computadora " + (j + 1) + ":");

            // Se utilizan los getters para obtener toda la informacion de las computadoras
            System.out.println("  CPU: " + computadoras[j].getCpu());
            System.out.println("  RAM: " + computadoras[j].getRam() + " GB");
            System.out.println("  Almacenamiento: " + computadoras[j].getAlmacenamiento() + " GB");
            System.out.println("  Graficos dedicados (GPU): "
                    + (computadoras[j].getTieneGpu() ? "Si" : "No"));
            System.out.println("  Fuente de Energia: "
                    + computadoras[j].getFuenteEnergia() + " W");
        }

        // Se cierra el Scanner cuando ya no se va a utilizar
        sc.close();
    }
}