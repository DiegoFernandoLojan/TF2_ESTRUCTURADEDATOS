/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.controlAlumnoDoble;
import gestorArchivos.gestorAlumnos;
import javax.swing.table.DefaultTableModel;
import controlador.controlAlumnoDoble.Nodo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import modelo.alumno;

/**
 *
 * @author ferna
 */
public class frmAlumnos extends javax.swing.JFrame {

    public final String ruta = System.getProperties().getProperty("user.dir");
    DefaultTableModel Modelo;
    String[] cabecera = {"N°","CODIGO" ,"NOMBRE", "APELLIDO", "CEDULA", "TELEFONO", "DIRECCION", "CARRERA", "MATERIA 1", "MATERIA 2", "MATERIA 3"};
    String[][] data = {};
    int num = 0;
    gestorAlumnos gestor = new gestorAlumnos();
    String FileName = "alumnos.bin";
    controlAlumnoDoble lista = new controlAlumnoDoble();
    Nodo nodo;

    /**
     * Creates new form frmAlumnos
     */
    public frmAlumnos() {
        initComponents();
         this.setLocationRelativeTo(null);
        this.setTitle("REGISTRO DE ALUMNOS");
        this.setResizable(false);
        gestor.AbrirArchivo(FileName, lista);
        Modelo = new DefaultTableModel(data, cabecera);
        tablaEstudiantes.setModel(Modelo);

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
                cbocarrera.addItem(linea);
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
                cbomateria1.addItem(linea);
                cbomateria2.addItem(linea);
                cbomateria3.addItem(linea);
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

        limpiar_entradas();
        ver_datos(1);

    }

    void limpiar_entradas() {
        txtnombre.setText("");
        txtapellido.setText("");
        txtcedula.setText("");
        txtdireccion.setText("");
        txttelefono.setText("");
        txtnombre.requestFocus();
        txtcodigo.setText("");
    }

    void vaciar_tabla() {
        int filas = tablaEstudiantes.getRowCount();
        for (int i = 0; i < filas; i++) {
            Modelo.removeRow(0);
        }
    }

    void ver_datos(int ind) {
        String nom, ape, direc, carrer, mat1, mat2, mat3;
        int cedu, cod, tele;

        switch (ind) {
            case 1: {
                vaciar_tabla();
                Nodo aux = lista.ini;
                num = 0;
                while (aux != null) {
                    cod = aux.alumno.getCodigoP();
                    nom = aux.alumno.getNombre();
                    ape = aux.alumno.getApellido();
                    cedu = aux.alumno.getCedula();
                    tele = aux.alumno.getTelefono();
                    direc = aux.alumno.getDireccion();
                    carrer = aux.alumno.getCarrera();
                    mat1 = aux.alumno.getMateria1();
                    mat2 = aux.alumno.getMateria2();
                    mat3 = aux.alumno.getMateria3();
                    num++;
                    Object[] fila = {num, cod, nom, ape, cedu, tele, direc, carrer, mat1, mat2, mat3};
                    Modelo.addRow(fila);
                    aux = aux.sig;
                }
            }
            break;

            case 2: {
                vaciar_tabla();
                Nodo aux = lista.fin;
                num = 0;
                while (aux != null) {
                    cod = aux.alumno.getCodigoP();
                    nom = aux.alumno.getNombre();
                    ape = aux.alumno.getApellido();
                    cedu = aux.alumno.getCedula();
                    tele = aux.alumno.getTelefono();
                    direc = aux.alumno.getDireccion();
                    carrer = aux.alumno.getCarrera();
                    mat1 = aux.alumno.getMateria1();
                    mat2 = aux.alumno.getMateria2();
                    mat3 = aux.alumno.getMateria3();
                    num++;
                    Object[] fila = {num, cod, nom, ape, cedu, tele, direc, carrer, mat1, mat2, mat3};
                    Modelo.addRow(fila);
                    aux = aux.ant;
                }
            }
            break;
        }
    }

    Nodo buscar(Nodo inicio, String nom) {
        Nodo pos = inicio;
        while (pos != null && !nom.equalsIgnoreCase(pos.alumno.nombre)) {
            pos = pos.sig;
        }
        return pos;
    }

