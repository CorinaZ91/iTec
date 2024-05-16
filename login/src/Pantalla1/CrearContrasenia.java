/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantalla1;

import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import DatosGN.Datos_usuario;
import DatosGN.Usuario;
import DatosGN.servicio;
import Milogica.DetallesUsuarioController;
import Milogica.ServiciosController;
import Milogica.UsuarioController;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Taller
 */
public class CrearContrasenia extends javax.swing.JFrame {

    int xMouse, yMouse;
    private Point mPoint;
    String ruta;
    DetallesUsuarioController mcontrollerDetalles = new DetallesUsuarioController();
    int id_usuario;
    String NombreUsuario, contrasenia, nUsuarioSinEspacios;

    public CrearContrasenia() {
        initComponents();
        this.setLocationRelativeTo(null);

        ArrayList<Datos_usuario> MiUsuario = mcontrollerDetalles.getDetallesUsuarios();

        int id = MiUsuario.size();

        Datos_usuario user = MiUsuario.get(id - 1);

        Nombre.setText(user.getNombre().toString());
        NombreUsuario = user.getNombre() + user.getAp_paterno() + user.getId_detalles();
        nUsuarioSinEspacios = NombreUsuario.replaceAll("\\s", "");

        NameUsuario.setText(nUsuarioSinEspacios);

    }

    void setidGenerado(int idNuevo) {
        id_usuario = idNuevo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        bg = new javax.swing.JPanel();
        inisio = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NameUsuario = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        usuario3 = new javax.swing.JLabel();
        Contrasenia = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        btnRegistrar = new javax.swing.JButton();
        usuario2 = new javax.swing.JLabel();
        usuario4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bg.setForeground(new java.awt.Color(204, 204, 204));
        bg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bg.setFocusable(false);
        bg.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bgMouseDragged(evt);
            }
        });
        bg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bgMousePressed(evt);
            }
        });
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inisio.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        inisio.setForeground(new java.awt.Color(102, 153, 255));
        inisio.setText("Crea tu contraseña");
        inisio.setToolTipText("");
        bg.add(inisio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        Nombre.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        Nombre.setText("\"\"");
        bg.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 140, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 204));
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 250, 10));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("X");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel2MouseDragged(evt);
            }
        });
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 296, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pantalla1/Imagenes/Imagen de WhatsApp 2024-04-02 a las 12.21.20_9c6b1ea1.jpg"))); // NOI18N
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        NameUsuario.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        NameUsuario.setText("Nombre de usuario");
        bg.add(NameUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 250, 10));

        usuario3.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        usuario3.setText("Crea tu contraseña");
        bg.add(usuario3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 140, -1));

        Contrasenia.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        Contrasenia.setForeground(new java.awt.Color(204, 204, 204));
        Contrasenia.setText("Escribe tu contraseña");
        Contrasenia.setBorder(null);
        Contrasenia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ContraseniaFocusGained(evt);
            }
        });
        Contrasenia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ContraseniaMousePressed(evt);
            }
        });
        Contrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContraseniaActionPerformed(evt);
            }
        });
        Contrasenia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ContraseniaKeyPressed(evt);
            }
        });
        bg.add(Contrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 240, -1));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 250, 10));

        btnRegistrar.setText("Finalizar registro");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        bg.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 160, 60));

        usuario2.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        usuario2.setText("Bienvenido ");
        bg.add(usuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        usuario4.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        usuario4.setText("Tu nombre de usuario es:");
        bg.add(usuario4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bgMousePressed

    private void bgMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMouseDragged

    }//GEN-LAST:event_bgMouseDragged

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        UsuarioController mControllerUsuario = new UsuarioController();
        Usuario mUsuario = new Usuario();
        contrasenia = Contrasenia.getText();

        mUsuario.setNombre_usuario(nUsuarioSinEspacios);
        mUsuario.setConstrasenia(contrasenia);
        mUsuario.setId_detalles(id_usuario);

        if (mControllerUsuario.Agregar(mUsuario) > 0) {
            JOptionPane.showMessageDialog(null, "Registro exitoso!");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar");

        }
        Login cambioPantalla = new Login();
        this.dispose();
        cambioPantalla.setVisible(true);


    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void ContraseniaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ContraseniaKeyPressed
        // Obtener el código de la tecla presionada
        int key = evt.getKeyCode();

        // Verificar si se presionó la tecla Enter (código 10)
        if (key == java.awt.event.KeyEvent.VK_ENTER) {
            // Pasar al siguiente campo de texto
            Contrasenia.requestFocus();
        }
    }//GEN-LAST:event_ContraseniaKeyPressed

    private void ContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContraseniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ContraseniaActionPerformed

    private void ContraseniaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContraseniaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ContraseniaMousePressed

    private void ContraseniaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ContraseniaFocusGained
        Contrasenia.setText("");
        Contrasenia.setForeground(Color.BLACK);
    }//GEN-LAST:event_ContraseniaFocusGained

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed

    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged

    }//GEN-LAST:event_jPanel1MouseDragged

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed

    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseDragged

    }//GEN-LAST:event_jLabel2MouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrearContrasenia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearContrasenia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearContrasenia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearContrasenia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearContrasenia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Contrasenia;
    private javax.swing.JLabel NameUsuario;
    private javax.swing.JLabel Nombre;
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel inisio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel usuario2;
    private javax.swing.JLabel usuario3;
    private javax.swing.JLabel usuario4;
    // End of variables declaration//GEN-END:variables

}
