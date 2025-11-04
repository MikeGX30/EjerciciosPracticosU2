/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EjerciciosPilas;
import java.util.Stack;
/**
 *
 * @author migue
 */

public class Ejercicio1 {
    public static void main(String[] args) {
        Stack<Integer> pila = new Stack<>();

        // Agregar elementos
        pila.push(5);
        pila.push(10);
        pila.push(15);
        pila.push(20);

        // Eliminar 2 elementos
        pila.pop();
        pila.pop();

        // Mostrar contenido 
        System.out.println("Contenido actual: " + pila);
    }
}
