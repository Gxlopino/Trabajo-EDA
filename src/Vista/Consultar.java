package Vista;

import Modelo.Administrador;
import static Modelo.Administrador.buscarPorDNI;
import Modelo.Expediente;
import Modelo.Interesado;
import TDA.Cola;
import TDA.ListaDoble;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Panel para consultar expedientes por DNI y ver sus detalles.
 *
 * @author fernando
 */
public class Consultar extends javax.swing.JPanel {

    DefaultTableModel model;
    DefaultTableModel modelFinalizados;

    /**
     * Crea un nuevo panel de consulta.
     */
    public Consultar() {
        initComponents();
        String[] columnas = {"ID", "DNI", "Nombre", "Prioridad", "Asunto", "Dependencia"};
        model = new DefaultTableModel(columnas, 0);
        jtableExp.setModel(model);
        mostrarPorPrioridad();
        

        // Modelo para expedientes finalizados
        String[] columnasFinalizados = {"ID", "DNI", "Nombre", "Prioridad", "Asunto", "Dependencia"};
        modelFinalizados = new DefaultTableModel(columnasFinalizados, 0);
        
        //Para que funcione presionando enter
        txtBúsuqeda.addActionListener((ActionEvent e) -> {
            buscarExpedientePorDNI();
        });
    }

