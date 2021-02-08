/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.controlMaestrosCircularDoble;
import gestorArchivos.gestorMaestros;
import javax.swing.table.DefaultTableModel;
import controlador.controlMaestrosCircularDoble.Nodo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import modelo.maestro;

/**
 *
 * @author ferna
 */
public class frmMaestros extends javax.swing.JFrame {

    public final String ruta = System.getProperties().getProperty("user.dir");
    DefaultTableModel miModelo;
    String[] cabecera = {"N°", "Nombre", "Apellido", "Direccion", "Cedula", "Telefono", "Cargo", "Sueldo", "Carrera", "Materia"};
    String[][] data = {};
    int num = 0;
    gestorMaestros gestor = new gestorMaestros();
    String FileName = "maestros.bin";
    controlMaestrosCircularDoble lista = new controlMaestrosCircularDoble();
    Nodo nodo;

    /**
     * Creates new form frmMaestros
     */
    public frmMaestros() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("REGISTRO DE MAESTROS");
        this.setResizable(false);
        gestor.AbrirArchivo(FileName, lista);
        miModelo = new DefaultTableModel(data, cabecera);
        tablaMaestros.setModel(miModelo);
        ver_datos(1);
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(ruta + "//carrera.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                cboAsignarCarrera.addItem(linea);
            }
        } catch (IOException e) {
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e2) {
            }
        }

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(ruta + "//materias.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                cbomateria.addItem(linea);
            }
        } catch (IOException e) {
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e2) {
            }
        }

    }

    void limpiar_entradas() {
        txtnombre.setText("");
        txtapellido.setText("");
        txtcedula.setText("");
        txtdireccion.setText("");
        txttelefono.setText("");
        txtcodigo.setText("");
        txtsueldo.setText("");
        txtnombre.requestFocus();
    }

    void vaciar_tabla() {
        int filas = tablaMaestros.getRowCount();
        for (int i = 0; i < filas; i++) {
            miModelo.removeRow(0);
        }
    }

    void ver_datos(int ind) {
        String nom, ape1, direc, rol, carrera, mater, sueld;
        int cedu, codig, telef;
        switch (ind) {
            case 1: {
                Nodo aux = lista.lc;
                vaciar_tabla();
                num = 0;
                while (aux != null) {
                    codig = aux.pro.getCodigoP();
                    nom = aux.pro.getNombre();
                    ape1 = aux.pro.getApellido();
                    direc = aux.pro.getDireccion();
                    cedu = aux.pro.getCedula();
                    telef = aux.pro.getTelefono();
                    sueld = aux.pro.getSueldo();
                    rol = aux.pro.getRol();
                    carrera = aux.pro.getCarrera();
                    mater = aux.pro.getMateria();
                    num++;
                    Object[] fila = {codig, nom, ape1, direc, cedu, telef, sueld, rol, carrera, mater};
                    miModelo.addRow(fila);
                    aux = aux.enlace;
                }
            }
            break;

            case 2: {
                Nodo aux = lista.lc1;
                vaciar_tabla();
                num = 0;
                while (aux != null) {
                    codig = aux.pro.getCodigoP();
                    nom = aux.pro.getNombre();
                    ape1 = aux.pro.getApellido();
                    direc = aux.pro.getDireccion();
                    cedu = aux.pro.getCedula();
                    telef = aux.pro.getTelefono();
                    sueld = aux.pro.getSueldo();
                    rol = aux.pro.getRol();
                    carrera = aux.pro.getCarrera();
                    mater = aux.pro.getMateria();
                    //aqui falta carrera
                    num++;
                    Object[] fila = {codig, nom, ape1, direc, cedu, telef, sueld, rol, carrera, mater};
                    miModelo.addRow(fila);
                    aux = aux.enlace1;
                }
            }
            break;
        }
    }

    Nodo buscar(Nodo inicio, String nom) {
        Nodo pos = inicio;
        while (pos != null && !nom.equalsIgnoreCase(pos.pro.nombre)) {
            pos = pos.enlace;
        }
        return pos;
    }

    void eliminar() {
        Nodo actual;
        boolean encontrado = false;
        actual = lista.lc;
        while ((actual != null) && (!encontrado)) {
            encontrado = actual.pro.getNombre().equalsIgnoreCase(txtnombre.getText().trim());
            if (!encontrado) {
                actual = actual.enlace;
            }
        }
        if (actual != null) {
            if (actual == lista.lc) {
                lista.lc = actual.enlace;
                if (actual.enlace != null) {
                    actual.enlace.enlace1 = null;
                }
            } else if (actual.enlace != null) {
                actual.enlace1.enlace = actual.enlace;
                actual.enlace.enlace1 = actual.enlace1;
            } else {
                actual.enlace1.enlace = null;
                lista.lc1 = actual.enlace1;
            }
            actual = null;
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMaestros = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdireccion = new javax.swing.JTextArea();
        txtcedula = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        txtcodigo = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbocargo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cboAsignarCarrera = new javax.swing.JComboBox<>();
        btnguardar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btnatras = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbomateria = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtsueldo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 0));

        jLabel1.setFont(new java.awt.Font("Eras Light ITC", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("REGISTRO DE MAESTROS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tablaMaestros.setBackground(new java.awt.Color(255, 255, 255));
        tablaMaestros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CODIGO", "NOMBRE", "APELLIDO", "CEDULA", "TELEFONO", "DIRECCION", "CARGO", "SUELDO", "CARRERA", "MATERIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaMaestros);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Eras Light ITC", 3, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Eras Light ITC", 3, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("CODIGO MAESTRO:");

        jLabel3.setFont(new java.awt.Font("Eras Light ITC", 3, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("NOMBRE:");

        jLabel4.setFont(new java.awt.Font("Eras Light ITC", 3, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("APELLIDO:");

        jLabel5.setFont(new java.awt.Font("Eras Light ITC", 3, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("CEDULA:");

        jLabel6.setFont(new java.awt.Font("Eras Light ITC", 3, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("TELEFONO:");

        jLabel7.setFont(new java.awt.Font("Eras Light ITC", 3, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("DIRECCION:");

        txtdireccion.setBackground(new java.awt.Color(255, 255, 255));
        txtdireccion.setColumns(20);
        txtdireccion.setForeground(new java.awt.Color(0, 0, 0));
        txtdireccion.setRows(5);
        jScrollPane2.setViewportView(txtdireccion);

        txtcedula.setBackground(new java.awt.Color(255, 255, 255));
        txtcedula.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        txtcedula.setForeground(new java.awt.Color(0, 0, 0));

        txttelefono.setBackground(new java.awt.Color(255, 255, 255));
        txttelefono.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        txttelefono.setForeground(new java.awt.Color(0, 0, 0));

        txtcodigo.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigo.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        txtcodigo.setForeground(new java.awt.Color(0, 0, 0));

        txtnombre.setBackground(new java.awt.Color(255, 255, 255));
        txtnombre.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        txtnombre.setForeground(new java.awt.Color(0, 0, 0));

        txtapellido.setBackground(new java.awt.Color(255, 255, 255));
        txtapellido.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        txtapellido.setForeground(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Eras Light ITC", 3, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("CARGO:");

        cbocargo.setBackground(new java.awt.Color(255, 255, 255));
        cbocargo.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        cbocargo.setForeground(new java.awt.Color(0, 102, 51));
        cbocargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Profesor", "Administrador Carrera", "Administrador Materia" }));

        jLabel9.setFont(new java.awt.Font("Eras Light ITC", 3, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("ASIGNAR:");

        cboAsignarCarrera.setBackground(new java.awt.Color(255, 255, 255));
        cboAsignarCarrera.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        cboAsignarCarrera.setForeground(new java.awt.Color(0, 102, 51));

        btnguardar.setBackground(new java.awt.Color(255, 255, 255));
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/addM.png"))); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btneliminar.setBackground(new java.awt.Color(255, 255, 255));
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/removeM.png"))); // NOI18N
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnbuscar.setBackground(new java.awt.Color(255, 255, 255));
        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search_102938.png"))); // NOI18N

        btnatras.setBackground(new java.awt.Color(255, 255, 255));
        btnatras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrowback_257.png"))); // NOI18N
        btnatras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnatrasActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Eras Light ITC", 3, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("MATERIA:");

        cbomateria.setBackground(new java.awt.Color(255, 255, 255));
        cbomateria.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        cbomateria.setForeground(new java.awt.Color(153, 0, 0));

        jLabel11.setFont(new java.awt.Font("Eras Light ITC", 3, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("SUELDO:");

        txtsueldo.setBackground(new java.awt.Color(255, 255, 255));
        txtsueldo.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnguardar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtcodigo)
                            .addComponent(txtnombre)
                            .addComponent(txtapellido, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(txtsueldo))))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtcedula)
                            .addComponent(txttelefono)
                            .addComponent(cbocargo, 0, 210, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2)
                                .addComponent(cboAsignarCarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(cbomateria, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(211, 211, 211)
                        .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnatras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(cbocargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(cboAsignarCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbomateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtsueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnatras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnatrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnatrasActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        frmPrincipal ty = new frmPrincipal();
        ty.setVisible(true);
        ty.toFront();
    }//GEN-LAST:event_btnatrasActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        int codig = Integer.parseInt(txtcodigo.getText().toString());
        String nom = txtnombre.getText().toUpperCase();
        String ape1 = txtapellido.getText().toUpperCase();
        int cedula = Integer.parseInt(txtcedula.getText().toString());
        int telef = Integer.parseInt(txttelefono.getText());
        String direc = txtdireccion.getText().toUpperCase();
        String suel = txtsueldo.getText().toString();
        String rol = cbocargo.getSelectedItem().toString();
        String carre = cboAsignarCarrera.getSelectedItem().toString();
        String matr = cbomateria.getSelectedItem().toString();
        lista.lc = lista.inserta_final(lista.lc, new maestro(new Object[]{codig, nom, ape1, cedula, telef, direc, suel, rol, carre, matr}));
        limpiar_entradas();
        ver_datos(1);
        gestor.GrabarArchivo(FileName, lista);
        
        //AQUI SI MAME Y NO SE QUE HICE JAJA BUENO NO ME ACUERDO BROS
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
        gestor.GrabarArchivo(FileName, lista);
        limpiar_entradas();
        ver_datos(1);
        if (lista.lc == null) {
            JOptionPane.showMessageDialog(this, "La lista esta vacia");
        }


    }//GEN-LAST:event_btneliminarActionPerformed

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
            java.util.logging.Logger.getLogger(frmMaestros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMaestros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMaestros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMaestros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMaestros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnatras;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JComboBox<String> cboAsignarCarrera;
    private javax.swing.JComboBox<String> cbocargo;
    private javax.swing.JComboBox<String> cbomateria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaMaestros;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtcedula;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextArea txtdireccion;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtsueldo;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