    Nodo buscar2(Nodo inicio, String ape1) {
        Nodo pos = inicio;
        while (pos != null && !ape1.equalsIgnoreCase(pos.alumno.apellido)) {
            pos = pos.sig;
        }
        return pos;
    }

    void eliminar() {
        Nodo actual;
        boolean encontrado = false;
        actual = lista.ini;
        while ((actual != null) && (!encontrado)) {
            encontrado = actual.alumno.nombre.equalsIgnoreCase(txtnombre.getText().trim());
            if (!encontrado) {
                actual = actual.sig;
            }
        }
        if (actual != null) {
            if (actual == lista.ini) {
                lista.ini = actual.sig;
                if (actual.sig != null) {
                    actual.sig.ant = null;
                }
            } else if (actual.sig != null) {
                actual.ant.sig = actual.sig;
                actual.sig.ant = actual.ant;
            } else {
                actual.ant.sig = null;
                lista.fin = actual.ant;
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
        tablaEstudiantes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        txtcedula = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        cbocarrera = new javax.swing.JComboBox<>();
        cbomateria1 = new javax.swing.JComboBox<>();
        cbomateria2 = new javax.swing.JComboBox<>();
        cbomateria3 = new javax.swing.JComboBox<>();
        btnagregar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btnatras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 51));

        jLabel1.setFont(new java.awt.Font("Eras Light ITC", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRO DE ESTUDIANTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tablaEstudiantes.setBackground(new java.awt.Color(255, 255, 255));
        tablaEstudiantes.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        tablaEstudiantes.setForeground(new java.awt.Color(0, 0, 0));
        tablaEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CODIGO", "NOMBRE", "APELLIDO", "CEDULA", "TELEFONO", "DIRECCION", "CARRERA", "MATERIA 1", "MATERIA 2", "MATERIA 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaEstudiantes);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Alumnos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Eras Light ITC", 3, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Eras Light ITC", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("CODIGO:");

        jLabel3.setFont(new java.awt.Font("Eras Light ITC", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("NOMBRE:");

        jLabel4.setFont(new java.awt.Font("Eras Light ITC", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("APELLIDO:");

        jLabel5.setFont(new java.awt.Font("Eras Light ITC", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("CEDULA:");

        txtcodigo.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigo.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        txtcodigo.setForeground(new java.awt.Color(0, 0, 0));

        txtnombre.setBackground(new java.awt.Color(255, 255, 255));
        txtnombre.setForeground(new java.awt.Color(0, 0, 0));

        txtapellido.setBackground(new java.awt.Color(255, 255, 255));
        txtapellido.setForeground(new java.awt.Color(0, 0, 0));

        txtcedula.setBackground(new java.awt.Color(255, 255, 255));
        txtcedula.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        txtcedula.setForeground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Eras Light ITC", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("TELEFONO:");

        jLabel7.setFont(new java.awt.Font("Eras Light ITC", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("DIRECCION:");

        jLabel8.setFont(new java.awt.Font("Eras Light ITC", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("CARRERA:");

        jLabel9.setFont(new java.awt.Font("Eras Light ITC", 3, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("MATERIA 1:");

        jLabel10.setFont(new java.awt.Font("Eras Light ITC", 3, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("MATERIA 2:");

        jLabel11.setFont(new java.awt.Font("Eras Light ITC", 3, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("MATERIA 3:");

        txttelefono.setBackground(new java.awt.Color(255, 255, 255));
        txttelefono.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        txttelefono.setForeground(new java.awt.Color(0, 0, 0));

        txtdireccion.setBackground(new java.awt.Color(255, 255, 255));
        txtdireccion.setFont(new java.awt.Font("Eras Light ITC", 3, 14)); // NOI18N
        txtdireccion.setForeground(new java.awt.Color(0, 0, 0));

        cbocarrera.setBackground(new java.awt.Color(255, 255, 255));
        cbocarrera.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        cbocarrera.setForeground(new java.awt.Color(0, 0, 0));

        cbomateria1.setBackground(new java.awt.Color(255, 255, 255));
        cbomateria1.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        cbomateria1.setForeground(new java.awt.Color(0, 0, 0));

        cbomateria2.setBackground(new java.awt.Color(255, 255, 255));
        cbomateria2.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        cbomateria2.setForeground(new java.awt.Color(0, 0, 0));

        cbomateria3.setBackground(new java.awt.Color(255, 255, 255));
        cbomateria3.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        cbomateria3.setForeground(new java.awt.Color(0, 0, 0));

        btnagregar.setBackground(new java.awt.Color(255, 255, 255));
        btnagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/addM.png"))); // NOI18N
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
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
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        btnatras.setBackground(new java.awt.Color(255, 255, 255));
        btnatras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrowback_257.png"))); // NOI18N
        btnatras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnatrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnagregar)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtapellido)))
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(txttelefono))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbocarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbomateria1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbomateria2, 0, 182, Short.MAX_VALUE))
                            .addComponent(cbomateria3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(63, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btneliminar)
                        .addGap(234, 234, 234)
                        .addComponent(btnbuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnatras)
                        .addGap(87, 87, 87))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbocarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbomateria1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbomateria2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbomateria3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnagregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnatras))
                .addContainerGap())
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
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

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        // TODO add your handling code here:
        int cod = Integer.parseInt(txtcodigo.getText().toString());
        String nom = txtnombre.getText().toUpperCase();
        String ape1 = txtapellido.getText().toUpperCase();
        int dni1 = Integer.parseInt(txtcedula.getText().toString());
        String cel1 = txttelefono.getText().toUpperCase();
        String direcc = txtdireccion.getText().toUpperCase();
        String car = cbocarrera.getSelectedItem().toString();
        String mat1 = cbomateria1.getSelectedItem().toString();
        String mat2 = cbomateria2.getSelectedItem().toString();
        String mat3 = cbomateria3.getSelectedItem().toString();
        if (nom.isEmpty() || ape1.isEmpty()
                || direcc.isEmpty() || car.isEmpty() || mat1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ALGUNOS CAMPOS ESTAN VACIOS, RELLENELOS!");
        } else if (mat2.isEmpty() && mat3.isEmpty()) {
            JOptionPane.showMessageDialog(this, "¿DESEAS INGRESAR MAS MATERIAS?");
        } else {
            lista.pFound = buscar(lista.ini, nom);
            if (lista.pFound != null) {
                lista.pFound = buscar2(lista.ini, ape1);
                if (lista.pFound != null) {
                    JOptionPane.showMessageDialog(this, "EL NOMBRE: " + nom + " , APELLIDO: " + ape1 + "YA EXISTE, INGRESE OTRO");
                }

            } else {
                int cel = Integer.parseInt(cel1);
                lista.ini = lista.inserta_final(lista.ini, new alumno(new Object[]{cod, nom, ape1, dni1, cel, direcc, car, mat1, mat2, mat3}));
                limpiar_entradas();
                ver_datos(1);
                gestor.GrabarArchivo(FileName, lista);
            }
        }
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
        gestor.GrabarArchivo(FileName, lista);
        limpiar_entradas();
        ver_datos(1);
        if (lista.ini == null) {
            JOptionPane.showMessageDialog(this, "LISTA VACIA");
        }

    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        String nom = txtnombre.getText();
        if (nom.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "INGRESE UN NOMBRE EN EL CAMPO NOMBRE!");
        } else {
            lista.pFound = buscar(lista.ini, nom);
            if (lista.pFound != null) {
                txtnombre.setText(lista.pFound.alumno.getNombre());
                txtapellido.setText(lista.pFound.alumno.getApellido());
                txtcedula.setText(String.valueOf(lista.pFound.alumno.getCedula()));
                txttelefono.setText(String.valueOf(lista.pFound.alumno.getTelefono()));
                txtdireccion.setText(String.valueOf(lista.pFound.alumno.getDireccion()));
            } else {
                JOptionPane.showMessageDialog(this, "EL NOMBRE: " + nom + " NO ESTA EN LA LISTA");
            }
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

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
            java.util.logging.Logger.getLogger(frmAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAlumnos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnatras;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JComboBox<String> cbocarrera;
    private javax.swing.JComboBox<String> cbomateria1;
    private javax.swing.JComboBox<String> cbomateria2;
    private javax.swing.JComboBox<String> cbomateria3;
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
    private javax.swing.JTable tablaEstudiantes;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtcedula;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
