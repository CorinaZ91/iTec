/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantalla1;

import static Conexion.ConfigBD.Usuario;
import DatosGN.Usuario;
import Milogica.UsuarioController;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
<<<<<<< Updated upstream

=======
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
>>>>>>> Stashed changes

/**
 *
 * @author Taller
 */
public class Login extends javax.swing.JFrame {

    int xMouse, yMouse;
<<<<<<< Updated upstream
    
        
    public Login() {
        
        initComponents();
        
=======
    private Point mPoint;
    UsuarioController mControllerUsuario = new UsuarioController();
    int id;
    String NameCorrect;

    public Login() {

        initComponents();
        this.setLocationRelativeTo(null);

>>>>>>> Stashed changes
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void ValidarUsuario(String nombre, String contrasenia) {

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        City = new javax.swing.JLabel();
        inisio = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        CONTRASEÑA = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        PassInput = new javax.swing.JPasswordField();
        NameInput = new javax.swing.JTextField();
        eNTRAR1 = new javax.swing.JPanel();
        ENTRAR1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        eNTRAR2 = new javax.swing.JPanel();
        ENTRAR = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
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

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pantalla1/Imagenes/Logo impretec.png"))); // NOI18N
        bg.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 170, 160));

        City.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pantalla1/Imagenes/city.png"))); // NOI18N
        bg.add(City, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 290, 500));

        inisio.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        inisio.setText("INICIAR SESIÓN");
        inisio.setToolTipText("");
        bg.add(inisio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 250, 40));

        usuario.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        usuario.setText("Usuario");
        bg.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 250, 10));

        CONTRASEÑA.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        CONTRASEÑA.setText("Contraseña");
        CONTRASEÑA.setName(""); // NOI18N
        bg.add(CONTRASEÑA, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        bg.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 250, -1));

        PassInput.setForeground(new java.awt.Color(204, 204, 204));
        PassInput.setText("******");
        PassInput.setBorder(null);
        PassInput.setCaretColor(new java.awt.Color(204, 204, 204));
        PassInput.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        PassInput.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        PassInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PassInputMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PassInputMousePressed(evt);
            }
        });
        bg.add(PassInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 190, -1));

        NameInput.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        NameInput.setForeground(new java.awt.Color(204, 204, 204));
        NameInput.setText("Ingrese su nombre de usuario");
        NameInput.setBorder(null);
        NameInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NameInputMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NameInputMousePressed(evt);
            }
        });
        bg.add(NameInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 240, -1));

        eNTRAR1.setBackground(new java.awt.Color(0, 74, 173));
        eNTRAR1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eNTRAR1MouseClicked(evt);
            }
        });

        ENTRAR1.setFont(new java.awt.Font("Roboto Black", 1, 12)); // NOI18N
        ENTRAR1.setForeground(new java.awt.Color(255, 255, 255));
        ENTRAR1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ENTRAR1.setText("REGISTRO");
        ENTRAR1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ENTRAR1.setVerifyInputWhenFocusTarget(false);
        ENTRAR1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ENTRAR1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout eNTRAR1Layout = new javax.swing.GroupLayout(eNTRAR1);
        eNTRAR1.setLayout(eNTRAR1Layout);
        eNTRAR1Layout.setHorizontalGroup(
            eNTRAR1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< Updated upstream
            .addGroup(eNTRAR1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ENTRAR1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
=======
            .addComponent(ENTRAR1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
>>>>>>> Stashed changes
        );
        eNTRAR1Layout.setVerticalGroup(
            eNTRAR1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eNTRAR1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ENTRAR1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg.add(eNTRAR1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 120, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel1MouseDragged(evt);
            }
        });
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 748, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 30));

        eNTRAR2.setBackground(new java.awt.Color(0, 74, 173));

        ENTRAR.setFont(new java.awt.Font("Roboto Black", 1, 12)); // NOI18N
        ENTRAR.setForeground(new java.awt.Color(255, 255, 255));
        ENTRAR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ENTRAR.setText("ENTRAR");
        ENTRAR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ENTRAR.setVerifyInputWhenFocusTarget(false);
        ENTRAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ENTRARMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout eNTRAR2Layout = new javax.swing.GroupLayout(eNTRAR2);
        eNTRAR2.setLayout(eNTRAR2Layout);
        eNTRAR2Layout.setHorizontalGroup(
            eNTRAR2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eNTRAR2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ENTRAR, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        eNTRAR2Layout.setVerticalGroup(
            eNTRAR2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eNTRAR2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ENTRAR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg.add(eNTRAR2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 120, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed

        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged

    }//GEN-LAST:event_jLabel1MouseDragged

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int CurrentX = this.getLocation().x;
        int CurrentY = this.getLocation().y;
        // Obtener el movimiento del del JFrame  en X, Y
        int MoveX = (CurrentX + evt.getX()) - (CurrentX + mPoint.x);
        int MoveY = (CurrentY + evt.getY()) - (CurrentY + mPoint.y);

        // Calcular las nuevas posisiones 
        int x = CurrentX + MoveX;
        int y = CurrentY + MoveY;

        // Asignar las posisiones al JFrame para generar el movimiento
        this.setLocation(x, y);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void NameInputMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NameInputMousePressed
        NameInput.setText("Ingrese su nombre de usuario");
        NameInput.setForeground(Color.GRAY);

        NameInput.setText("");
        NameInput.setForeground(Color.BLACK);
        PassInput.setText("*******");
        PassInput.setForeground(Color.GRAY);

<<<<<<< Updated upstream
=======
    }//GEN-LAST:event_NameInputMousePressed

    private void PassInputMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PassInputMousePressed
        if (PassInput.getText().isEmpty()) {
            PassInput.setText("Ingrese su contraseña");
            PassInput.setForeground(Color.GRAY);
        }
        // No establecer el texto del jTextField1 en blanco aquí
        PassInput.setText("");
        PassInput.setForeground(Color.BLACK);
    }//GEN-LAST:event_PassInputMousePressed

