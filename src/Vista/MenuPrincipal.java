package Vista;

import Controlador.GuardadorDatos;
import TDA.Pila;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Ventana principal de la aplicación que gestiona la navegación entre paneles.
 *
 * @author fernando
 */
public class MenuPrincipal extends javax.swing.JFrame {

    Principal viewPrincipal;
    Seguimiento viewSeguimiento;
    RGSTExpediente viewExpediente;
    Consultar viewConsultar;
    Bienestar viewDependencias;
    private final Pila<JPanel> historialPaneles;

    /**
     * Crea un nuevo formulario MenuPrincipal.
     */
    public MenuPrincipal() {
        initComponents();
        viewPrincipal = new Principal();
        viewSeguimiento = new Seguimiento();
        viewExpediente = new RGSTExpediente();
        viewConsultar = new Consultar();
        viewDependencias = new Bienestar();
        historialPaneles = new Pila<>();

        ShowJPanelPila(viewPrincipal, false);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Sistema de Trámite Documentario");
        setSize(new Dimension(610, 375));

        Controlador.Notificador notificador = new Controlador.Notificador();
        notificador.iniciar();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                GuardadorDatos.guardarExpedientesEnTxt("expedientes.txt");
            }
        });
    }

    /**
     * Muestra un panel en el área de contenido principal y gestiona el
     * historial.
     *
     * @param p El panel a mostrar.
     * @param apilar Si es verdadero, el panel actual se guarda en el historial.
     */
    public void ShowJPanelPila(JPanel p, boolean apilar) {
        // Si hay un panel actual visible y debemos apilarlo
        if (apilar && content.getComponentCount() > 0) {
            JPanel panelActual = (JPanel) content.getComponent(0);
            // Solo apilamos si no es el mismo panel que estamos mostrando
            if (historialPaneles.esVacia() || historialPaneles.cima.getItem() != panelActual) {
                historialPaneles.apilar(panelActual);
            }
        }

        p.setSize(430, 280);
        p.setLocation(0, 0);

        content.removeAll();
        content.add(p, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    private void ShowJPanelPrincipal(JPanel p) {
        /* Muestra el contenido del Jpanel p para que se muestre en content. */
        p.setSize(430, 280);
        p.setLocation(0, 0);

        content.removeAll();
        content.add(p, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bttnExpediente = new javax.swing.JButton();
        bttnSeguimiento = new javax.swing.JButton();
        bttnConsultar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bttnDependencias = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        bttnPrincipal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));
        jPanel2.setForeground(new java.awt.Color(255, 153, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bttnExpediente.setBackground(new java.awt.Color(255, 153, 51));
        bttnExpediente.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        bttnExpediente.setForeground(new java.awt.Color(255, 255, 255));
        bttnExpediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Copia de historial1.png"))); // NOI18N
        bttnExpediente.setText("Expediente");
        bttnExpediente.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(255, 153, 0)));
        bttnExpediente.setBorderPainted(false);
        bttnExpediente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bttnExpediente.setIconTextGap(15);
        bttnExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnExpedienteActionPerformed(evt);
            }
        });
        jPanel2.add(bttnExpediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 160, 40));

        bttnSeguimiento.setBackground(new java.awt.Color(255, 153, 51));
        bttnSeguimiento.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        bttnSeguimiento.setForeground(new java.awt.Color(255, 255, 255));
        bttnSeguimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Copia de anadir.png"))); // NOI18N
        bttnSeguimiento.setText("Seguimiento");
        bttnSeguimiento.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(255, 153, 0)));
        bttnSeguimiento.setBorderPainted(false);
        bttnSeguimiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bttnSeguimiento.setIconTextGap(15);
        bttnSeguimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnSeguimientoActionPerformed(evt);
            }
        });
        jPanel2.add(bttnSeguimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 160, 40));

        bttnConsultar.setBackground(new java.awt.Color(255, 153, 51));
        bttnConsultar.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        bttnConsultar.setForeground(new java.awt.Color(255, 255, 255));
        bttnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Copia de activo.png"))); // NOI18N
        bttnConsultar.setText("Consultar");
        bttnConsultar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(255, 153, 0)));
        bttnConsultar.setBorderPainted(false);
        bttnConsultar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bttnConsultar.setIconTextGap(15);
        bttnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnConsultarActionPerformed(evt);
            }
        });
        jPanel2.add(bttnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 160, 40));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("© Grupo 2");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo (1) (1).png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 100, 90));

        bttnDependencias.setBackground(new java.awt.Color(255, 153, 51));
        bttnDependencias.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        bttnDependencias.setForeground(new java.awt.Color(255, 255, 255));
        bttnDependencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Copia de categorias.png"))); // NOI18N
        bttnDependencias.setText("Dependencias");
        bttnDependencias.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(255, 153, 0)));
        bttnDependencias.setBorderPainted(false);
        bttnDependencias.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bttnDependencias.setIconTextGap(15);
        bttnDependencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnDependenciasActionPerformed(evt);
            }
        });
        jPanel2.add(bttnDependencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 160, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 159, 350));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 22)); // NOI18N
        jLabel1.setText("Sistema de Trámite");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 266, -1));

        content.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        jPanel1.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 430, 280));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 410, -1));

        bttnPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hogar (1).png"))); // NOI18N
        bttnPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnPrincipalActionPerformed(evt);
            }
        });
        jPanel1.add(bttnPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttnExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnExpedienteActionPerformed
        if (content.getComponentCount() == 0 || content.getComponent(0) != viewExpediente) {
            ShowJPanelPila(viewExpediente, true);
        }
    }//GEN-LAST:event_bttnExpedienteActionPerformed

    private void bttnPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnPrincipalActionPerformed
        ShowJPanelPrincipal(viewPrincipal);
    }//GEN-LAST:event_bttnPrincipalActionPerformed

    private void bttnSeguimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnSeguimientoActionPerformed
        viewSeguimiento.mostrarPorPrioridad(false);
        // Solo apilamos si no estamos ya en este panel
        if (content.getComponentCount() == 0 || content.getComponent(0) != viewSeguimiento) {
            ShowJPanelPila(viewSeguimiento, true);
        }
    }//GEN-LAST:event_bttnSeguimientoActionPerformed

    private void bttnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnConsultarActionPerformed
        // Solo apilamos si no estamos ya en este panel
        viewConsultar.mostrarPorPrioridad();
        if (content.getComponentCount() == 0 || content.getComponent(0) != viewConsultar) {
            ShowJPanelPila(viewConsultar, true);
        }
    }//GEN-LAST:event_bttnConsultarActionPerformed

    private void bttnDependenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnDependenciasActionPerformed
        if (content.getComponentCount() == 0 || content.getComponent(0) != viewDependencias) {
            viewDependencias.mostrarPorPrioridad();
            ShowJPanelPila(viewDependencias, true);
        }
    }//GEN-LAST:event_bttnDependenciasActionPerformed

    public static void main(String args[]) {
        FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttnConsultar;
    private javax.swing.JButton bttnDependencias;
    private javax.swing.JButton bttnExpediente;
    private javax.swing.JButton bttnPrincipal;
    private javax.swing.JButton bttnSeguimiento;
    public static javax.swing.JPanel content;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
