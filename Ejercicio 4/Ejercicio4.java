package Ejercicio4;

import java.util.Scanner;

class Nodo {
    double coef;
    int exp;
    Nodo sig;

    Nodo(double c, int e) {
        coef = c;
        exp = e;
        sig = null;
    }
}

class Polinomio {
    Nodo ultimo;

    Polinomio() {
        ultimo = null;
    }

    void agregar(double c, int e) {
        Nodo nuevo = new Nodo(c, e);
        if (ultimo == null) {
            ultimo = nuevo;
            nuevo.sig = nuevo;
        } else {
            nuevo.sig = ultimo.sig;
            ultimo.sig = nuevo;
            ultimo = nuevo;
        }
    }

    void mostrar() {
        if (ultimo == null) {
            System.out.println("Polinomio vacío");
            return;
        }

        Nodo actual = ultimo.sig;
        Nodo inicio = actual;
        boolean primero = true;

        System.out.print("P(x) = ");
        do {
            if (actual.coef >= 0 && !primero) System.out.print(" + ");
            else if (actual.coef < 0) System.out.print(" - ");

            double valor = Math.abs(actual.coef);
            if (actual.exp == 0) System.out.print(valor);
            else if (actual.exp == 1) System.out.print((valor == 1 ? "" : valor) + "x");
            else System.out.print((valor == 1 ? "" : valor) + "x^" + actual.exp);

            actual = actual.sig;
            primero = false;
        } while (actual != inicio);
        System.out.println();
    }

    double evaluar(double x) {
        if (ultimo == null) return 0;

        double resultado = 0;
        Nodo actual = ultimo.sig;
        Nodo inicio = actual;

        do {
            resultado += actual.coef * Math.pow(x, actual.exp);
            actual = actual.sig;
        } while (actual != inicio);

        return resultado;
    }

    void tabla() {
        System.out.println("\n=== TABLA DE EVALUACIÓN ===");
        System.out.println("  x   |   P(x)");
        System.out.println("---------------");
        for (double x = 0; x <= 5; x += 0.5) {
            System.out.printf("%4.1f  | %8.3f%n", x, evaluar(x));
        }
    }

    boolean existe(int e) {
        if (ultimo == null) return false;

        Nodo actual = ultimo.sig;
        Nodo inicio = actual;

        do {
            if (actual.exp == e) return true;
            actual = actual.sig;
        } while (actual != inicio);

        return false;
    }

    void recorrido() {
        if (ultimo == null) {
            System.out.println("Lista vacía");
            return;
        }

        System.out.println("\n=== RECORRIDO CIRCULAR ===");
        Nodo actual = ultimo.sig;
        Nodo inicio = actual;
        int i = 1;

        do {
            System.out.println("Nodo " + i + ": " + actual.coef + "x^" + actual.exp);
            System.out.println("  Apunta a: " + actual.sig.coef + "x^" + actual.sig.exp);
            actual = actual.sig;
            i++;
        } while (actual != inicio);

        System.out.println("Ultimo nodo apunta al primero: " + ultimo.coef + "x^" + ultimo.exp +
                           " -> " + ultimo.sig.coef + "x^" + ultimo.sig.exp);
    }
}

public class Actividad04 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Polinomio p = new Polinomio();

        System.out.println("=== POLINOMIO CIRCULAR ===");
        System.out.println("Ingresa coeficiente y exponente (ej. 3 4), escribe 'fin' para terminar:");

        while (true) {
            System.out.print("Término: ");
            if (entrada.hasNext("fin")) {
                entrada.next();
                break;
            }

            if (entrada.hasNextDouble()) {
                double c = entrada.nextDouble();
                if (entrada.hasNextInt()) {
                    int e = entrada.nextInt();
                    if (e < 0) {
                        System.out.println("Exponente no válido.");
                        continue;
                    }
                    if (p.existe(e)) {
                        System.out.println("Ya existe un término con exponente " + e);
                        continue;
                    }
                    p.agregar(c, e);
                    System.out.println("Añadido: " + c + "x^" + e);
                } else {
                    System.out.println("Exponente inválido.");
                    entrada.next();
                }
            } else {
                System.out.println("Formato incorrecto.");
                entrada.next();
            }
        }

        System.out.println("\n--- POLINOMIO ---");
        p.mostrar();

        p.recorrido();
        p.tabla();

        System.out.println("\n¿Evaluar en otro valor? (s/n)");
        entrada.nextLine();
        String r = entrada.nextLine();
        if (r.equalsIgnoreCase("s")) {
            System.out.print("Valor de x: ");
            double x = entrada.nextDouble();
            System.out.printf("P(%.2f) = %.3f%n", x, p.evaluar(x));
        }

        entrada.close();
        System.out.println("\n=== FIN DEL PROGRAMA ===");
    }
}
