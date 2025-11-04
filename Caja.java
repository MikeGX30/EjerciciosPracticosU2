/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioscola;

/**
 *
 * @author migue
 */
public class Caja {
    private int tiempoRestante; // tiempo que falta para terminar de atender

    public Caja() {
        this.tiempoRestante = 0;
    }

    public boolean estaLibre() {
        return tiempoRestante == 0;
    }

    public void atenderCliente(int tiempoAtencion) {
        this.tiempoRestante = tiempoAtencion;
    }

    public void pasarUnMinuto() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }
}

