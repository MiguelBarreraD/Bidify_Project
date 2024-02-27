package edu.eci.arsw.bidify.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Monitor {
    private boolean suspendido = false;
    public Monitor(){
    }
    // Método para suspender los hilos
    public synchronized void suspenderHilos() {
        suspendido = true;
    }
    
    // Método para reanudar los hilos
    public synchronized void reanudarHilos() {
        suspendido = false;
        notifyAll(); // Notificar a todos los hilos que pueden continuar
    }
    
    // Método para que los hilos esperen si están suspendidos
    public synchronized void esperarSiSuspendido() throws InterruptedException {
        while (suspendido) {
            wait(); // Esperar hasta que se reanuden los hilos
        }
    }
}