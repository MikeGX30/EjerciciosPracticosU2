package Ejercicio5;

import java.util.Scanner;

class Nodo {
    char valor;
    Nodo antes;
    Nodo despues;

    Nodo(char v) {
        valor = v;
        antes = null;
        despues = null;
    }
}

class Lista {
    Nodo inicio, fin;

    Lista() {
        inicio = fin = null;
    }

    void agregar(char v) {
        Nodo nuevo = new Nodo(v);
        if (inicio == null) {
            inicio = fin = nuevo;
        } else {
            fin.despues = nuevo;
            nuevo.antes = fin;
            fin = nuevo;
        }
    }

    void desdeTexto(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c != ' ') agregar(c);
        }
    }

    void mostrarAdelante() {
        if (inicio == null) {
            System.out.println("Lista vacía");
            return;
        }

        Nodo actual = inicio;
        System.out.print("Adelante: ");
        while (actual != null) {
            System.out.print(actual.valor + " ");
            actual = actual.despues;
        }
        System.out.println();
    }

    void mostrarAtras() {
        if (fin == null) {
            System.out.println("Lista vacía");
            return;
        }

        Nodo actual = fin;
        System.out.print("Atrás: ");
        while (actual != null) {
            System.out.print(actual.valor + " ");
            actual = actual.antes;
        }
        System.out.println();
    }

    void ordenarBurbuja() {
        if (inicio == null || inicio.despues == null) return;

        boolean cambio;
        do {
            cambio = false;
            Nodo actual = inicio;
            while (actual.despues != null) {
                if (actual.valor > actual.despues.valor) {
                    char temp = actual.valor;
                    actual.valor = actual.despues.valor;
                    actual.despues.valor = temp;
                    cambio = true;
                }
                actual = actual.despues;
            }
        } while (cambio);
    }

    void ordenarInsercion() {
        if (inicio == null || inicio.despues == null) return;

        Nodo actual = inicio.despues;
        while (actual != null) {
            char valor = actual.valor;
            Nodo anterior = actual.antes;

            while (anterior != null && anterior.valor > valor) {
                anterior.despues.valor = anterior.valor;
                anterior = anterior.antes;
            }

            if (anterior == null) {
                inicio.valor = valor;
            } else {
                anterior.despues.valor = valor;
            }

            actual = actual.despues;
        }
    }

    int contar() {
        int total = 0;
        Nodo actual = inicio;
        while (actual != null) {
            total++;
            actual = actual.despues;
        }
        return total;
    }

    void mostrarReferencias() {
        if (inicio == null) {
            System.out.println("Lista vacía");
            return;
        }

        Nodo actual = inicio;
        System.out.println("\n=== REFERENCIAS ===");
        while (actual != null) {
            String antes = (actual.antes == null) ? "null" : String.valueOf(actual.antes.valor);
            String despues = (actual.despues == null) ? "null" : String.valueOf(actual.despues.valor);
            System.out.println("Nodo: " + actual.valor + " | Antes: " + antes + " | Después: " + despues);
            actual = actual.despues;
        }
    }
}

public class Actividad05 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Lista lista = new Lista();

        System.out.println("=== LISTA DOBLE DE CARACTERES ===");
        System.out.print("Ingresa una cadena: ");
        String texto = entrada.nextLine();

        lista.desdeTexto(texto);

        System.out.println("\n--- ORIGINAL ---");
        lista.mostrarAdelante();
        lista.mostrarAtras();
        System.out.println("Total caracteres: " + lista.contar());

        lista.mostrarReferencias();

        System.out.println("\n--- ORDENANDO ---");
        lista.ordenarBurbuja();

        System.out.println("\n--- ORDENADA ---");
        lista.mostrarAdelante();
        lista.mostrarAtras();

        lista.mostrarReferencias();

        entrada.close();
        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}
