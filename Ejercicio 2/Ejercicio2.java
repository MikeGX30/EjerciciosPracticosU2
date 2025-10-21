package Ejercicio2;

import java.io.*;
import java.util.Scanner;

class Nodo {
    String palabra;
    Nodo siguiente;

    Nodo(String texto) {
        palabra = texto;
        siguiente = null;
    }
}

class Lista {
    Nodo inicio, fin;

    Lista() {
        inicio = fin = null;
    }

    void agregar(String texto) {
        Nodo nuevo = new Nodo(texto);
        if (inicio == null) {
            inicio = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    void mostrar() {
        if (inicio == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        Nodo actual = inicio;
        int i = 1;
        System.out.println("=== LISTA DE PALABRAS ===");
        while (actual != null) {
            System.out.println(i + ". " + actual.palabra);
            actual = actual.siguiente;
            i++;
        }
        System.out.println("=========================");
    }

    boolean eliminar(String texto) {
        if (inicio == null) return false;

        if (inicio.palabra.equals(texto)) {
            inicio = inicio.siguiente;
            if (inicio == null) fin = null;
            return true;
        }

        Nodo actual = inicio;
        while (actual.siguiente != null) {
            if (actual.siguiente.palabra.equals(texto)) {
                actual.siguiente = actual.siguiente.siguiente;
                if (actual.siguiente == null) fin = actual;
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }

    boolean buscar(String texto) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.palabra.equals(texto)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    void leerArchivo(String archivo) {
        try (Scanner lector = new Scanner(new File(archivo))) {
            System.out.println("Leyendo desde: " + archivo);
            while (lector.hasNext()) {
                String palabra = lector.next().replaceAll("[^a-zA-ZáéíóúñÑ]", "").toLowerCase();
                if (!palabra.isEmpty()) agregar(palabra);
            }
            System.out.println("Lectura completada.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo al guardar.");
        }
    }

    void guardarArchivo(String archivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            Nodo actual = inicio;
            while (actual != null) {
                escritor.write(actual.palabra);
                if (actual.siguiente != null) escritor.write(" ");
                actual = actual.siguiente;
            }
            System.out.println("Palabras guardadas en: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    int contar() {
        int total = 0;
        Nodo actual = inicio;
        while (actual != null) {
            total++;
            actual = actual.siguiente;
        }
        return total;
    }
}

public class Actividad02 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Lista lista = new Lista();
        final String ARCHIVO = "palabras.txt";

        System.out.println("=== LISTA DE PALABRAS DESDE ARCHIVO ===");
        lista.leerArchivo(ARCHIVO);

        int opcion;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Ver palabras");
            System.out.println("2. Agregar palabra");
            System.out.println("3. Eliminar palabra");
            System.out.println("4. Buscar palabra");
            System.out.println("5. Guardar y salir");
            System.out.print("Opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1 -> {
                    lista.mostrar();
                    System.out.println("Total: " + lista.contar());
                }
                case 2 -> {
                    System.out.print("Nueva palabra: ");
                    String nueva = entrada.nextLine().trim().toLowerCase();
                    if (!nueva.isEmpty()) {
                        lista.agregar(nueva);
                        System.out.println("Agregada: '" + nueva + "'");
                    } else {
                        System.out.println("No puede estar vacía.");
                    }
                }
                case 3 -> {
                    System.out.print("Palabra a eliminar: ");
                    String eliminar = entrada.nextLine().trim().toLowerCase();
                    if (lista.eliminar(eliminar)) {
                        System.out.println("Eliminada: '" + eliminar + "'");
                    } else {
                        System.out.println("No encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("Palabra a buscar: ");
                    String buscar = entrada.nextLine().trim().toLowerCase();
                    if (lista.buscar(buscar)) {
                        System.out.println("Sí está en la lista.");
                    } else {
                        System.out.println("No está en la lista.");
                    }
                }
                case 5 -> {
                    lista.guardarArchivo(ARCHIVO);
                    System.out.println("¡Hasta luego!");
                }
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 5);

        entrada.close();
    }
}
