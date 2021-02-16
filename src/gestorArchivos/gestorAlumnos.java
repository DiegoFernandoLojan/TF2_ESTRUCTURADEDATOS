/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorArchivos;

import controlador.controlAlumnoDoble;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import controlador.controlAlumnoDoble.Nodo;
import modelo.alumno;

/**
 *
 * @author ferna
 */
public class gestorAlumnos implements Serializable {

    static String ruta = System.getProperties().getProperty("user.dir") + "//";

    public static void EscribeTxtAlumno(StringBuffer alumno) {
        File archivo;
        FileWriter escribir;
        PrintWriter linea;
        archivo = new File("carrera.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(alumno);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                Logger.getLogger(gestorAlumnos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS ALUMNOS", "INFORMACION", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            try {
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(alumno);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                Logger.getLogger(gestorAlumnos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "ERROR AL ESCRIBIR DATOS ALUMNOS", "INFORMACION", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void GrabarArchivo(String FileName, controlAlumnoDoble lista) {
        try {
            FileOutputStream fos = new FileOutputStream(FileName);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            if (out != null) {
                Nodo auxiliar = lista.getIni();
                while (auxiliar != null) {
                    out.writeObject(auxiliar.getAlumno());
                    auxiliar = auxiliar.getSig();
                }
                out.close();
            }
        } catch (Exception e) {
        }
    }

    public void AbrirArchivo(String FileName, controlAlumnoDoble lista) {
        try {
            FileInputStream fis = new FileInputStream(FileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            if (in != null) {
                alumno elemento;
                while ((elemento = (alumno) in.readObject()) != null) {
                    lista.ini = lista.inserta_final(lista.ini, elemento);
                }
                in.close();
            }
        } catch (Exception e) {
        }
    }
}
