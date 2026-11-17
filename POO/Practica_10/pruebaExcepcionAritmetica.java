/*
El proposito de este programa es el de mostrar como es que funcionan  de manera practica las dos excepciones ArithmeticException e InputMismatchException.
El programa se basa, en que se le va a pedir a el usuario que ingrese dos numeros, para posteriormente realizar una division.
Se usa ArithmeticException para poder evitar que el usuario realize una division entre 0, por otro lado, se usa InputMismatchException, para evitar que el usuario ingrese letras ya sea en el numerador o en el denominador

Copado Hernandez Luis Felipe
De La Cruz Garcia Juan Antonio 
Rivera Hernandez Luis Angel 
Salvador Casimiro Diego

*/
import java.util.Scanner;

public class pruebaExcepcionAritmetica{
    // Declaracion de los atributos, se ponen en privado para que el usuario no pueda acceder a ellos directamente 
    private float num1;
    private float num2;
    // Declaracion de los dos constructores, tanto el caso base, asi como el constructor lleno
    public pruebaExcepcionAritmetica(){
        
    }
    public pruebaExcepcionAritmetica(float num1, float num2) {
        this.num1 = num1;
        this.num2 = num2;
    }
    // Metodos setters
    public void setnum1(float num1){
        this.num1=num1;
    }
    
    public void setnum2(float num2){
        this.num2=num2;
    }
    // Metodos getters 
    public float getnum1(){
        return num1;
    } 
    
    public float getnum2(){
        return num2;
    }
    
    // Creacion del metodo dividir, aqui es donde vamos a hacer uso de try-catch, el cual nos va a permitir atrapar la excepcion ArithmeticException
    public void dividir() {
        try {
            // Primero vamos a hacer una verificacion, ya que si dividimos entre valores flotantes, el denominador no lanzara la ArithmeticException, ya que, como empleamos atributos tipo float, si hacemos una division normalmente entre cero java la va a detectar como ifinito y no como excepcion 
            if (num2==0){
            // Empleamos throw para lanzar de forma "manual" la excepcion, ya que como mencionamos, java de forma automatica no lo va a detectar
                throw new ArithmeticException("Error: division entre 0");
            }
            float resultado = num1 / num2;
            System.out.println("El resultado de esta division es: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("Error: No se puede realizar una division entre cero.");
        }
    }
    // Creacion de la clase main 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Inicializamos las variables que vamos a usar para almacenar los numeros que ingrese el usuario
        int num1 = 0;
        int num2 = 0;

        System.out.println("Programa para probar las excepciones");

        // Ciclo para el primer número (captura InputMismatchException) con ayuda de un while, que obliga al usuario a ingresar correctamente la informacion 
        while (true) {
            try {
                System.out.println("Ingresa un numero (el numerador) para hacer la division: ");
                num1 = scanner.nextInt(); 
                break; // Si se ingresa bien, rompe el ciclo y continúa
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: No ingrese letras, por favor ingrese un número válido.");
                scanner.next(); // Limpia el valor incorrecto del buffer
            }
        }

        // Ciclo para el segundo número (captura InputMismatchException) con ayuda de un while, que obliga al usuario a ingresar correctamente la informacion
        while (true) {
            try {
                System.out.println("Ahora ingresa el otro numero (el denominador) para hacer tu division: ");
                num2 = scanner.nextInt(); 
                break; // Si se ingresa bien, rompe el ciclo
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: No ingrese letras, por favor ingrese un número válido.");
                scanner.next(); // Limpia el valor incorrecto del buffer
            }
        }

        // Creamos el objeto y operamos (aquí actúa la ArithmeticException en el método)
        pruebaExcepcionAritmetica operacion = new pruebaExcepcionAritmetica(num1, num2);
        operacion.dividir();

        scanner.close();
    }
}
