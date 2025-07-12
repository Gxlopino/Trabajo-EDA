package Controlador;

import Modelo.Expediente;
import TDA.Cola;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 * Clase utilitaria para guardar expedientes en un archivo de texto.
 * Recorre todas las colas y guarda los datos en formato adecuado para su posterior carga.
 * Incluye el campo de dependencia y maneja los estados para el Administrador.
 *
 * Solo se agregaron comentarios aclaratorios, la lógica es la original.
 */
public class GuardadorDatos {

    /**
     * Guarda todos los expedientes activos (no finalizados) en un archivo de texto.
     * Recorre todas las colas de expedientes (Administrador, Admision, Alumnos/Egresados, Matricula)
     * y guarda cada expediente en formato texto, agregando el campo 'Dependencia' según la cola y estado.
     *
     * Formato por línea:
     * dni;nombres;telefono;tipo;email;prioridad;asunto;documentoReferencia;estado;fechaInicio;fechaFinal;documentoResultante;dependencia
     *
     * @param rutaArchivo La ruta absoluta o relativa donde se guardará el archivo .txt de salida.
     *                    Por ejemplo: "expedientes.txt" o "C:/archivos/expedientes.txt"
     *                    Si el archivo ya existe, será sobrescrito.
     */
    public static void guardarExpedientesEnTxt(String rutaArchivo) {
        // Formato de fecha para guardar las fechas en el archivo (año-mes-día)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // El 'false' en FileWriter indica que se debe sobrescribir el archivo.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, false))) {
            // Guardar expedientes de la cola principal (Administrador)
            // Cola temporal para no perder los expedientes al recorrer la cola principal
            Cola<Expediente> tempAdmin = new Cola<>();
            while (!Modelo.Administrador.ExpedientesPrincipal.esVacia()) {
                Expediente exp = Modelo.Administrador.ExpedientesPrincipal.desencolar();
                tempAdmin.encolar(exp);
                // Si el expediente está en estado 1, es "En proceso de derivacion"; si no, está bajo el Administrador
                String fechaInicioStr = (exp.getFechaInicio() != null) ? sdf.format(exp.getFechaInicio()) : "null";
                String fechaFinalStr = (exp.getFechaFinal() != null) ? sdf.format(exp.getFechaFinal()) : "null";
                String docResultanteStr = (exp.getDocumentoResultante() != null && !exp.getDocumentoResultante().isEmpty()) ? exp.getDocumentoResultante() : "null";
                String linea = String.join(";",
                        exp.getInteresado().getDni(),
                        exp.getInteresado().getNombre(),
                        exp.getInteresado().getTelefono(),
                        exp.getInteresado().getTipo(),
                        exp.getInteresado().getEmail(),
                        exp.getPrioridad(),
                        exp.getAsunto(),
                        exp.getDocumentoReferencia(),
                        String.valueOf(exp.getEstado()),
                        fechaInicioStr,
                        fechaFinalStr,
                        docResultanteStr,
                        "Administrador");
                bw.write(linea);
                bw.newLine();
            }
            // Restaura la cola principal a su estado original
            while (!tempAdmin.esVacia()) {
                Modelo.Administrador.ExpedientesPrincipal.encolar(tempAdmin.desencolar());
            }

            // Guardar expedientes de Admisión
            Cola<Expediente> tempAdmision = new Cola<>();
            while (!Modelo.Bienestar_Class.ExpedientesBienestar.esVacia()) {
                Expediente exp = Modelo.Bienestar_Class.ExpedientesBienestar.desencolar();
                tempAdmision.encolar(exp);
                String fechaInicioStr = (exp.getFechaInicio() != null) ? sdf.format(exp.getFechaInicio()) : "null";
                String fechaFinalStr = (exp.getFechaFinal() != null) ? sdf.format(exp.getFechaFinal()) : "null";
                String docResultanteStr = (exp.getDocumentoResultante() != null) ? exp.getDocumentoResultante() : "null";
                String linea = String.join(";",
                        exp.getInteresado().getDni(),
                        exp.getInteresado().getNombre(),
                        exp.getInteresado().getTelefono(),
                        exp.getInteresado().getTipo(),
                        exp.getInteresado().getEmail(),
                        exp.getPrioridad(),
                        exp.getAsunto(),
                        exp.getDocumentoReferencia(),
                        String.valueOf(exp.getEstado()),
                        fechaInicioStr,
                        fechaFinalStr,
                        docResultanteStr,
                        "Bienestar");
                //Escritura
                bw.write(linea);
                //Espacio
                bw.newLine();
            }
            while (!tempAdmision.esVacia()) {
                Modelo.Bienestar_Class.ExpedientesBienestar.encolar(tempAdmision.desencolar());
            }

            // Guardar expedientes de Alumnos/Egresados
            Cola<Expediente> tempAlumEgre = new Cola<>();
            while (!Modelo.Empleabilidad_Class.ExpedientesEmpleabilidad.esVacia()) {
                Expediente exp = Modelo.Empleabilidad_Class.ExpedientesEmpleabilidad.desencolar();
                tempAlumEgre.encolar(exp);
                String fechaInicioStr = (exp.getFechaInicio() != null) ? sdf.format(exp.getFechaInicio()) : "null";
                String fechaFinalStr = (exp.getFechaFinal() != null) ? sdf.format(exp.getFechaFinal()) : "null";
                String docResultanteStr = (exp.getDocumentoResultante() != null && !exp.getDocumentoResultante().isEmpty()) ? exp.getDocumentoResultante() : "null";
                String linea = String.join(";",
                        exp.getInteresado().getDni(),
                        exp.getInteresado().getNombre(),
                        exp.getInteresado().getTelefono(),
                        exp.getInteresado().getTipo(),
                        exp.getInteresado().getEmail(),
                        exp.getPrioridad(),
                        exp.getAsunto(),
                        exp.getDocumentoReferencia(),
                        String.valueOf(exp.getEstado()),
                        fechaInicioStr,
                        fechaFinalStr,
                        docResultanteStr,
                        "Empleabilidad");
                bw.write(linea);
                bw.newLine();
            }
            while (!tempAlumEgre.esVacia()) {
                Modelo.Empleabilidad_Class.ExpedientesEmpleabilidad.encolar(tempAlumEgre.desencolar());
            }

            // Guardar expedientes de Matrícula
            Cola<Expediente> tempMatricula = new Cola<>();
            while (!Modelo.Dep_medico_Class.ExpedientesDepMedico.esVacia()) {
                Expediente exp = Modelo.Dep_medico_Class.ExpedientesDepMedico.desencolar();
                tempMatricula.encolar(exp);
                String fechaInicioStr = (exp.getFechaInicio() != null) ? sdf.format(exp.getFechaInicio()) : "null";
                String fechaFinalStr = (exp.getFechaFinal() != null) ? sdf.format(exp.getFechaFinal()) : "null";
                String docResultanteStr = (exp.getDocumentoResultante() != null && !exp.getDocumentoResultante().isEmpty()) ? exp.getDocumentoResultante() : "null";
                String linea = String.join(";",
                        exp.getInteresado().getDni(),
                        exp.getInteresado().getNombre(),
                        exp.getInteresado().getTelefono(),
                        exp.getInteresado().getTipo(),
                        exp.getInteresado().getEmail(),
                        exp.getPrioridad(),
                        exp.getAsunto(),
                        exp.getDocumentoReferencia(),
                        String.valueOf(exp.getEstado()),
                        fechaInicioStr,
                        fechaFinalStr,
                        docResultanteStr,
                        "Dep. Médico");
                bw.write(linea);
                bw.newLine();
            }
            while (!tempMatricula.esVacia()) {
                Modelo.Dep_medico_Class.ExpedientesDepMedico.encolar(tempMatricula.desencolar());
            }
            // Mensaje de éxito en consola
            System.out.println("Datos de expedientes guardados correctamente en " + rutaArchivo);
        } catch (IOException e) {
            // Si ocurre un error al guardar, muestra un mensaje emergente de error
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar los datos en 'expedientes.txt'.", "Error de Guardado", JOptionPane.ERROR_MESSAGE);
        }
    }

}    
