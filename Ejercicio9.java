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

public class Ejercicio9 {
    public static void main(String[] args) {
        // Crear una lista 
        int[] lista = {1, 2, 3, 4};

        // Crear la pila para invertir los numeros
        Stack<Integer> pila = new Stack<>();

        // Apilar los numeros de la lista
        for (int n : lista) pila.push(n);

        // Mostrar los numeros en orden inverso
        System.out.print("Lista invertida: ");
        while (!pila.isEmpty()) System.out.print(pila.pop() + " ");
    }
}
