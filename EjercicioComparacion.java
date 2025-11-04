/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicioscola;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author migue
 */

public class EjercicioComparacion{

    // Metodo que compara dos colas
    public static boolean compararColas(Queue<Integer> cola1, Queue<Integer> cola2) {
        
        // Si los tamaños son diferentes ya no son iguales
        if (cola1.size() != cola2.size()) {
            return false;
        }

        // Colas auxiliares para no perder los datos og
        Queue<Integer> aux1 = new LinkedList<>();
        Queue<Integer> aux2 = new LinkedList<>();

        boolean sonIguales = true;

        // Recorremos ambas colas al mismo tiempo
        while (!cola1.isEmpty()) {
            int dato1 = cola1.remove();
            int dato2 = cola2.remove();

            // Guardamos los datos en colas auxiliares
            aux1.add(dato1);
            aux2.add(dato2);

            // Si algun dato no coincide ya no son iguales
            if (dato1 != dato2) {
                sonIguales = false;
            }
        }

        // Ponemos las colas originales
        while (!aux1.isEmpty()) {
            cola1.add(aux1.remove());
            cola2.add(aux2.remove());
        }

        return sonIguales;
    }

    public static void main(String[] args) {
        Queue<Integer> colaA = new LinkedList<>();
        Queue<Integer> colaB = new LinkedList<>();

        // Agregamos elementos a las colas
        colaA.add(10);
        colaA.add(20);
        colaA.add(30);

        colaB.add(10);
        colaB.add(20);
        colaB.add(30);

        // Llamamos al metodo y mostramos el resultado
        boolean resultado = compararColas(colaA, colaB);

        System.out.println("Las colas son iguales?: " + resultado);
    }
}
