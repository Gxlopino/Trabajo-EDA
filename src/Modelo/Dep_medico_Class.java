/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import TDA.Cola;
import static Modelo.Bienestar_Class.ExpedientesBienestar;

/**
 * Clase que representa la dependencia de Matrícula.
 * Administra su propia cola de expedientes y permite derivar, agregar, eliminar y buscar expedientes.
 *
 * @author YUSTIN
 */
public class Dep_medico_Class {

    // Cola de expedientes asignados a la dependencia de Matrícula
    public static Cola<Expediente> ExpedientesDepMedico;

    /**
     * Inicializa la cola de expedientes de Matrícula.
     */
    static {
        ExpedientesDepMedico = new Cola<>();
    }

    /**
     * Agrega un expediente a la cola de Matrícula.
     * @param item Expediente a agregar
     */
    public static void agregar(Expediente item) {
        ExpedientesDepMedico.encolar(item);
    }

    /**
     * Elimina el primer expediente de la cola de Matrícula.
     */
    public static void desencolarExpediente() {
        ExpedientesDepMedico.desencolar();
    }
public static boolean esVacio(){
       return ExpedientesDepMedico.esVacia();
    }
    /**
     * Deriva un expediente desde Matrícula hacia otra dependencia o al administrador.
     * @param destino Nombre de la dependencia destino ("Administrador", "Admision", "Alumnos_Egresados")
     * @param expediente Expediente a derivar
     */
    public static void derivarA(String destino, Expediente expediente) {
        switch (destino) {
            case "Administrador":
                expediente.Dependencia = "Administrador";
                break;
            case "Bienestar":
                Modelo.Bienestar_Class.agregar(expediente);
                expediente.Dependencia = "Bienestar";
                break;
            case "Empleabilidad":
                Modelo.Empleabilidad_Class.agregar(expediente);
                expediente.Dependencia = "Empleabilidad";
                break;
            default:
                break;
        }
        EliminarDeCola(expediente);
    }

    /**
     * Elimina un expediente específico de la cola de Expedientes Matricula.
     * @param expediente Expediente a eliminar
     */
    public static void EliminarDeCola(Expediente expediente) {
        Cola<Expediente> temp = new Cola<>();
        while (!ExpedientesDepMedico.esVacia()) {
            Expediente tmp = ExpedientesDepMedico.desencolar();
            if (tmp.getId() != expediente.getId()) {
                temp.encolar(tmp);
            }
        }
        while (!temp.esVacia()) {
            ExpedientesDepMedico.encolar(temp.desencolar());
        }
    }

    /**
     * Muestra todos los expedientes en Matrícula.
     */
    public static void mostrar() {
        Cola<Expediente> temp = new Cola<>();

        while (!ExpedientesDepMedico.esVacia()) {
            Expediente tmp = ExpedientesDepMedico.desencolar();
            tmp.mostrar();
            temp.encolar(tmp);
        }

        while (!temp.esVacia()) {
            ExpedientesDepMedico.encolar(temp.desencolar());
        }
    }

    /**
     * Busca un expediente en la cola de Matrícula por el DNI del interesado.
     * @param dni DNI a buscar
     * @return El expediente encontrado, o null si no existe
     */
    public static Expediente buscarPorDNI(String dni) {
        Cola<Expediente> temp = new Cola<>();
        Expediente EAux = new Expediente();
        Interesado IAux = new Interesado();
        while (!ExpedientesDepMedico.esVacia()) {
            Expediente tmp = ExpedientesDepMedico.desencolar();
            IAux = tmp.getInteresado();
            String Saux = IAux.getDni();
            if (Saux.equals(dni)) {
                EAux = tmp;
            }
            temp.encolar(tmp);
        }
        while (!temp.esVacia()) {
            ExpedientesDepMedico.encolar(temp.desencolar());
        }
        return EAux;
    }
}