>>>>>>> Stashed changes
    private void ENTRAR1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ENTRAR1MouseClicked
// Abre una nueva ventana de registro
        this.dispose();
        RegistroForm registroForm = new RegistroForm();
        registroForm.setVisible(true);

    }//GEN-LAST:event_ENTRAR1MouseClicked

    private void ENTRARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ENTRARMouseClicked
<<<<<<< Updated upstream
         Pantalla2 pantalla2 = new Pantalla2();
    pantalla2.setVisible(true);
    }//GEN-LAST:event_ENTRARMouseClicked

    private void eNTRAR1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eNTRAR1MouseClicked
       
    }//GEN-LAST:event_eNTRAR1MouseClicked

=======
        String passCorrect, pass, name;
        boolean continuar = false;
        ArrayList<Usuario> mUsuarios = mControllerUsuario.getUsuarios();
        for (int i = 0; i < mUsuarios.size(); i++) {
            Usuario mUsuario = mUsuarios.get(i);
            passCorrect = mUsuario.getConstrasenia();
            NameCorrect = mUsuario.getNombre_usuario();
            id = mUsuario.getId_usuario();

            pass = PassInput.getText();
            name = NameInput.getText();
            

            if (passCorrect.equals(pass) && NameCorrect.equals(name)) {
                continuar = true;
                break;
            } else {
                continuar = false;
            }

        }
        if (continuar) {
            dispose();
            
            Pantalla2 pantalla2 = new Pantalla2();
            pantalla2.setId(id);
            pantalla2.setNameCorrect(NameCorrect);
            pantalla2.setExtendedState(JFrame.MAXIMIZED_BOTH);
            pantalla2.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");

        }

    }//GEN-LAST:event_ENTRARMouseClicked

    private void eNTRAR1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eNTRAR1MouseClicked

    }//GEN-LAST:event_eNTRAR1MouseClicked

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:

        mPoint = evt.getPoint(); // Obtener el pundo de partida del movimiento
        getComponentAt(mPoint);

    }//GEN-LAST:event_jPanel1MousePressed

    private void bgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMousePressed
        mPoint = evt.getPoint(); // Obtener el pundo de partida del movimiento
        getComponentAt(mPoint);
    }//GEN-LAST:event_bgMousePressed

    private void bgMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMouseDragged
        int CurrentX = this.getLocation().x;
        int CurrentY = this.getLocation().y;
        // Obtener el movimiento del del JFrame  en X, Y
        int MoveX = (CurrentX + evt.getX()) - (CurrentX + mPoint.x);
        int MoveY = (CurrentY + evt.getY()) - (CurrentY + mPoint.y);

        // Calcular las nuevas posisiones 
        int x = CurrentX + MoveX;
        int y = CurrentY + MoveY;

        // Asignar las posisiones al JFrame para generar el movimiento
        this.setLocation(x, y);    }//GEN-LAST:event_bgMouseDragged

    private void NameInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NameInputMouseClicked
        NameInput.setText("");
        NameInput.setForeground(Color.BLACK);
        PassInput.setText("*******");
        PassInput.setForeground(Color.GRAY);

    }//GEN-LAST:event_NameInputMouseClicked

    private void PassInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PassInputMouseClicked
        //PassInput.setText("");
        //NameInput.setText("Ingrese su nombre de usuario");
        //NameInput.setForeground(Color.GRAY);

    }//GEN-LAST:event_PassInputMouseClicked

>>>>>>> Stashed changes
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Crear una instancia del formulario de espera

        try {
            // Configurar el aspecto y la sensación del UI
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Crear una instancia del formulario de espera
        PantallaEspera pantallaEspera = new PantallaEspera();
        // Mostrar el formulario de espera
        pantallaEspera.setVisible(true);

        // Simular una espera (puedes reemplazar esto con tu lógica real)
        try {
            Thread.sleep(1000); // 4500
        } catch (InterruptedException ex) {
            // Manejar la interrupción
        }

        // Cerrar el formulario de espera
        pantallaEspera.dispose();

        // Crear e iniciar la ventana de inicio de sesión
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CONTRASEÑA;
    private javax.swing.JLabel City;
    private javax.swing.JLabel ENTRAR;
    private javax.swing.JLabel ENTRAR1;
    private javax.swing.JLabel Logo;
    private javax.swing.JTextField NameInput;
    private javax.swing.JPasswordField PassInput;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel eNTRAR1;
    private javax.swing.JPanel eNTRAR2;
    private javax.swing.JLabel inisio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
}
