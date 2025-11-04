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

public class Ejercicio3MLA {
    public static void main(String[] args) {
        
        //Crea una lista vacia 
        Stack<Integer> pila = new Stack<>();

        //Se verifica si esta vacia 
        System.out.println("Esta vacia la pila? " + pila.isEmpty());

        //Agrega un alemento 
        pila.push(1);

        //Vuelve a verificar si esta vacia 
        System.out.println("Esta vacia la pila? " + pila.isEmpty());
    }
}


