/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Modelo.Administrador;
import Modelo.Bienestar_Class;
import Modelo.Expediente;
import Modelo.Interesado;
import TDA.*;
import static Vista.MenuPrincipal.content;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fazef
 */
public class Empleabilidad extends javax.swing.JPanel {

    Border default_border = BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(153, 153, 153));
    Border red_border = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.RED);
    Lista<JButton> botones = new Lista<>();

    private final DefaultTableModel model;

    public Empleabilidad() {
        initComponents();
        String[] columnas = {"ID", "DNI", "Nombre", "Prioridad", "Asunto", "F. Final"};
        model = new DefaultTableModel(columnas, 0);
        jTableAlumn_Egr.setModel(model);
        mostrarPorPrioridad();
        botones.agregar(bttnAdmision);
        botones.agregar(bttnAlumEgre);
        botones.agregar(bttnMatricula);
        //Aplicar el mismo formato de borde a todos los botones
        for (int i = 1; i <= botones.longitud(); i++) {
            JButton boton = botones.iesimo(i);
            boton.setBorder(default_border);
        }

        addAction(); //Cargar el metodo para la interacción con los botones.
    }

    public void mostrarPorPrioridad() {
        DefaultTableModel modelo = (DefaultTableModel) jTableAlumn_Egr.getModel();
        modelo.setRowCount(0);

        Cola<Expediente> original = Modelo.Empleabilidad_Class.ExpedientesEmpleabilidad;
        Cola<Expediente> temp = new Cola<>();
        Cola<Expediente> alta = new Cola<>();
        Cola<Expediente> media = new Cola<>();
        Cola<Expediente> baja = new Cola<>();

        while (!original.esVacia()) {
            Expediente e = original.desencolar();
            if (e.getEstado() == 2) {
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

    public void setButtonBorder(JButton button) {
        for (int i = 1; i <= botones.longitud(); i++) {
            JButton b = botones.iesimo(i);
            b.setBorder(default_border);
            b.setForeground(new Color(153, 153, 153));
        }

        // Borde rojo para el botón seleccionado
        button.setBorder(red_border);
        button.setForeground(Color.BLACK);
    }

    public void resaltarBoton(JButton seleccionado) {
        for (int i = 1; i <= botones.longitud(); i++) {
            JButton boton = botones.iesimo(i);

            if (boton == seleccionado) {
                boton.setEnabled(true);
                boton.setForeground(Color.BLACK);
                boton.setBackground(Color.WHITE);
            } else {
                boton.setEnabled(false);
                boton.setForeground(new Color(180, 180, 180)); // gris claro
                boton.setBackground(new Color(230, 230, 230)); // fondo más pálido
            }
        }
    }

    public void addAction() {
        Nodo<JButton> aux = botones.getCabeza();
        while (aux != null) {
            JButton button = aux.getItem();
            button.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setButtonBorder(button);
                    resaltarBoton(button);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBorder(red_border);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBorder(default_border);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }
            });
            aux = aux.getSgteNodo();
        }
    }

    private void buscarExpedientePorDNI() {
        String dni = txtBuscar.getText().trim();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un DNI para buscar.", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!dni.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "Por favor, El DNI debe tener 8 digitos", "DNI invalido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Expediente expediente = Modelo.Empleabilidad_Class.buscarPorDNI(dni);
        model.setRowCount(0);

        if (expediente != null && expediente.getInteresado() != null) {
            Interesado interesado = expediente.getInteresado();
            model.addRow(new Object[]{
                expediente.getId(),
                interesado.getDni(),
                interesado.getNombre(),
                expediente.getPrioridad(),
                expediente.getAsunto()
            });
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún expediente con el DNI proporcionado.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void ShowJPanel(JPanel p) {
        /* Muestra el contenido del Jpanel p para que se muestre en content. */
        p.setSize(430, 280);
        p.setLocation(0, 0);

        content.removeAll();
        content.add(p, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelAL = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlumn_Egr = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        bttnBuscar = new javax.swing.JButton();
        bttnDerivar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        bttnAdmision = new javax.swing.JButton();
        bttnAlumEgre = new javax.swing.JButton();
        bttnMatricula = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jPanelAL.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableAlumn_Egr.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableAlumn_Egr);

        jPanelAL.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 410, 150));
        jPanelAL.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 130, 30));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel2.setText("Búsqueda por DNI");
        jPanelAL.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, -1));

        bttnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bttnBuscar.setText("BUSCAR");
        bttnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnBuscarActionPerformed(evt);
            }
        });
        jPanelAL.add(bttnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, 30));

        bttnDerivar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bttnDerivar.setText("DERIVAR");
        bttnDerivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnDerivarActionPerformed(evt);
            }
        });
        jPanelAL.add(bttnDerivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 160, 50));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanelAL.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 20, 40));

        bttnAdmision.setText("Bienestar");
        bttnAdmision.setBorder(null);
        bttnAdmision.setContentAreaFilled(false);
        bttnAdmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAdmisionActionPerformed(evt);
            }
        });
        jPanelAL.add(bttnAdmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 100, 30));

        bttnAlumEgre.setText("Empleabilidad");
        bttnAlumEgre.setBorder(null);
        bttnAlumEgre.setContentAreaFilled(false);
        bttnAlumEgre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAlumEgreActionPerformed(evt);
            }
        });
        jPanelAL.add(bttnAlumEgre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 130, 30));

        bttnMatricula.setText("Dep. Médico");
        bttnMatricula.setBorder(null);
        bttnMatricula.setContentAreaFilled(false);
        bttnMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnMatriculaActionPerformed(evt);
            }
        });
        jPanelAL.add(bttnMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 100, 30));

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Empleabilidad");
        jPanelAL.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAL, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelAL, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bttnAdmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAdmisionActionPerformed
        // TODO add your handling code here:
        ShowJPanel(new Bienestar());
    }//GEN-LAST:event_bttnAdmisionActionPerformed

    private void bttnAlumEgreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAlumEgreActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_bttnAlumEgreActionPerformed

    private void bttnMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnMatriculaActionPerformed
        // TODO add your handling code here:
        ShowJPanel(new Dep_medico());
    }//GEN-LAST:event_bttnMatriculaActionPerformed

    private void bttnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnBuscarActionPerformed
        buscarExpedientePorDNI();        // TODO add your handling code here:
    }//GEN-LAST:event_bttnBuscarActionPerformed

    private void bttnDerivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnDerivarActionPerformed
        int filaSeleccionada = jTableAlumn_Egr.getSelectedRow();
        if (filaSeleccionada == -1) {
            // No hay fila seleccionada
            JOptionPane.showMessageDialog(this, "Seleccione una fila antes de derivar.");
            return;
        }
        String[] opciones = {"Bienestar", "Administrador", "Dep. Médico"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);

        int opcion = JOptionPane.showConfirmDialog(
                this,
                comboBox,
                "¿A dónde deseas derivar?",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {
            String dependencia = (String) comboBox.getSelectedItem();
            String dni = (String) jTableAlumn_Egr.getValueAt(filaSeleccionada, 1);
            Expediente exp = Modelo.Empleabilidad_Class.buscarPorDNI(dni);
            Modelo.Empleabilidad_Class.derivarA(dependencia, exp);
            mostrarPorPrioridad();
            System.out.println("Destino seleccionado: " + dependencia);
            Modelo.Empleabilidad_Class.EliminarDeCola(exp);
            Administrador.buscarPorDNI(dni).setDependencia(dependencia);
        }
    }//GEN-LAST:event_bttnDerivarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttnAdmision;
    private javax.swing.JButton bttnAlumEgre;
    private javax.swing.JButton bttnBuscar;
    private javax.swing.JButton bttnDerivar;
    private javax.swing.JButton bttnMatricula;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelAL;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableAlumn_Egr;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
