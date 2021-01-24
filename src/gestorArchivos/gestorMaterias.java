/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorArchivos;


import controlador.controlMateriasCola;
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
import modelo.materias;
import controlador.controlMateriasCola.Nodo1;



/**
 *
 * @author ferna
 */
public class gestorMaterias implements Serializable{
    static String ruta = System.getProperties().getProperty("user.dir") + "//";

    public static void EscribeTxtMateria(StringBuffer materia) {
        File archivo;
        FileWriter escribir;
        PrintWriter linea;
        archivo = new File("materias.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(materia);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                Logger.getLogger(gestorMaterias.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS MATERIA", "INFORMACION", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            try {
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(materia);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                Logger.getLogger(gestorMaterias.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "ERROR AL ESCRIBIR DATOS MATERIA", "INFORMACION", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void GrabarArchivo(String FileName, controlMateriasCola lista) {
        try {
            FileOutputStream fos = new FileOutputStream(FileName);
            ObjectOutputStream sale = new ObjectOutputStream(fos);
            if (sale != null) {
                Nodo1 auxiliar = lista.getFrente();
                while (auxiliar != null) {
                    sale.writeObject(auxiliar.getMat());
                    auxiliar = auxiliar.getSig();
                }
                sale.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR DATOS2 CURSOS", "INFORMACION", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void AbrirArchivo(String FileName, controlMateriasCola lista) {
        try {
            FileInputStream fis = new FileInputStream(FileName);
            ObjectInputStream entra = new ObjectInputStream(fis);
            if (entra != null) {
                materias elemento;
                while ((elemento = (materias) entra.readObject()) != null) {
                    lista.encolar(elemento);
                }
                entra.close();
            }
        } catch (IOException | ClassNotFoundException e) {
                
        }
    }
}