    /**
     * Busca un expediente por el DNI ingresado y lo muestra en la tabla.
     */
    private void buscarExpedientePorDNI() {
        String dni = txtBúsuqeda.getText().trim();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un DNI para buscar.", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!dni.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "Por favor, El DNI debe tener 8 dígitos", "DNI invalido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.setRowCount(0);
        boolean encontrado = false;

        // Buscar en cola principal ya que se mantenie en la cola princpial, una copia se manda a las dependencias
        TDA.Cola<Expediente> tempPrincipal = new TDA.Cola<>();
        while (!Modelo.Administrador.ExpedientesPrincipal.esVacia()) {
            Expediente exp = Modelo.Administrador.ExpedientesPrincipal.desencolar();
            if (exp.getInteresado() != null && dni.equals(exp.getInteresado().getDni())) {
                model.addRow(new Object[]{
                    exp.getId(),
                    exp.getInteresado().getDni(),
                    exp.getInteresado().getNombre(),
                    exp.getPrioridad(),
                    exp.getAsunto(),
                    exp.Dependencia
                });
                encontrado = true;

            } else if (exp.getInteresado() != null && dni.equals(exp.getInteresado().getDni()) && esFinalizados.isSelected()) {
                model.addRow(new Object[]{
                    exp.getId(),
                    exp.getInteresado().getDni(),
                    exp.getInteresado().getNombre(),
                    exp.getPrioridad(),
                    exp.getAsunto(),
                    exp.Dependencia
                });
                encontrado = true;
            }

            tempPrincipal.encolar(exp);
        }
        while (!tempPrincipal.esVacia()) {
            Modelo.Administrador.ExpedientesPrincipal.encolar(tempPrincipal.desencolar());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableExp = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBúsuqeda = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        bttnDetalles = new javax.swing.JButton();
        esFinalizados = new javax.swing.JCheckBox();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtableExp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtableExp);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 410, 190));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Búsqueda por DNI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, -1));
        jPanel1.add(txtBúsuqeda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 36, 130, 30));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 10, 70));

        bttnDetalles.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        bttnDetalles.setText("VER DETALLES");
        bttnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnDetallesActionPerformed(evt);
            }
        });
        jPanel1.add(bttnDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 10, 140, 60));

        esFinalizados.setText("Finalizados");
        esFinalizados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esFinalizadosActionPerformed(evt);
            }
        });
        jPanel1.add(esFinalizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bttnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnDetallesActionPerformed
        // Obtener la fila seleccionada en la tabla (jTable1)
        int selectedRow = jtableExp.getSelectedRow();

        // Verificar si no se ha seleccionado ninguna fila
        if (selectedRow == -1) {
            // Mostrar mensaje de advertencia si no hay selección
            JOptionPane.showMessageDialog(this,
                    "Por favor, seleccione un expediente de la tabla.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si no hay fila seleccionada
        }

        // Obtener el DNI de la fila seleccionada (columna 1 del modelo)
        String dni = (String) model.getValueAt(selectedRow, 1);

        // Buscar el expediente correspondiente al DNI obtenido
        Expediente expediente = Administrador.buscarPorDNI(dni);

        // Verificar si se encontró el expediente
        if (expediente != null) {
            // Obtener los datos del interesado asociado al expediente
            Interesado interesado = expediente.getInteresado();

            // Crear formateador de fecha (día/mes/año)
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Formatear fecha final (si existe) o mostrar "N/A" si es null
            String fechaFinal = (expediente.getFfinal() != null)
                    ? sdf.format(expediente.getFfinal())
                    : "N/A";

            // Obtener nombre del documento resultante o mensaje si no existe
            String docResultante = (expediente.getDocumentoResultante() != null)
                    ? expediente.getDocumentoResultante()
                    : "No generado aún.";

            // Convertir el código numérico del estado a texto legible
            String estado;
            estado = switch (expediente.getEstado()) {
                case 1 ->
                    "Sin derivar";
                case 2 ->
                    "En proceso";
                case 3 ->
                    "Finalizado";
                default ->
                    "Desconocido";
            };

            // Construir el mensaje con todos los detalles
            String detalles = "--- Detalles del Interesado ---\n"
                    + "DNI: " + interesado.getDni() + "\n"
                    + "Nombre: " + interesado.getNombre() + "\n"
                    + "Teléfono: " + interesado.getTelefono() + "\n"
                    + "Email: " + interesado.getEmail() + "\n"
                    + "Tipo: " + interesado.getTipo() + "\n\n"
                    + "--- Detalles del Expediente ---\n"
                    + "ID: " + expediente.getId() + "\n"
                    + "Asunto: " + expediente.getAsunto() + "\n"
                    + "Prioridad: " + expediente.getPrioridad() + "\n"
                    + "Estado: " + estado + "\n"
                    + "Dependencia: " + expediente.getDependencia() + "\n"
                    + "Fecha de Finalización: " + fechaFinal + "\n"
                    + "Documento de Referencia: " + expediente.getDocumentoReferencia() + "\n"
                    + "Documento Resultante: " + docResultante + "\n";

            // Mostrar el mensaje con todos los detalles en un cuadro de diálogo
            JOptionPane.showMessageDialog(this,
                    detalles,
                    "Detalles Completos del Expediente",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_bttnDetallesActionPerformed

    private void bttnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnBuscarActionPerformed
    }//GEN-LAST:event_bttnBuscarActionPerformed

    private void esFinalizadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esFinalizadosActionPerformed
        // TODO add your handling code here:
        if (esFinalizados.isSelected()) {
            cargarExpedientesFinalizados();
        } else {
            mostrarPorPrioridad();
        }
    }//GEN-LAST:event_esFinalizadosActionPerformed

    public void mostrarPorPrioridad() {
        DefaultTableModel modelo = (DefaultTableModel) jtableExp.getModel();
        modelo.setRowCount(0);

        Cola<Expediente> original = Modelo.Administrador.ExpedientesPrincipal;
        Cola<Expediente> temp = new Cola<>();
        Cola<Expediente> alta = new Cola<>();
        Cola<Expediente> media = new Cola<>();
        Cola<Expediente> baja = new Cola<>();

        while (!original.esVacia()) {
            Expediente e = original.desencolar();
            if (e.getEstado() == 1 || e.getEstado() == 2) {
                switch (e.getPrioridad()) {
                    case "Alta":
                        alta.encolar(e);
                        break;
                    case "Media":
                        media.encolar(e);
                        break;
                    case "Baja":
                        baja.encolar(e);
                        break;
                }
            }
            temp.encolar(e);
        }

        while (!temp.esVacia()) {
            original.encolar(temp.desencolar());
        }

        agregarColaATabla(alta, modelo);
        agregarColaATabla(media, modelo);
        agregarColaATabla(baja, modelo);
    }

    private void agregarColaATabla(Cola<Expediente> cola, DefaultTableModel modelo) {
        Cola<Expediente> temp = new Cola<>();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        while (!cola.esVacia()) {
            Expediente e = cola.desencolar();
            Interesado i = e.getInteresado();
            String fechaFinal = (e.getFfinal() != null) ? sdf.format(e.getFfinal()) : "N/A";

            modelo.addRow(new Object[]{
                e.getId(),
                i.getDni(),
                i.getNombre(),
                e.getPrioridad(),
                e.getAsunto(),
                fechaFinal
            });
            temp.encolar(e);
        }
        // Restaurar la cola original
        while (!temp.esVacia()) {
            cola.encolar(temp.desencolar());
        }
    }

    private void cargarExpedientesFinalizados() {
        model.setRowCount(0); // Limpiar la tabla
        TDA.ListaDoble<Expediente> lista = Administrador.ExpedientesFinalizados;

        for (int i = 1; i <= lista.longitud(); i++) {
            Expediente exp = lista.iesimo(i);
            if (exp != null && exp.getInteresado() != null) {
                model.addRow(new Object[]{
                    exp.getId(),
                    exp.getInteresado().getDni(),
                    exp.getInteresado().getNombre(),
                    exp.getPrioridad(),
                    exp.getAsunto(),
                    exp.getDependencia()
                });
            }
        }

        jtableExp.setModel(model); // Por si acaso, actualizar el modelo
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttnDetalles;
    private javax.swing.JCheckBox esFinalizados;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jtableExp;
    private javax.swing.JTextField txtBúsuqeda;
    // End of variables declaration//GEN-END:variables
}
