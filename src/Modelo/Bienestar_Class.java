/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import TDA.Cola;

/**
 * Clase que representa la dependencia de Admisión.
 * Administra su propia cola de expedientes y permite derivar, agregar, eliminar y buscar expedientes.
 */
public class Bienestar_Class {
    
    // Cola de expedientes asignados a la dependencia de Admisión
    public static Cola<Expediente> ExpedientesBienestar;
    
    /**
     * Inicializa la cola de expedientes de Admisión.
     */
    static  {
        ExpedientesBienestar = new Cola<>();
    }

    /**
     * Agrega un expediente a la cola de Admisión.
     * @param item Expediente a agregar
     */
    public static void agregar(Expediente item) {
        ExpedientesBienestar.encolar(item);
    }

    /**
     * Elimina el primer expediente de la cola de Admisión.
     */
    public static void desencolarExpediente() {
        ExpedientesBienestar.desencolar();

    }
    /**
     * Verifica si es vacia
     */
    public static boolean esVacio(){
       return ExpedientesBienestar.esVacia();
    }
    /**
     * Deriva un expediente desde Admisión hacia otra dependencia o al administrador.
     * @param destino Nombre de la dependencia destino ("Administrador", "Alumnos_Egresados", "Matricula")
     * @param expediente Expediente a derivar
     */
    public static void derivarA(String destino, Expediente expediente) {
        // Utiliza un switch para determinar la dependencia destino
        switch (destino) {
            case "Administrador":
                // Deriva el expediente al administrador
                expediente.Dependencia = "Administrador";
                break;
            case "Empleabilidad":
                // Deriva el expediente a empleabilidad
                Modelo.Empleabilidad_Class.agregar(expediente);
                expediente.Dependencia = "Empleabilidad";
                break;
            case "Dep. Médico":
                // Deriva el expediente a dep medico
                Modelo.Dep_medico_Class.agregar(expediente);
                expediente.Dependencia = "Dep. Médico";
                break;
            default:
                // No realiza ninguna acción si el destino no es válido
                break;
        }
        // Elimina el expediente de la cola de Admisión después de derivarlo
        EliminarDeCola(expediente);
    }

    /**
     * Elimina un expediente específico de la cola de Admisión.
     * @param expediente Expediente a eliminar
     */
    public static void EliminarDeCola(Expediente expediente) {
        // Crea una cola temporal para almacenar los expedientes que no se eliminarán
        Cola<Expediente> temp = new Cola<>();
        // Recorre la cola de Admisión y elimina el expediente especificado
        while (!ExpedientesBienestar.esVacia()) {
            Expediente tmp = ExpedientesBienestar.desencolar();
            if (tmp.getId() != expediente.getId()) {
                // Agrega el expediente a la cola temporal si no es el que se busca eliminar
                temp.encolar(tmp);
            }
        }
        // Vuelve a agregar los expedientes de la cola temporal a la cola de Admisión
        while (!temp.esVacia()) {
            ExpedientesBienestar.encolar(temp.desencolar());
        }
    }

    /**
     * Muestra todos los expedientes en la cola de Admisión.
     */
    public static void mostrar() {
        // Crea una cola temporal para almacenar los expedientes mientras se muestran
        Cola<Expediente> temp = new Cola<>();

        // Recorre la cola de Admisión y muestra cada expediente
        while (!ExpedientesBienestar.esVacia()) {
            Expediente tmp = ExpedientesBienestar.desencolar();
            tmp.mostrar();
            // Agrega el expediente a la cola temporal después de mostrarlo
            temp.encolar(tmp);
        }

        // Vuelve a agregar los expedientes de la cola temporal a la cola de Admisión
        while (!temp.esVacia()) {
            ExpedientesBienestar.encolar(temp.desencolar());
        }
    } 
    
    /**
     * Busca un expediente en la cola de Admisión por el DNI del interesado.
     * @param dni DNI a buscar
     * @return El expediente encontrado, o null si no existe
     */
    public static Expediente buscarPorDNI(String dni) {
        // Crea una cola temporal para almacenar los expedientes mientras se buscan
        Cola<Expediente> temp = new Cola<>();
        Expediente EAux= new Expediente();
        Interesado IAux= new Interesado();
        // Recorre la cola de Admisión y busca el expediente con el DNI especificado
        while (!ExpedientesBienestar.esVacia()) {
            Expediente tmp = ExpedientesBienestar.desencolar();
            IAux=tmp.getInteresado();
            String Saux=IAux.getDni();
            if(Saux.equals(dni)){
                EAux=tmp;   
            }
            temp.encolar(tmp);
        }
        while (!temp.esVacia()) {
            ExpedientesBienestar.encolar(temp.desencolar());
        }
        return EAux;
    }
        
        
}
