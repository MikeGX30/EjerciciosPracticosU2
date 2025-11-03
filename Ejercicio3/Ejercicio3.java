package Ejercicio3;

import java.util.Scanner;

class Termino {
    double coef;
    int exp;
    Termino sig;

    Termino(double c, int e) {
        coef = c;
        exp = e;
        sig = null;
    }
}

class Polinomio {
    private Termino inicio;

    Polinomio() {
        inicio = null;
    }

    void agregar(double c, int e) {
        Termino nuevo = new Termino(c, e);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Termino actual = inicio;
            while (actual.sig != null) actual = actual.sig;
            actual.sig = nuevo;
        }
    }

    void mostrar() {
        if (inicio == null) {
            System.out.println("Polinomio vacío");
            return;
        }

        Termino actual = inicio;
        boolean primero = true;
        System.out.print("P(x) = ");
        while (actual != null) {
            if (actual.coef >= 0 && !primero) System.out.print(" + ");
            else if (actual.coef < 0) System.out.print(" - ");

            double valor = Math.abs(actual.coef);
            if (actual.exp == 0) System.out.print(valor);
            else if (actual.exp == 1) System.out.print((valor == 1 ? "" : valor) + "x");
            else System.out.print((valor == 1 ? "" : valor) + "x^" + actual.exp);

            actual = actual.sig;
            primero = false;
        }
        System.out.println();
    }

    double evaluar(double x) {
        double resultado = 0;
        Termino actual = inicio;
        while (actual != null) {
            resultado += actual.coef * Math.pow(x, actual.exp);
            actual = actual.sig;
        }
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

    boolean existeExp(int e) {
        Termino actual = inicio;
        while (actual != null) {
            if (actual.exp == e) return true;
            actual = actual.sig;
        }
        return false;
    }
}

public class Actividad03 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Polinomio p = new Polinomio();

        System.out.println("=== POLINOMIO ===");
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
                        System.out.println("Exponente no puede ser negativo.");
                        continue;
                    }
                    if (p.existeExp(e)) {
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
