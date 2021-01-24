/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import modelo.materias;

/**
 *
 * @author ferna
 */
public class controlMateriasCola implements Serializable {

    public class Nodo1 implements Serializable {

        public materias mat;
        public Nodo1 sig;

        public Nodo1(materias materia1) {
            mat = materia1;
            sig = null;
        }

        public materias getMat() {
            return mat;
        }

        public void setMat(materias mat) {
            this.mat = mat;
        }

        public Nodo1 getSig() {
            return sig;
        }

        public void setSig(Nodo1 sig) {
            this.sig = sig;
        }

    }

    public Nodo1 frente, fincola;
    public Nodo1 pFound;
    public Nodo1 pFound2;
    int num = 0;

    public controlMateriasCola() {
        fincola = null;
    }

    public void encolar(materias materia1) {
        Nodo1 nuevo = new Nodo1(materia1);
        if (frente == null) {
            frente = nuevo;
        } else {
            fincola.sig = nuevo;
        }
        fincola = nuevo;
        fincola.sig = null;
    }

    public Nodo1 buscar(Nodo1 frente, String cod) {
        Nodo1 pos = frente;
        while (pos != null && !cod.equalsIgnoreCase(pos.mat.getCodigo_materia())) {
            pos = pos.sig;
        }
        return pos;
    }

    public Nodo1 buscar2(Nodo1 frente, String mat) {
        Nodo1 pos = frente;
        while (pos != null && !mat.equalsIgnoreCase(pos.mat.getNombre_materia())) {
            pos = pos.sig;
        }
        return pos;
    }

    public String frente() {
        String eliminado = "";
        Nodo1 aux = frente;
        String co = aux.mat.getCodigo_materia();
        String n = aux.mat.getNombre_materia();
        eliminado = co + "," + n;
        frente = frente.sig;
        return eliminado;
    }

    public Nodo1 getFrente() {
        return frente;
    }

    public void setFrente(Nodo1 frente) {
        this.frente = frente;
    }

    public Nodo1 getFincola() {
        return fincola;
    }

    public void setFincola(Nodo1 fincola) {
        this.fincola = fincola;
    }

    public Nodo1 getpFound() {
        return pFound;
    }

    public void setpFound(Nodo1 pFound) {
        this.pFound = pFound;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
