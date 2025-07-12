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
public class Dep_medico extends javax.swing.JPanel {

    Border default_border = BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(153, 153, 153));
    Border red_border = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.RED);
    Lista<JButton> botones = new Lista<>();
    DefaultTableModel model;

    public Dep_medico() {
        initComponents();
        String[] columnas = {"ID", "DNI", "Nombre", "Prioridad", "Asunto", "F. Final"};
        model = new DefaultTableModel(columnas, 0);
        jTableMatricula.setModel(model); //jTableMatricula
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
        DefaultTableModel modelo = (DefaultTableModel) jTableMatricula.getModel();
        modelo.setRowCount(0);

        Cola<Expediente> original = Modelo.Dep_medico_Class.ExpedientesDepMedico;
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

        Expediente expediente = Modelo.Dep_medico_Class.buscarPorDNI(dni);
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

        jPanelMT = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMatricula = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        bttnBuscar = new javax.swing.JButton();
        bttnDerivar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        bttnAdmision = new javax.swing.JButton();
        bttnAlumEgre = new javax.swing.JButton();
        bttnMatricula = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jPanelMT.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMT.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableMatricula.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableMatricula);

        jPanelMT.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 410, 150));
        jPanelMT.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 130, 30));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        jLabel2.setText("Búsqueda por DNI");
        jPanelMT.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, -1));

        bttnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bttnBuscar.setText("BUSCAR");
        bttnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnBuscarActionPerformed(evt);
            }
        });
        jPanelMT.add(bttnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, 30));

        bttnDerivar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bttnDerivar.setText("DERIVAR");
        bttnDerivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnDerivarActionPerformed(evt);
            }
        });
        jPanelMT.add(bttnDerivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, 160, 50));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanelMT.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 20, 40));

        bttnAdmision.setText("Bienestar");
        bttnAdmision.setBorder(null);
        bttnAdmision.setContentAreaFilled(false);
        bttnAdmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAdmisionActionPerformed(evt);
            }
        });
        jPanelMT.add(bttnAdmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 100, 30));

        bttnAlumEgre.setText("Empleabilidad");
        bttnAlumEgre.setBorder(null);
        bttnAlumEgre.setContentAreaFilled(false);
        bttnAlumEgre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAlumEgreActionPerformed(evt);
            }
        });
        jPanelMT.add(bttnAlumEgre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 130, 30));

        bttnMatricula.setText("Dep. Médico");
        bttnMatricula.setBorder(null);
        bttnMatricula.setContentAreaFilled(false);
        bttnMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnMatriculaActionPerformed(evt);
            }
        });
        jPanelMT.add(bttnMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 100, 30));

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Departamento Médico");
        jPanelMT.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMT, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bttnAdmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAdmisionActionPerformed
        // TODO add your handling code here:
        ShowJPanel(new Bienestar());
    }//GEN-LAST:event_bttnAdmisionActionPerformed

    private void bttnAlumEgreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAlumEgreActionPerformed
        // TODO add your handling code here:
        ShowJPanel(new Empleabilidad());

    }//GEN-LAST:event_bttnAlumEgreActionPerformed

    private void bttnMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnMatriculaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_bttnMatriculaActionPerformed

    private void bttnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnBuscarActionPerformed
        buscarExpedientePorDNI();        // TODO add your handling code here:
    }//GEN-LAST:event_bttnBuscarActionPerformed

    private void bttnDerivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnDerivarActionPerformed

        int filaSeleccionada = jTableMatricula.getSelectedRow();
        if (filaSeleccionada == -1) {
            // No hay fila seleccionada
            JOptionPane.showMessageDialog(this, "Seleccione una fila antes de derivar.");
            return;
        }
        String[] opciones = {"Bienestar", "Empleabilidad", "Administrador"};
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
            String dni = (String) jTableMatricula.getValueAt(filaSeleccionada, 1);
            Expediente exp = Modelo.Dep_medico_Class.buscarPorDNI(dni);
            Modelo.Dep_medico_Class.derivarA(dependencia, exp);
            mostrarPorPrioridad();
            System.out.println("Destino seleccionado: " + dependencia);
            Modelo.Dep_medico_Class.EliminarDeCola(exp);
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
    private javax.swing.JPanel jPanelMT;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableMatricula;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
