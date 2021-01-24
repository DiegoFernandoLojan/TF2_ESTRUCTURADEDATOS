/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorArchivos;

import controlador.controlUsuarioPila;
import controlador.controlUsuarioPila.Nodo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

import modelo.usuarioLogin;

/**
 *
 * @author ferna
 */
public class gestorUsuario {

    static String ruta = System.getProperties().getProperty("user.dir") + "//";

    public static void EscribeTxt(StringBuffer usuario) {
        File archivo;
        FileWriter escribir;
        PrintWriter linea;
        archivo = new File("login.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(usuario);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS", "INFORMACION", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            try {
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                linea.println(usuario);
                linea.close();
                escribir.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS", "INFORMACION", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void GrabarArchivo(String FileName, controlUsuarioPila lista) {
        try {
            FileOutputStream fos = new FileOutputStream(FileName);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            if (out != null) {
                Nodo auxiliar = lista.getInicio();
                while (auxiliar != null) {
                    out.writeObject(auxiliar.getUsu());
                    auxiliar = auxiliar.getSigui();
                }
                out.close();
            }
        } catch (Exception e) {
        }
    }

    public void AbrirArchivo(String FileName, controlUsuarioPila lista) {
        try {
            FileInputStream fis = new FileInputStream(FileName);
            ObjectInputStream in = new ObjectInputStream(fis);
            if (in != null) {
                usuarioLogin elemento;
                while ((elemento = (usuarioLogin) in.readObject()) != null) {
                    lista.push(elemento);
                }
                in.close();
            }
        } catch (Exception e) {
        }
    }

}
