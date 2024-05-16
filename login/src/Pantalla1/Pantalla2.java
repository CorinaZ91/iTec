package Pantalla1;

//librerias
import DatosGN.detalleventa;
import DatosGN.merma;
import DatosGN.servicio;
import DatosGN.venta;
import Milogica.ControllerDetalleVenta;
import Milogica.ServiciosController;
import Milogica.VentaController;
import java.awt.Color;
import java.awt.Component;
import java.awt.PageAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Pantalla2 extends javax.swing.JFrame {

    //Reloj
    String hora, minutos, segundos, am_pm;
    Calendar calendario;
    Thread h1;

    int idActual;
    String NameActual;

    /////
    float totalVenta = 0;

    ServiciosController mControllerServicios = new ServiciosController();
    VentaController mControllerVentas = new VentaController();
    DefaultTableModel mTablaVenta = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return (column == 1 || column == 2);
        }
    };

    DefaultTableModel mTablaServicio = new DefaultTableModel();
    DefaultTableModel mTablaVentasRealizadas = new DefaultTableModel();

    JComboBox<String> opciones = new JComboBox<>();

    public Pantalla2() {

        opciones.getModel();
        opciones.addItem("Color");
        opciones.addItem("ByN");

        try {
            initComponents(); // Inicializar componentes de la GUI
            this.setVisible(true);
            runReloj();
            fecha();
            CargarIconos();
            cargarEncabezados();
            EncabezadosVentasRealizadas();
            msjTotal.setVisible(true);
            lblTotalVenta.setVisible(true);
            BtnCerrarventa.setVisible(true);

            setColumsServicios();

        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir durante la ejecución del código
            e.printStackTrace(); // Imprimir la traza de la excepción
        }

    }

    private boolean TableNoVacia(JTable Tabla) {
        return Tabla.getModel().getRowCount() > 0;
    }

    private void EncabezadosVentasRealizadas() {
        mTablaVentasRealizadas.addColumn("Fecha");
        mTablaVentasRealizadas.addColumn("No. venta");
        mTablaVentasRealizadas.addColumn("Usuario");
        mTablaVentasRealizadas.addColumn("Total");
    }

    private void calcularTotal() {
        msjTotal.setVisible(true);
        lblTotalVenta.setVisible(true);
        totalVenta = 0;
        int fila = TblVentas.getRowCount();

        for (int i = fila - 1; i >= 0; i--) {
            Object Precio = TblVentas.getValueAt(i, 3);
            Object cantidad = TblVentas.getValueAt(i, 2);
            float precio = Float.parseFloat(Precio.toString());
            int cantidadEntero = Integer.parseInt(cantidad.toString());
            precio = precio * cantidadEntero;

            totalVenta = totalVenta + precio;
        }
        String TotalVentaS = Float.toString(totalVenta);
        lblTotalVenta.setText(TotalVentaS);
    }

    void setId(int id) {
        idActual = id;
    }

    void setNameCorrect(String NameCorrect) {
        NameActual = NameCorrect;
    }

    private void fecha() {
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaCString = fecha.format(formato);
        Fecha.setText(fechaCString);

    }

    private void CargarIconos() {
        setImage(jLabelLogo, "/icons/iTec.jpg");
        setImage(Lblimpretec, "/icons/impretec.png");
        setImage(labelSalir, "/icons/usuario.png");
        setImage(lblCopia, "/icons/logocopias.png");
        setImage(lblImpresion, "/icons/logoimpresora.png");
        setImage(lblMerma, "/icons/logomerma2.png");
        setImage(lblOtros, "/icons/anadir.png");
    }

    public void runReloj() {
        h1 = new Thread() {
            public void run() {
                try {
                    while (true) {
                        calcular();
                        Hora.setText(hora + ":" + minutos + ":" + segundos + " " + am_pm);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        h1.start();
    }

    public void calcular() {
        calendario = Calendar.getInstance(TimeZone.getDefault());
        Date fechaHoraActual = new Date();
        calendario.setTime(fechaHoraActual);
        am_pm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        if (am_pm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = (calendario.get(Calendar.HOUR_OF_DAY)) > 9 ? "" + (calendario.get(Calendar.HOUR_OF_DAY)) : "0" + (calendario.get(Calendar.HOUR_OF_DAY));
        }
        minutos = (calendario.get(Calendar.MINUTE)) > 9 ? "" + (calendario.get(Calendar.MINUTE)) : "0" + (calendario.get(Calendar.MINUTE));
        segundos = (calendario.get(Calendar.SECOND)) > 9 ? "" + (calendario.get(Calendar.SECOND)) : "0" + (calendario.get(Calendar.SECOND));

    }

    private void ColumnaSpinner() {
        TblVentas.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor());
    }

    private void ColumnaComboBox() {

        DefaultCellEditor comboBoxEditor = new DefaultCellEditor(opciones);
        TblVentas.getColumnModel().getColumn(1).setCellEditor(comboBoxEditor);
    }

    private void cargarEncabezados() {
        mTablaVenta.addColumn("Descripción");
        mTablaVenta.addColumn("Tipo");
        mTablaVenta.addColumn("Cantidad");
        mTablaVenta.addColumn("Precio");
        this.setLocationRelativeTo(null);

    }

    private void setImage(JLabel label, String ruta) {
        Icon logo = new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(label.getWidth(), label.getHeight(), 0));
        label.setIcon(logo);

    }

    private Icon getImage(String ruta, int w, int h) {
        Icon logo = new ImageIcon(new ImageIcon(getClass().getResource(ruta))
                .getImage().getScaledInstance(w, h, 0));
        return logo;
    }

    private void ClearServiciosTabla() {
        int fila = mTablaServicio.getRowCount();
        for (int i = fila - 1; i >= 0; i--) {
            mTablaServicio.removeRow(i);
        }
    }

    private void LoadServices() {
        ClearServiciosTabla();
        TblServicios.setDefaultRenderer(Object.class, new RenderTablaServicios());
        ArrayList<servicio> miServicios = mControllerServicios.getServices();
        Object Datos[] = new Object[4];
        for (int i = 0; i < miServicios.size(); i++) {
            servicio miServicio = miServicios.get(i);
            Datos[0] = miServicio.getId_servicio();
            Datos[1] = new JLabel(getImage("/icons/" + miServicio.getImgen(), 70, 70));
            Datos[2] = miServicio.getDescripcion();
            Datos[3] = miServicio.getPrecio();
            mTablaServicio.addRow(Datos);
        }

        TblServicios.setModel(mTablaServicio);
        TblServicios.setRowHeight(100);
        TblServicios.getColumnModel().getColumn(0).setPreferredWidth(60);
        TblServicios.getColumnModel().getColumn(1).setPreferredWidth(60);
        TblServicios.getColumnModel().getColumn(2).setPreferredWidth(60);
        TblServicios.getColumnModel().getColumn(3).setPreferredWidth(60);

    }

    private void setColumsServicios() {
        mTablaServicio.addColumn("Folio");
        mTablaServicio.addColumn("Logo");
        mTablaServicio.addColumn("Descripción");
        mTablaServicio.addColumn("Precio");
    }

    private void cambiarVista(JPanel panel) {
        calcularTotal();
        panel.setLocation(0, 0);
        panel.setSize(content.getWidth(), content.getHeight());
        //panel.setSize(1443,933);
        content.removeAll();
        content.add(panel);
        content.repaint();
        activarVista(panel);
    }

    private void activarVista(JPanel panel) {
        panel.setEnabled(true);
        panel.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Lblimpretec = new javax.swing.JLabel();
        lblImpresion = new javax.swing.JLabel();
        lblCopia = new javax.swing.JLabel();
        lblOtros = new javax.swing.JLabel();
        lblMerma = new javax.swing.JLabel();
        BtnCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        content = new javax.swing.JPanel();
        panelVenta = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblVentas = new javax.swing.JTable();
        panelCatalogo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblServicios = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        BtnVenderCatalogo = new javax.swing.JButton();
        panelBalances = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblVentas1 = new javax.swing.JTable();
        lblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        labelSalir = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        Hora = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblTotalVenta = new javax.swing.JLabel();
        msjTotal = new javax.swing.JLabel();
        BtnCerrarventa = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenu19 = new javax.swing.JMenu();
        jMenu21 = new javax.swing.JMenu();
        jMenu23 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();

        jMenu3.setText("jMenu3");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setFocusCycleRoot(false);
        setLocationByPlatform(true);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 74, 173));

        Lblimpretec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/f.jpg"))); // NOI18N
        Lblimpretec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Lblimpretec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblimpretecMouseClicked(evt);
            }
        });

        lblImpresion.setBackground(new java.awt.Color(255, 255, 255));
        lblImpresion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/f.jpg"))); // NOI18N
        lblImpresion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblImpresion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImpresionMouseClicked(evt);
            }
        });

        lblCopia.setBackground(new java.awt.Color(255, 255, 255));
        lblCopia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/f.jpg"))); // NOI18N
        lblCopia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCopia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCopiaMouseClicked(evt);
            }
        });

        lblOtros.setBackground(new java.awt.Color(255, 255, 255));
        lblOtros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/f.jpg"))); // NOI18N
        lblOtros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOtros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOtrosMouseClicked(evt);
            }
        });

        lblMerma.setBackground(new java.awt.Color(255, 255, 255));
        lblMerma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/f.jpg"))); // NOI18N
        lblMerma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMerma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMermaMouseClicked(evt);
            }
        });

        BtnCancelar.setLabel("Cancelar");
        BtnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCancelarMouseClicked(evt);
            }
        });
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        jButton1.setText("Eliminar artículo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lblimpretec, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMerma, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCopia, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(lblImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(85, 85, 85)
                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(Lblimpretec, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCopia, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMerma, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(271, 271, 271))
        );

        content.setBackground(new java.awt.Color(255, 102, 51));
        content.setToolTipText("");
        content.setMaximumSize(null);

        panelVenta.setBackground(new java.awt.Color(255, 255, 255));

        TblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción", "Tipo", "Cantidad", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblVentas.getTableHeader().setResizingAllowed(false);
        TblVentas.getTableHeader().setReorderingAllowed(false);
        TblVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblVentasMouseClicked(evt);
            }
        });
        TblVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TblVentasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(TblVentas);
        if (TblVentas.getColumnModel().getColumnCount() > 0) {
            TblVentas.getColumnModel().getColumn(0).setResizable(false);
            TblVentas.getColumnModel().getColumn(1).setResizable(false);
            TblVentas.getColumnModel().getColumn(2).setResizable(false);
            TblVentas.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout panelVentaLayout = new javax.swing.GroupLayout(panelVenta);
        panelVenta.setLayout(panelVentaLayout);
        panelVentaLayout.setHorizontalGroup(
            panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVentaLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1752, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panelVentaLayout.setVerticalGroup(
            panelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        panelCatalogo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pantalla1/Imagenes/Lupa (1).png"))); // NOI18N

        jTextField1.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField1.setText("Buscar");
        jTextField1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField1MousePressed(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        jLabel20.setText("Existencias");

        TblServicios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(TblServicios);

        jButton2.setText("Nuevo servicio");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        BtnVenderCatalogo.setText("Agregar a la venta");
        BtnVenderCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVenderCatalogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCatalogoLayout = new javax.swing.GroupLayout(panelCatalogo);
        panelCatalogo.setLayout(panelCatalogoLayout);
        panelCatalogoLayout.setHorizontalGroup(
            panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCatalogoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 812, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(panelCatalogoLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnVenderCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        panelCatalogoLayout.setVerticalGroup(
            panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCatalogoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(192, 192, 192)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(BtnVenderCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jButton2.getAccessibleContext().setAccessibleName("");

        panelBalances.setBackground(new java.awt.Color(255, 255, 255));

        TblVentas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "T1", "T2", "T3", "T4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblVentas1.getTableHeader().setResizingAllowed(false);
        TblVentas1.getTableHeader().setReorderingAllowed(false);
        TblVentas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblVentas1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TblVentas1);
        if (TblVentas1.getColumnModel().getColumnCount() > 0) {
            TblVentas1.getColumnModel().getColumn(0).setResizable(false);
            TblVentas1.getColumnModel().getColumn(1).setResizable(false);
            TblVentas1.getColumnModel().getColumn(2).setResizable(false);
            TblVentas1.getColumnModel().getColumn(3).setResizable(false);
        }

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitulo.setText("Titulo");

        javax.swing.GroupLayout panelBalancesLayout = new javax.swing.GroupLayout(panelBalances);
        panelBalances.setLayout(panelBalancesLayout);
        panelBalancesLayout.setHorizontalGroup(
            panelBalancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(panelBalancesLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1667, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelBalancesLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1075, Short.MAX_VALUE))
        );
        panelBalancesLayout.setVerticalGroup(
            panelBalancesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBalancesLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelBalances, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(247, Short.MAX_VALUE)))
            .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelBalances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(120, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        labelSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/f.jpg"))); // NOI18N
        labelSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSalirMouseClicked(evt);
            }
        });

        jLabelLogo.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/f.jpg"))); // NOI18N

        Fecha.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Fecha.setText("Fecha");

        Hora.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Hora.setText("Hora");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 938, Short.MAX_VALUE)
                .addComponent(labelSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Fecha)
                            .addComponent(Hora))))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalVenta.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTotalVenta.setText("0.0");

        msjTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        msjTotal.setText("Total de la venta $");

        BtnCerrarventa.setText("Realizar venta");
        BtnCerrarventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCerrarventaMouseClicked(evt);
            }
        });
        BtnCerrarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCerrarventaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(478, 478, 478)
                .addComponent(BtnCerrarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 681, Short.MAX_VALUE)
                .addComponent(msjTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(407, 407, 407))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(msjTotal)
                            .addComponent(lblTotalVenta))
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(BtnCerrarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N

        jMenu1.setText("Consultar");
        jMenu1.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });

        jMenu4.setText("Consultar Venta diaria");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu4);

        jMenu5.setText("Total de copias monocromáticas");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu5);

        jMenu7.setText("Total copias a color");
        jMenu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu7MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu7);

        jMenu11.setText("Total impresiones monocromáticas");
        jMenu11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu11MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu11);

        jMenu19.setText("Total de Impresiones a color");
        jMenu19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu19MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu19);

        jMenu21.setText("Total merma");
        jMenu21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu21MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu21);

        jMenu23.setText("Total de hojas gastadas");
        jMenu23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu23MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu23);

        jMenu9.setText("Cierre de caja");
        jMenu1.add(jMenu9);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);

        jMenu8.setText("Registar Productos");
        jMenu8.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu8MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu8);
        jMenuBar1.add(jMenu6);

        jMenu10.setText("Catálogo");
        jMenu10.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jMenu10.setFocusable(false);
        jMenu10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu10MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu10);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 138, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 913, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSalirMouseClicked
        Panusuario nuevaVentana = new Panusuario();
        nuevaVentana.setId(idActual);
        nuevaVentana.setName(NameActual);
        // Configurar la nueva ventana
        nuevaVentana.setSize(300, 200);
        nuevaVentana.setLocationRelativeTo(null);
        nuevaVentana.setDefaultCloseOperation(Pantalla2.DISPOSE_ON_CLOSE); // Esto asegura que solo se cierre la nueva ventana al hacer clic en su botón de cerrar

        // Hacer visible la nueva ventana
        nuevaVentana.setVisible(true);

    }//GEN-LAST:event_labelSalirMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked


    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked

        lblTitulo.setText("Ventas diarias");
        cambiarVista(panelBalances);
        msjTotal.setVisible(false);
        lblTotalVenta.setVisible(false);
        BtnCerrarventa.setVisible(false);
        ArrayList<VentasRealizadas> misVentas = mControllerVentas.getVentasRealizada();
        Object Datos[] = new Object[4];

        for (int i = 0; i < misVentas.size(); i++) {
            Datos[0] = misVentas.get(i).getFecha();
            Datos[1] = misVentas.get(i).getId_venta();
            Datos[2] = misVentas.get(i).getNombre_usuario();
            Datos[3] = misVentas.get(i).getTotal();
            mTablaVentasRealizadas.addRow(Datos);
        }

        // Configurar el modelo de la tabla y ajustar las preferencias de las columnas
        TblVentas1.setModel(mTablaVentasRealizadas);
        TblVentas1.setRowHeight(60);
        TblVentas1.getColumnModel().getColumn(0).setPreferredWidth(60);
        TblVentas1.getColumnModel().getColumn(1).setPreferredWidth(60);
        TblVentas1.getColumnModel().getColumn(2).setPreferredWidth(60);
        TblVentas1.getColumnModel().getColumn(3).setPreferredWidth(60);


    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        lblTitulo.setText("Total de copias monocromaticas ");
        cambiarVista(panelBalances);
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu7MouseClicked

    }//GEN-LAST:event_jMenu7MouseClicked

    private void jMenu11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu11MouseClicked

    }//GEN-LAST:event_jMenu11MouseClicked

    private void jMenu19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu19MouseClicked

    }//GEN-LAST:event_jMenu19MouseClicked

    private void jMenu21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu21MouseClicked

    }//GEN-LAST:event_jMenu21MouseClicked

    private void jMenu23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu23MouseClicked

    }//GEN-LAST:event_jMenu23MouseClicked

    private void jMenu8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MousePressed

    }//GEN-LAST:event_jMenu8MousePressed

    private void LblimpretecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblimpretecMouseClicked

        cambiarVista(panelVenta);
        msjTotal.setVisible(true);
        lblTotalVenta.setVisible(true);
        BtnCerrarventa.setVisible(true);
    }//GEN-LAST:event_LblimpretecMouseClicked

    private void lblCopiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCopiaMouseClicked

        cambiarVista(panelVenta);
        msjTotal.setVisible(true);
        lblTotalVenta.setVisible(true);
        BtnCerrarventa.setVisible(true);

        ArrayList<servicio> miServicio = mControllerServicios.getServices();
        servicio ser = miServicio.get(0);

        Object Datos[] = new Object[4];
        Datos[0] = ser.getDescripcion();
        Datos[2] = 1;
        Datos[3] = ser.getPrecio();

        // Agregar la fila a la tabla
        mTablaVenta.addRow(Datos);
        // Configurar el modelo de la tabla y ajustar las preferencias de las columnas
        TblVentas.setModel(mTablaVenta);
        TblVentas.setRowHeight(60);
        TblVentas.getColumnModel().getColumn(0).setPreferredWidth(60);
        TblVentas.getColumnModel().getColumn(1).setPreferredWidth(60);
        TblVentas.getColumnModel().getColumn(2).setPreferredWidth(60);
        TblVentas.getColumnModel().getColumn(3).setPreferredWidth(60);

        calcularTotal();


    }//GEN-LAST:event_lblCopiaMouseClicked

    private void TblVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblVentasMouseClicked
        calcularTotal();
        int filaSeleccionada = TblVentas.getSelectedRow();
        Object Folio = TblVentas.getValueAt(filaSeleccionada, 0);
        String fol = Folio.toString();
        if ("Copia".equals(fol) || "Impresion".equals(fol)) {
            ColumnaComboBox();
            ColumnaSpinner();
        }
    }//GEN-LAST:event_TblVentasMouseClicked

    private void lblImpresionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImpresionMouseClicked

        cambiarVista(panelVenta);
        msjTotal.setVisible(true);
        lblTotalVenta.setVisible(true);
        BtnCerrarventa.setVisible(true);

        ArrayList<servicio> miServicio = mControllerServicios.getServices();
        servicio ser = miServicio.get(1);

        Object Datos[] = new Object[4];
        Datos[0] = ser.getDescripcion();
        Datos[2] = 1;
        Datos[3] = ser.getPrecio();
        // Agregar la fila a la tabla
        mTablaVenta.addRow(Datos);

        // Configurar el modelo de la tabla y ajustar las preferencias de las columnas
        TblVentas.setModel(mTablaVenta);
        TblVentas.setRowHeight(60);
        TblVentas.getColumnModel().getColumn(0).setPreferredWidth(60);
        TblVentas.getColumnModel().getColumn(1).setPreferredWidth(60);
        TblVentas.getColumnModel().getColumn(2).setPreferredWidth(60);
        TblVentas.getColumnModel().getColumn(3).setPreferredWidth(60);

        calcularTotal();
    }//GEN-LAST:event_lblImpresionMouseClicked

    private void lblOtrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOtrosMouseClicked
        LoadServices();
        cambiarVista(panelCatalogo);
        msjTotal.setVisible(false);
        lblTotalVenta.setVisible(false);
        BtnCerrarventa.setVisible(false);
    }//GEN-LAST:event_lblOtrosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int fila = TblVentas.getSelectedRow();
        if (fila >= 0) {
            mTablaVenta.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(null, "No has seleccionado ningún articulo");
        }

        calcularTotal();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MousePressed
        jTextField1.setText(" ");
        jTextField1.setForeground(Color.BLACK);
    }//GEN-LAST:event_jTextField1MousePressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed


    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCancelarMouseClicked
        int fila = TblVentas.getRowCount();
        if (fila > 0) {
            for (int i = fila - 1; i >= 0; i--) {
                mTablaVenta.removeRow(i);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No has agregado ningún articulo");
        }

        calcularTotal();
    }//GEN-LAST:event_BtnCancelarMouseClicked

    private void TblVentas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblVentas1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TblVentas1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        NuevoServicio mServicio = new NuevoServicio();
        mServicio.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenu10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu10MouseClicked
        // TODO add your handling code here:
        LoadServices();
        cambiarVista(panelCatalogo);
    }//GEN-LAST:event_jMenu10MouseClicked

    private void lblMermaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMermaMouseClicked
        // TODO add your handling code here
        Merma nuevaVentana = new Merma();

        // Configurar la nueva ventana
        nuevaVentana.setLocationRelativeTo(null);
        nuevaVentana.setDefaultCloseOperation(Pantalla2.DISPOSE_ON_CLOSE); // Esto asegura que solo se cierre la nueva ventana al hacer clic en su botón de cerrar

        // Hacer visible la nueva ventana
        nuevaVentana.setVisible(true);
    }//GEN-LAST:event_lblMermaMouseClicked

    private void BtnCerrarventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCerrarventaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCerrarventaMouseClicked

    private void BtnCerrarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCerrarventaActionPerformed
        // TODO add your handling code here:
        if (TableNoVacia(TblVentas)) {
            calcularTotal();
            VentaController mVentaController = new VentaController();
            venta mVenta = new venta();

            mVenta.setFecha(LocalDate.now());
            mVenta.setId_usuario(idActual);

            String totalVentaText = lblTotalVenta.getText();
            float totalVentaFloat = Float.parseFloat(totalVentaText);
            mVenta.setTotal(totalVentaFloat);

            int idNuevo = mVentaController.Agregar(mVenta);

            if (idNuevo > 0) {
                ControllerDetalleVenta mDetallesController = new ControllerDetalleVenta();

                int totalServicios = mTablaVenta.getRowCount();
                detalleventa mDetalleVenta = null;
                Boolean TodoGuardado = true;
                for (int i = 0; i < totalServicios; i++) {
                    mDetalleVenta = new detalleventa();
                    mDetalleVenta.setId_venta(idNuevo);
                    //mDetalleVenta.setId_merma(1);

                    int Cantidad = Integer.parseInt(String.valueOf(mTablaVenta.getValueAt(i, 2)));
                    mDetalleVenta.setCantidad(Cantidad);
                    Float Precio = Float.parseFloat(String.valueOf(mTablaVenta.getValueAt(i, 3)));
                    mDetalleVenta.setPrecio(Precio);
                    int id_nuevoDetalles = mDetallesController.Agregar(mDetalleVenta);

                    TodoGuardado = id_nuevoDetalles > 0;

                }
                if (TodoGuardado) {
                    JOptionPane.showMessageDialog(null, "Venta registrada");
                    int fila = TblVentas.getRowCount();
                    if (fila > 0) {
                        for (int i = fila - 1; i >= 0; i--) {
                            mTablaVenta.removeRow(i);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se registraron todos los detalles");

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar");
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se han agregado servicios a la venta");

            }
        }

    }//GEN-LAST:event_BtnCerrarventaActionPerformed

    private void BtnVenderCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVenderCatalogoActionPerformed
        int filaSeleccionada = TblServicios.getSelectedRow();
        msjTotal.setVisible(true);
        lblTotalVenta.setVisible(true);
        BtnCerrarventa.setVisible(true);

        if (filaSeleccionada != -1) {
            Object datoColumna0 = TblServicios.getValueAt(filaSeleccionada, 0);
            Object datoColumna2 = TblServicios.getValueAt(filaSeleccionada, 2);
            Object datoColumna3 = TblServicios.getValueAt(filaSeleccionada, 3);

            int variable0 = Integer.parseInt(datoColumna0.toString());
            String variable2 = datoColumna2.toString();
            float variable3 = Float.parseFloat(datoColumna3.toString());

            cambiarVista(panelVenta);

            Object Datos[] = new Object[4];
            Datos[0] = variable2;
            Datos[2] = 1;
            Datos[3] = variable3;
            // Agregar la fila a la tabla
            mTablaVenta.addRow(Datos);

            // Configurar el modelo de la tabla y ajustar las preferencias de las columnas
            TblVentas.setModel(mTablaVenta);
            TblVentas.setRowHeight(60);
            TblVentas.getColumnModel().getColumn(0).setPreferredWidth(60);
            TblVentas.getColumnModel().getColumn(1).setPreferredWidth(60);
            TblVentas.getColumnModel().getColumn(2).setPreferredWidth(60);
            TblVentas.getColumnModel().getColumn(3).setPreferredWidth(60);
            calcularTotal();

        } else {
            // No se ha seleccionado ninguna fila
            JOptionPane.showMessageDialog(null, "No has seleccionado ningun servicio");
        }
    }//GEN-LAST:event_BtnVenderCatalogoActionPerformed

    private void TblVentasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TblVentasKeyPressed
        // TODO add your handling code here:
        calcularTotal();
    }//GEN-LAST:event_TblVentasKeyPressed

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
            java.util.logging.Logger.getLogger(Pantalla2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnCerrarventa;
    private javax.swing.JButton BtnVenderCatalogo;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel Hora;
    private javax.swing.JLabel Lblimpretec;
    private javax.swing.JTable TblServicios;
    private javax.swing.JTable TblVentas;
    private javax.swing.JTable TblVentas1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel content;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu21;
    private javax.swing.JMenu jMenu23;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelSalir;
    private javax.swing.JLabel lblCopia;
    private javax.swing.JLabel lblImpresion;
    private javax.swing.JLabel lblMerma;
    private javax.swing.JLabel lblOtros;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotalVenta;
    private javax.swing.JLabel msjTotal;
    private javax.swing.JPanel panelBalances;
    private javax.swing.JPanel panelCatalogo;
    private javax.swing.JPanel panelVenta;
    // End of variables declaration//GEN-END:variables

}
