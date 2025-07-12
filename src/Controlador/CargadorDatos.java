package Controlador;

import Modelo.Expediente;
import Modelo.Interesado;
import TDA.Lista;
import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Clase de utilidad para cargar datos desde archivos de texto.
 * Permite leer y distribuir los expedientes en las colas correspondientes según su dependencia y estado.
 */
public class CargadorDatos {

    /**
     * Carga expedientes desde un archivo de texto y los distribuye en las colas correspondientes.
     * El archivo debe tener el siguiente formato por línea:
     * dni;nombres;telefono;tipo;email;prioridad;asunto;documentoReferencia;estado;fechaInicio;fechaFinal;documentoResultante;dependencia
     * @param rutaArchivo Ruta absoluta o relativa del archivo .txt de entrada
     */
    public static void cargarExpedientesDesdeTxt(String rutaArchivo) {
        // Formato de fecha para interpretar las fechas del archivo
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue; // Ignorar líneas vacías

                // Reemplazo de String[] con TDA Lista
                Lista<String> datos = new Lista<>();
                String temp = "";

                // Recorremos cada carácter para dividir la línea manualmente por ';'
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);
                    if (c == ';') {
                        datos.agregar(temp.trim());
                        temp = "";
                    } else {
                        temp += c;
                    }
                }
                if (!temp.isEmpty()) datos.agregar(temp.trim()); // Agregar último campo

                // Validamos si hay 13 campos como se espera
                if (datos.longitud() == 13) {
                    try {
                        Interesado interesado = new Interesado(
                            datos.iesimo(1), datos.iesimo(2), datos.iesimo(3),
                            datos.iesimo(4), datos.iesimo(5)
                        );

                        Expediente expediente = new Expediente(
                            datos.iesimo(6), interesado, datos.iesimo(7), datos.iesimo(8)
                        );

                        expediente.setEstado(Integer.parseInt(datos.iesimo(9)));

                        if (!datos.iesimo(10).equals("null")) {
                            expediente.setFechaInicio(sdf.parse(datos.iesimo(10)));
                        }
                        if (!datos.iesimo(11).equals("null")) {
                            expediente.setFechaFinal(sdf.parse(datos.iesimo(11)));
                        }

                        String docResultante = datos.iesimo(12);
                        if (!docResultante.equals("null")) {
                            expediente.setDocumentoResultante(docResultante);
                        }

                        String dependencia = datos.iesimo(13);
                        expediente.Dependencia = dependencia;

                        // Distribución por dependencia
                        if (dependencia.equalsIgnoreCase("Administrador") || dependencia.equalsIgnoreCase("En proceso de derivacion")) {
                            Modelo.Administrador.agregar(expediente);
                        } else if (dependencia.equalsIgnoreCase("Bienestar")) {
                            Modelo.Bienestar_Class.agregar(expediente);
                        } else if (dependencia.equalsIgnoreCase("Empleabilidad")) {
                            Modelo.Empleabilidad_Class.agregar(expediente);
                        } else if (dependencia.equalsIgnoreCase("Dep. Médico")) {
                            Modelo.Dep_medico_Class.agregar(expediente);
                        } else {
                            Modelo.Administrador.agregar(expediente); // Valor por defecto
                        }
                    } catch (ParseException e) {
                        System.err.println("Error al parsear fechas para la línea: " + linea);
                    }
                } else {
                    System.err.println("Línea con formato incorrecto en el archivo: " + linea);
                }
            }

            System.out.println("Datos de expedientes cargados correctamente desde " + rutaArchivo);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "No se encontró el archivo 'expedientes.txt' o ocurrió un error al leerlo.\nEl programa iniciará sin datos precargados.",
                "Advertencia de Carga", JOptionPane.WARNING_MESSAGE);
        }
    }
}



