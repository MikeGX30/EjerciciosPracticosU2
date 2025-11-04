/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EjerciciosPilas;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author migue
 */
public class Ejercicio2MLA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> pila = new Stack<>();
        String nombre;

        // Leer nombres hasta que el usuario escriba "FIN"
        while (true) {
            System.out.print("Ingrese un nombre (FIN para salir): ");
            nombre = sc.nextLine();

            // Si el usuario escribe "FIN", termina el ciclo
            if (nombre.equalsIgnoreCase("FIN")) break;

            //Agregar nombre a la pila
            pila.push(nombre);
        }

        //Se muestran en orden inverso los nombres
        System.out.println("Nombres en orden inverso:");
        while (!pila.isEmpty()) {
            System.out.println(pila.pop());
        }
    }
}