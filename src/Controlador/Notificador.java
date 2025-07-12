package Controlador;

import Modelo.Administrador;
import Modelo.Expediente;
import Modelo.Interesado;
import TDA.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 * Gestiona las notificaciones automáticas sobre el estado de los expedientes.
 */
public class Notificador {

    private final Timer timer;

    /**
     * Constructor que inicializa el temporizador.
     */
    public Notificador() {
        this.timer = new Timer();
    }

    /**
     * Inicia la tarea de notificación para que se ejecute cada 10 minutos.
     */
    public void iniciar() {
        // Ejecuta la tarea cada 10 minutos (600,000 milisegundos)
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                MostrarExpedientesSd();
            }
        }, 0, 600000);
        //10000
    }

    /**
     * Cuenta y notifica expedientes en todas las colas (principal, admisión,
     * alumnos/egresados, matrícula).
     */
    private void MostrarExpedientesSd() {
        //mostrar todos los expedientes sin derivar
        String detalles = "";
        Cola<Expediente> Sd = Administrador.buscarPorEstado(1);
        while (!Sd.esVacia()) {
            Expediente expediente = Sd.desencolar();
            Interesado interesado = expediente.getInteresado();
            detalles += "\nId: " + expediente.getId()
                    + "\t" + " | DNI: " + interesado.getDni()
                    + "\t" + " | Prioridad: " + expediente.getPrioridad()
                    + "\t" + " | Estado: Sin derivar";
        }
        if (detalles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay expedientes pendientes.", "Notificación de Expedientes sin derivar", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, detalles, "Notificación de Expedientes sin derivar", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Detiene el temporizador de notificaciones.
     */
    public void detener() {
        timer.cancel();
    }

}
