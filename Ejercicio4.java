/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package EjerciciosPilas;
import java.util.Stack;
import java.util.Scanner;

/**
 *
 * @author migue
 */

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir una palabra 
        System.out.print("Ingrese una palabra: ");
        String palabra = sc.nextLine();

        // Crear una pila para los caracteres
        Stack<Character> pila = new Stack<>();

        // Apilar cada letra de la palabra
        for (char c : palabra.toCharArray()) pila.push(c);

        // Mostrar la palabra invertida
        System.out.print("Invertida: ");
        while (!pila.isEmpty()) System.out.print(pila.pop());
    }
}
