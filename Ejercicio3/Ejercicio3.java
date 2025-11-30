package Ejercicio3;

import java.util.Scanner;

/**
 * @author GTID141 
 * @author 1224100689.mla@gmail.com
 * @author miguel Lozano
 */
////////////////////////////////////////////////////////////////////////
/**
 * Clase Termino:
 * Aqui basicamente guardamos un coeficiente, un exponente y la referencia
 * al siguiente termino, esto representa cada pedazo del polinomio.
 */
class Termino {
    double coef; // coeficiente del termino numero que multiplica a x
    int exp;     // exponente del termino
    Termino sig; // referencia al siguiente termino

    Termino(double c, int e) {
        coef = c;
        exp = e;
        sig = null;
    }
}

/**
 * Clase Polinomio:
 * Aqui manejamos toda la lista de terminos, que al final forman el polinomio, se pueden agregar terminos, mostrarlos,
 * evaluarlo en un valor y hasta generar una tabla de valores, todo va ordenado como lista enlazada sencilla.
 */
class Polinomio {
    private Termino inicio;

    Polinomio() {
        inicio = null;
    }

    /**
     * Metodo agregar:
     * Mete un nuevo termino al final del polinomio. No hay orden automatico, simplemente lo encadena al ultimo.
     */
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

    /**
     * Metodo mostrar:
     * Imprime el polinomio con formato bonito, si esta vacio, pues avisamos.
     */
    void mostrar() {
        if (inicio == null) {
            System.out.println("Polinomio vacio");
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

    /**
     * Metodo evaluar:
     * Calcula el valor del polinomio sustituyendo x por el numero que metemos, recorre cada termino y hace coef * x^exponente y todo eso se suma.
     */
    double evaluar(double x) {
        double resultado = 0;
        Termino actual = inicio;
        while (actual != null) {
            resultado += actual.coef * Math.pow(x, actual.exp);
            actual = actual.sig;
        }
        return resultado;
    }

    /**
     * Metodo tabla:
     * Genera una tabla para ver como cambia el polinomio desde x=0 hasta x=5
     * avanzando de 0.5 en 0.5. Basicamente es una mini tabla de valores.
     */
    void tabla() {
        System.out.println("\n=== TABLA DE EVALUACION ===");
        System.out.println("  x   |   P(x)");
        System.out.println("---------------");
        for (double x = 0; x <= 5; x += 0.5) {
            System.out.printf("%4.1f  | %8.3f%n", x, evaluar(x));
        }
    }

    /**
     * Metodo existeExp:
     * Revisa si ya hay un termino con un exponente especifico, esto sirve para evitar duplicados.
     */
    boolean existeExp(int e) {
        Termino actual = inicio;
        while (actual != null) {
            if (actual.exp == e) return true;
            actual = actual.sig;
        }
        return false;
    }
}

/**
 * Clase principal:
 * Aqui es donde pedimos datos al usuario, construimos el polinomio y luego mostramos todo, tambien se puede evaluar en otro valor si se quiere.
 */
public class Actividad03 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Polinomio p = new Polinomio();

        System.out.println("=== POLINOMIO ===");
        System.out.println("Ingresa coeficiente y exponente (ej. 3 4), escribe 'fin' para terminar:");

        while (true) {
            System.out.print("Termino: ");

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
                        System.out.println("Ya existe un termino con exponente " + e);
                        continue;
                    }

                    p.agregar(c, e);
                    System.out.println("Anadido: " + c + "x^" + e);
                } else {
                    System.out.println("Exponente invalido.");
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

        System.out.println("\nEvaluar en otro valor? (s/n)");
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
