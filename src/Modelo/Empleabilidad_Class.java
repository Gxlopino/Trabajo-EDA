/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import TDA.Cola;

/**
 * Clase que representa la dependencia de Alumnos y Egresados.
 * Administra su propia cola de expedientes y permite derivar, agregar, eliminar y buscar expedientes.
 */
public class Empleabilidad_Class {
    
    // Cola de expedientes asignados a la dependencia de Alumnos/Egresados
    /**
     * Cola de expedientes que se encuentran actualmente en la dependencia de Alumnos/Egresados.
     * Esta cola se utiliza para almacenar y gestionar los expedientes que están siendo procesados.
     */
    public static Cola<Expediente> ExpedientesEmpleabilidad;
    static  {
        ExpedientesEmpleabilidad = new Cola<>();
    }

    /**
     * Agrega un expediente a la cola de Alumnos/Egresados.
     * @param item Expediente a agregar
     */
    public static void agregar(Expediente item) {
        // Agrega el expediente a la cola
        ExpedientesEmpleabilidad.encolar(item);
    }

    /**
     * Elimina el primer expediente de la cola de Alumnos/Egresados.
     */
    public static void desencolarExpediente() {
        // Elimina el primer expediente de la cola
        ExpedientesEmpleabilidad.desencolar();
    }
    public static boolean esVacio(){
       return ExpedientesEmpleabilidad.esVacia();
    }
    /**
     * Deriva un expediente desde Alumnos/Egresados hacia otra dependencia o al administrador.
     * @param destino Nombre de la dependencia destino ("Administrador", "Admision", "Matricula")
     * @param expediente Expediente a derivar
     */
    public static void derivarA(String destino, Expediente expediente) {
        // Utiliza un switch para determinar la dependencia destino
        switch (destino) {
            case "Administrador":
                // Deriva el expediente al administrador
                expediente.Dependencia = "Administrador";
                break;
            case "Bienestar":
                // Deriva el expediente a Admision
                Modelo.Bienestar_Class.agregar(expediente);
                expediente.Dependencia = "Bienestar";
                break;
            case "Dep. Médico":
                // Deriva el expediente a Matricula
                Modelo.Dep_medico_Class.agregar(expediente);
                expediente.Dependencia = "Dep. Médico";
                break;
            default:
                // No realiza ninguna acción si el destino no es válido
                break;
        }
        // Elimina el expediente de la cola de Alumnos/Egresados
        EliminarDeCola(expediente);
    }

    /**
     * Elimina un expediente específico de la cola de Alumnos/Egresados.
     * @param expediente Expediente a eliminar
     */
    public static void EliminarDeCola(Expediente expediente) {
        // Crea una cola temporal para almacenar los expedientes que no se van a eliminar
        Cola<Expediente> temp = new Cola<>();
        // Recorre la cola de Alumnos/Egresados
        while (!ExpedientesEmpleabilidad.esVacia()) {
            // Obtiene el primer expediente de la cola
            Expediente tmp = ExpedientesEmpleabilidad.desencolar();
            // Verifica si el expediente es el que se va a eliminar
            if (tmp.getId() != expediente.getId()) {
                // Si no es el expediente a eliminar, lo agrega a la cola temporal
                temp.encolar(tmp);
            }
        }
        // Recorre la cola temporal y agrega los expedientes de vuelta a la cola de Alumnos/Egresados
        while (!temp.esVacia()) {
            ExpedientesEmpleabilidad.encolar(temp.desencolar());
        }
    }

    /**
     * Muestra todos los expedientes en la cola de Alumnos/Egresados.
     */
    public static void mostrar() {
        // Crea una cola temporal para almacenar los expedientes
        Cola<Expediente> temp = new Cola<>();
        // Recorre la cola de Alumnos/Egresados
        while (!ExpedientesEmpleabilidad.esVacia()) {
            // Obtiene el primer expediente de la cola
            Expediente tmp = ExpedientesEmpleabilidad.desencolar();
            // Muestra el expediente
            tmp.mostrar();
            // Agrega el expediente a la cola temporal
            temp.encolar(tmp);
        }
        // Recorre la cola temporal y agrega los expedientes de vuelta a la cola de Alumnos/Egresados
        while (!temp.esVacia()) {
            ExpedientesEmpleabilidad.encolar(temp.desencolar());
        }
    } 
    
    /**
     * Busca un expediente en la cola de Alumnos/Egresados por el DNI del interesado.
     * @param dni DNI a buscar
     * @return El expediente encontrado, o null si no existe
     */
    public static Expediente buscarPorDNI(String dni){
        // Crea una cola temporal para almacenar los expedientes
        Cola<Expediente> temp = new Cola<>();
        // Crea un expediente auxiliar para almacenar el resultado
        Expediente EAux= new Expediente();
        // Crea un interesado auxiliar para almacenar el interesado del expediente
        Interesado IAux= new Interesado();
        // Recorre la cola de Alumnos/Egresados
        while (!ExpedientesEmpleabilidad.esVacia()) {
            // Obtiene el primer expediente de la cola
            Expediente tmp = ExpedientesEmpleabilidad.desencolar();
            // Obtiene el interesado del expediente
            IAux=tmp.getInteresado();
            // Obtiene el DNI del interesado
            String Saux=IAux.getDni();
            if(Saux.equals(dni)){
                EAux=tmp;   
            }
            temp.encolar(tmp);
        }
        while (!temp.esVacia()) {
            ExpedientesEmpleabilidad.encolar(temp.desencolar());
        }
        return EAux;
    }
        
}
