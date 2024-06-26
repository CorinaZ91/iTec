package Pantalla1;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ProgressBarUI;

public class PantallaEspera extends javax.swing.JFrame {

    private Color mTransaparent;
    private Color mColor;
    private Point mPoint;

    public PantallaEspera() {
        mTransaparent = new Color(0, 0, 0, 0);
        mColor = new Color(0, 74, 173);
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        setBackground(mTransaparent);

        // Pintar el fondo del JFrame y hacerlo transparente 
        ImagenFondoEspera mFondo = new ImagenFondoEspera(jPanel1, "/icons/shape.png");
        jPanel1.add(mFondo).repaint();
        jPanel1.setOpaque(false);
        jPanel1.setBorder(null);
        jPanel1.setBackground(mTransaparent);

        ImagenFondoEspera mSalir = new ImagenFondoEspera(pnlCerrar, "/icons/x.png");
        pnlCerrar.add(mSalir).repaint();
        pnlCerrar.setOpaque(false);
        pnlCerrar.setBorder(null);
        pnlCerrar.setBackground(mTransaparent);

        ProgressBarInicado();
    }

    private void ProgressBarInicado() {
        // Iniciar el progress bar a partir de un Timer
        
        //NO CAMBIA EL COLOR DE LA PROGRESS BAR
        Timer mTimer = new Timer(40, (ActionEvent e) -> {
            UIDefaults defaults = UIManager.getLookAndFeelDefaults();

            defaults.put("ProgressBar.Background", Color.red);

            SwingUtilities.invokeLater(() -> {
                pbCarga.setValue(pbCarga.getValue() + 1); // Valor del progess bar
                pbCarga.setStringPainted(true); // Activar texto dentro del Progress bar
                pbCarga.setString("Cargando... " + pbCarga.getValue() + "%"); // Texto que irá en el progress bar
            });

            
            

        });

        mTimer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlCerrar = new javax.swing.JPanel();
        pbCarga = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 65)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("iTEC");

        pnlCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlCerrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlCerrarLayout = new javax.swing.GroupLayout(pnlCerrar);
        pnlCerrar.setLayout(pnlCerrarLayout);
        pnlCerrarLayout.setHorizontalGroup(
            pnlCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );
        pnlCerrarLayout.setVerticalGroup(
            pnlCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        pbCarga.setForeground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pbCarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        mPoint = evt.getPoint(); // Obtener el pundo de partida del movimiento
        getComponentAt(mPoint);
    }//GEN-LAST:event_jPanel1MousePressed

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

    private void pnlCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_pnlCerrarMouseClicked

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
            java.util.logging.Logger.getLogger(PantallaEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaEspera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar pbCarga;
    private javax.swing.JPanel pnlCerrar;
    // End of variables declaration//GEN-END:variables
}
