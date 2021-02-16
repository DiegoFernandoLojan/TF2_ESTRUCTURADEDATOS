/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import modelo.alumno;

/**
 *
 * @author ferna
 */
public class controlAlumnoDoble implements Serializable {

    public class Nodo implements Serializable {

        public alumno alumno;
        public Nodo sig;
        public Nodo ant;

        public Nodo(alumno alum) {
            alumno = alum;
            sig = null;
            ant = null;
        }

        public alumno getAlumno() {
            return alumno;
        }

        public void setAlumno(alumno alumno) {
            this.alumno = alumno;
        }

        public Nodo getSig() {
            return sig;
        }

        public void setSig(Nodo sig) {
            this.sig = sig;
        }

        public Nodo getAnt() {
            return ant;
        }

        public void setAnt(Nodo ant) {
            this.ant = ant;
        }
    }

    public Nodo ini, fin;
    public Nodo pFound;

    public controlAlumnoDoble() {
        ini = fin = pFound = null;
    }

    public Nodo inserta_final(Nodo inicio, alumno alum) {
        Nodo nuevo = new Nodo(alum);
        nuevo.sig = inicio;
        if (inicio == null) {
            fin = nuevo;
            fin.sig = null;
        }
        if (inicio != null) {
            inicio.ant = nuevo;
        }
        inicio = nuevo;
        return inicio;
    }

    public Nodo getIni() {
        return ini;
    }

    public void setIni(Nodo ini) {
        this.ini = ini;
    }

    public Nodo getFin() {
        return fin;
    }

    public void setFin(Nodo fin) {
        this.fin = fin;
    }

    public Nodo getpFound() {
        return pFound;
    }

    public void setpFound(Nodo pFound) {
        this.pFound = pFound;
    }

}
