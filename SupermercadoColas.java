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

public class SupermercadoColas {

    public static void main(String[] args) {
        
        // Cola para los carritos disponibles: 25 carritos
        Queue<Integer> carritos = new LinkedList<>();
        for (int i = 1; i <= 25; i++) {
            carritos.add(i);
        }

        // Tres cajas con sus colas de clientes
        Queue<Integer> caja1 = new LinkedList<>();
        Queue<Integer> caja2 = new LinkedList<>();
        Queue<Integer> caja3 = new LinkedList<>();

        int totalClientes = 40; //---------------------------------------------------------

        for (int cliente = 1; cliente <= totalClientes; cliente++) {
            System.out.println("\nLlega el cliente " + cliente + " al supermercado:");

            // Si no hay carritos disponibles, el cliente espera
            if (carritos.isEmpty()) {
                System.out.println("No hay carritos disponibles. El cliente " + cliente + " tiene que esperar");
                continue;
            }

            // Cliente toma un carrito
            int carritoTomado = carritos.remove();
            System.out.println("Cliente " + cliente + " toma el carrito " + carritoTomado);

            // Elige la caja con menos clientes
            int tam1 = caja1.size();
            int tam2 = caja2.size();
            int tam3 = caja3.size();

            if (tam1 <= tam2 && tam1 <= tam3) {
                caja1.add(cliente);
                System.out.println("Cliente " + cliente + " se forma en la Caja 1");
            } else if (tam2 <= tam1 && tam2 <= tam3) {
                caja2.add(cliente);
                System.out.println("Cliente " + cliente + " se forma en la Caja 2");
            } else {
                caja3.add(cliente);
                System.out.println("Cliente " + cliente + " se forma en la Caja 3");
            }

            // Cada 3 clientes que llegan uno paga y devuelve el carrito
            if (cliente % 3 == 0) {
                liberarCarrito(carritos, caja1, caja2, caja3);
            }

            mostrarEstado(carritos, caja1, caja2, caja3);
        }
    }
    // Metodo para liberar un carrito osea cliente paga y se va
    public static void liberarCarrito(Queue<Integer> carritos, Queue<Integer> c1, Queue<Integer> c2, Queue<Integer> c3) {
        
        // Se libera un cliente de la primera caja que tenga alguien
        if (!c1.isEmpty()) {
            int cliente = c1.remove();
            carritos.add(1); 
            System.out.println(">> Cliente " + cliente + " termino en Caja 1 y devolvi el carrito.");
        } else if (!c2.isEmpty()) {
            int cliente = c2.remove();
            carritos.add(1);
            System.out.println(">> Cliente " + cliente + " termino en Caja 2 y devolvio el carrito.");
        } else if (!c3.isEmpty()) {
            int cliente = c3.remove();
            carritos.add(1);
            System.out.println(">> Cliente " + cliente + " termino en Caja 3 y devolvio el carrito.");
        }
    }

    // Metodo para mostrar el supermercado
    public static void mostrarEstado(Queue<Integer> carritos, Queue<Integer> c1, Queue<Integer> c2, Queue<Integer> c3) {
        System.out.println("------------------------------------------------");
        System.out.println("Carritos disponibles: " + carritos.size());
        System.out.println("Cola Caja 1: " + c1.size() + " clientes");
        System.out.println("Cola Caja 2: " + c2.size() + " clientes");
        System.out.println("Cola Caja 3: " + c3.size() + " clientes");
        System.out.println("------------------------------------------------");
    }
}
