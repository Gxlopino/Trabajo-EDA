/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
/**
 * Clase que representa un expediente (caso o trámite) dentro del sistema.
 * Cada expediente tiene información del interesado, prioridad, asunto, estado y dependencia actual.
 * Se utiliza para gestionar el flujo de documentos entre dependencias.
 */
public class Expediente {

    // Contador de ID para expedientes
    private static int contadorID = 1;

    // Atributos del expediente
    /**
     * ID único del expediente
     */
    private int id;

    /**
     * Prioridad del expediente (puede ser "Alta", "Media" o "Baja")
     */
    private String prioridad;

    /**
     * Persona o entidad que solicita el trámite
     */
    private Interesado interesado;

    /**
     * Descripción breve del trámite o petición
     */
    private String asunto;

    /**
     * Nombre del documento adjunto o referencia asociada
     */
    private String documentoReferencia;

    /**
     * Estado del expediente:
     * 1: En proceso de derivación
     * 2: En cola principal del Administrador
     * 3: Finalizado
     */
    private int estado;

    /**
     * Nombre de la dependencia actual donde está el expediente
     */
    public String Dependencia = "Administrador";

    /**
     * Fecha en que se inició el trámite
     */
    private Date fechaInicio;

    /**
     * Fecha en que se finalizó el trámite (puede ser null si no ha finalizado)
     */
    private Date fechaFinal;

    /**
     * Documento generado como resultado del trámite (es null cuando el expediente no esta compltado)
     */
    private String documentoResultante;

    /**
     * Constructor por defecto.
     */
    public Expediente() {
    }

    /**
     * Constructor para crear un nuevo expediente.
     *
     * @param prioridad La prioridad del expediente (Alta, Media, Baja).
     * @param interesado El interesado que registra el expediente.
     * @param asunto El motivo del trámite.
     * @param documentoReferencia Documento adjunto como referencia.
     */
    public Expediente(String prioridad, Interesado interesado, String asunto, String documentoReferencia) {
        this.id = contadorID++;
        this.prioridad = prioridad;
        this.interesado = interesado;
        this.asunto = asunto;
        this.documentoReferencia = documentoReferencia;
        this.fechaInicio = new Date();
        this.fechaFinal = calcularFechaFinal(this.fechaInicio, this.asunto);
        this.estado = 1;
        this.documentoResultante = null; // Se genera al finalizar
    }

    // --- Getters y Setters ---
    /**
     * Devuelve el ID del expediente.
     *
     * @return ID del expediente
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve la dependencia actual del expediente.
     *
     * @return Nombre de la dependencia
     */
    public String getDependencia() {
        return Dependencia;
    }

    /**
     * Establece la dependencia actual del expediente.
     *
     * @param dependencia Nombre de la dependencia
     */
    public void setDependencia(String dependencia) {
        this.Dependencia = dependencia;
    }

    /**
     * Devuelve el interesado (solicitante) de este expediente.
     *
     * @return Objeto Interesado
     */
    public Interesado getInteresado() {
        return interesado;
    }

    /**
     * Establece el interesado (solicitante) de este expediente.
     *
     * @param interesado Objeto Interesado a asignar
     */
    public void setInteresado(Interesado interesado) {
        this.interesado = interesado;
    }

    /**
     * Devuelve el asunto del expediente.
     *
     * @return Asunto (descripción del trámite)
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Devuelve la fecha de inicio del expediente.
     *
     * @return Fecha de inicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio del expediente.
     *
     * @param fechaInicio Fecha de inicio
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Devuelve la fecha de finalización del expediente.
     *
     * @return Fecha de finalización (puede ser null)
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * Establece la fecha de finalización del expediente.
     *
     * @param fechaFinal Fecha de finalización
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * Devuelve el estado actual del expediente.
     *
     * @return Estado (1: en proceso, 2: en administrador, 3: finalizado)
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Establece el estado del expediente.
     *
     * @param estado Valor entero (1, 2 o 3)
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * Devuelve el documento resultante generado por el trámite.
     *
     * @return Nombre del documento resultante (puede ser null)
     */
    public String getDocumentoResultante() {
        return documentoResultante;
    }

    /**
     * Establece el documento resultante del expediente.
     *
     * @param documentoResultante Nombre del documento resultante
     */
    public void setDocumentoResultante(String documentoResultante) {
        this.documentoResultante = documentoResultante;
    }


    /**
     * Devuelve la prioridad del expediente.
     * @return Prioridad ("Alta", "Media" o "Baja")
     */
    public String getPrioridad() {
        return prioridad;
    }

    public Date getFfinal() {
        return fechaFinal;
    }

    public void setFfinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * Devuelve el documento de referencia asociado al expediente.
     * @return Nombre del documento de referencia
     */
    public String getDocumentoReferencia() {
        return documentoReferencia;
    }

    /**
     * Calcula la fecha de finalización estimada del expediente.
     *
     * @param fechaInicio La fecha en que se inicia el trámite.
     * @param asunto El tipo de asunto para determinar los días de plazo.
     * @return La fecha de finalización calculada.
     */
    private Date calcularFechaFinal(Date fechaInicio, String asunto) {
        // 1. Crear un objeto Calendar (para manipular fechas fácilmente)
        Calendar calendar = Calendar.getInstance();

        // 2. Establecer la fecha de inicio en el Calendar
        calendar.setTime(fechaInicio);

        // 3. Establecer un plazo por defecto de 2 días
        Random rand = new Random();
        int diasASumar = rand.nextInt(4);


        // 5. Sumar los días calculados a la fecha inicial
        calendar.add(Calendar.DAY_OF_YEAR, diasASumar);

        // 6. Devolver la nueva fecha calculada
        return calendar.getTime();
    }

    /**
     * Genera el nombre del documento resultante.
     *
     * @return El nombre del documento con formato: (primer caracter
     * asunto)(último caracter asunto)(últimos 2 dígitos DNI).pdf
     */
    private String generarNombreDocumentoResultante() {
        char primerCaracter = this.asunto.charAt(0);
        char ultimoCaracter = this.asunto.charAt(this.asunto.length() - 1);
        String dni = this.interesado.getDni();
        String ultimosDosDigitosDNI = dni.substring(dni.length() - 2);
        return "" + primerCaracter + ultimoCaracter + ultimosDosDigitosDNI + Expediente.contadorID + ".pdf";
    }

    /**
     * Cambia el estado del expediente a "En proceso".
     */
    public void derivarTramite() {
        this.estado = 2;
    }

    /**
     * Cambia el estado del expediente a "Finalizado" y genera el documento
     * resultante.
     */
    public void completarTramite() {
        this.estado = 3;
        this.documentoResultante = generarNombreDocumentoResultante();
    }

    /**
     * Muestra la información del expediente en la consola.
     */
    public void mostrar() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaInicioStr = (fechaInicio != null) ? sdf.format(fechaInicio) : "N/A";
        String fechaFinalStr = (fechaFinal != null) ? sdf.format(fechaFinal) : "N/A";
        String docResultanteStr = (documentoResultante != null) ? documentoResultante.toString() : "No generado";
        System.out.println("Expediente{" + "id=" + id + ", prioridad=" + prioridad + ", Interesado=" + interesado.toString() + ", Asunto=" + asunto + ", DocumentoReferencia=" + documentoReferencia + ", Finicio=" + fechaInicioStr + ", Ffinal=" + fechaFinalStr + ", Estado=" + estado + ", DocumentoResultante=" + docResultanteStr + '}');
    }
}
