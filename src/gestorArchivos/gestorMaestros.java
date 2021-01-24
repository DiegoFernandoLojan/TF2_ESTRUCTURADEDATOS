package gestorArchivos;

import controlador.controlMaestrosCircularDoble;
import controlador.controlMaestrosCircularDoble.Nodo;
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
import modelo.maestro;

public class gestorMaestros implements Serializable {

    static String ruta = System.getProperties().getProperty("user.dir") + "//";

    public static void EscribeTxtaMaestro(StringBuffer maestro) {
        File archivo;
        FileWriter escribir;
        PrintWriter linea;
        archivo = new File("maestros.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(maestro);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                Logger.getLogger(gestorMaestros.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS MAESTRO", "INFORMACION", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            try {
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(maestro);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                Logger.getLogger(gestorMaestros.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "ERROR AL ESCRIBIR DATOS MAESTRO", "INFORMACION", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void GrabarArchivo(String FileName, controlMaestrosCircularDoble lista) {
        try {
            FileOutputStream fos = new FileOutputStream(FileName);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            if (out != null) {
                Nodo auxiliar = lista.getLc();
                while (auxiliar != null) {
                    out.writeObject(auxiliar.getPro());
                    auxiliar = auxiliar.getEnlace();
                }
                out.close();
            }
        } catch (Exception e) {
        }
    }

    public void AbrirArchivo(String FileName, controlMaestrosCircularDoble lista) {
        try {
            FileInputStream fis = new FileInputStream(FileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            if (in != null) {
                maestro elemento;
                while ((elemento = (maestro) in.readObject()) != null) {
                    lista.lc = lista.inserta_final(lista.lc, elemento);
                }
                in.close();
            }
        } catch (Exception e) {
        }
    }
}
