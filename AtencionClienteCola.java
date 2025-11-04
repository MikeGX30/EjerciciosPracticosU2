/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicioscola;

/**
 *
 * @author migue
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class AtencionClienteCola{
    public static void main(String[] args) {
        int minutosTotales = 7 * 60; // 7 horas por minuto 60 = 420 min
        int clientesAtendidos = 0;
        int maxFila = 0;
        int sumaTamanioFila = 0;
        int tiempoMaxEspera = 0;
        int tiempoAperturaCuartaCaja = -1;
        boolean cuartaCajaAbierta = false;

        Queue<Cliente> fila = new LinkedList<>();
        Random rand = new Random();

        // Creamos 4 cajas la 4 si en necesario solamente 
        Caja[] cajas = {new Caja(), new Caja(), new Caja(), new Caja()};

        // Simulacion minuto a minuto
        for (int minuto = 1; minuto <= minutosTotales; minuto++) {
            // Llega un nuevo cliente cada minuto
            fila.add(new Cliente(minuto));

            // Abrir cuarta caja si hay mas de 20 clientes
            if (fila.size() > 20 && !cuartaCajaAbierta) {
                cuartaCajaAbierta = true;
                tiempoAperturaCuartaCaja = minuto;
                System.out.println("Cuarta caja abierta en el minuto: " + minuto);
            }

            // Atender en las cajas activas
            for (int i = 0; i < (cuartaCajaAbierta ? 4 : 3); i++) {
                Caja caja = cajas[i];

                // Si la caja esta libre y hay alguien en la fila
                if (caja.estaLibre() && !fila.isEmpty()) {
                    Cliente cliente = fila.poll();
                    int tiempoEspera = minuto - cliente.getTiempoLlegada();
                    tiempoMaxEspera = Math.max(tiempoMaxEspera, tiempoEspera);

                    int tiempoAtencion = 2 + rand.nextInt(4); 
                    caja.atenderCliente(tiempoAtencion);
                    clientesAtendidos++;
                }

                // Pasar el tiempo de atención de esa caja
                caja.pasarUnMinuto();
            }

            // Actualizar estadisticas
            sumaTamanioFila += fila.size();
            maxFila = Math.max(maxFila, fila.size());
        }

        // Calculos finales
        double promedioFila = (double) sumaTamanioFila / minutosTotales;

        System.out.println("\n RESULTADOS DE LA SIMULACION:");
        System.out.println("Total de clientes atendidos: " + clientesAtendidos);
        System.out.println("Tamaño medio de la fila: " + String.format("%.2f", promedioFila));
        System.out.println("Tamaño maximo de la fila: " + maxFila);
        System.out.println("Tiempo maximo de espera: " + tiempoMaxEspera + " min");
        if (cuartaCajaAbierta)
            System.out.println("Cuarta caja abierta en el minuto: " + tiempoAperturaCuartaCaja);
        else
            System.out.println("La cuarta caja nunca se abrio.");
    }
}
