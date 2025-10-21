package Ejercicio1;

import java.util.Random;
import java.util.Scanner;

class Nodo {
    int dato;
    Nodo siguiente;

    Nodo(int valor) {
        dato = valor;
        siguiente = null;
    }
}

class ListaEnlazada {
    Nodo inicio, fin;

    ListaEnlazada() {
        inicio = fin = null;
    }

    void agregar(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (inicio == null) {
            inicio = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    void mostrar() {
        if (inicio == null) {
            System.out.println("La lista está vacía");
            return;
        }

        Nodo actual = inicio;
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    void eliminarMayores(int limite) {
        if (inicio == null) {
            System.out.println("La lista está vacía");
            return;
        }

        while (inicio != null && inicio.dato > limite) {
            inicio = inicio.siguiente;
        }

        if (inicio == null) {
            fin = null;
            System.out.println("Todos los nodos fueron eliminados.");
            return;
        }

        Nodo actual = inicio;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato > limite) {
                actual.siguiente = actual.siguiente.siguiente;
                if (actual.siguiente == null) {
                    fin = actual;
                }
            } else {
                actual = actual.siguiente;
            }
        }
    }

    void generarAleatorios(int cantidad, int maximo) {
        Random r = new Random();
        for (int i = 0; i < cantidad; i++) {
            agregar(r.nextInt(maximo) + 1);
        }
    }
}

public class Actividad01 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ListaEnlazada lista = new ListaEnlazada();

        System.out.println("=== MANIPULACIÓN DE LISTA ENLAZADA ===");

        System.out.print("¿Cuántos números deseas generar? ");
        int cantidad = entrada.nextInt();

        System.out.print("¿Valor máximo para los números aleatorios? ");
        int maximo = entrada.nextInt();

        lista.generarAleatorios(cantidad, maximo);

        System.out.println("\n--- Lista Original ---");
        lista.mostrar();

        System.out.print("\nIngresa el valor límite para eliminar nodos: ");
        int limite = entrada.nextInt();

        lista.eliminarMayores(limite);

        System.out.println("\n--- Lista después de eliminar nodos > " + limite + " ---");
        lista.mostrar();

        entrada.close();
        System.out.println("\n=== PROGRAMA TERMINADO ===");
    }
}
