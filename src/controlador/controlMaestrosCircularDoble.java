package controlador;

import java.io.Serializable;
import modelo.maestro;

public class controlMaestrosCircularDoble implements Serializable {

    public class Nodo implements Serializable {

        public maestro pro;
        public Nodo enlace;
        public Nodo enlace1;

        public Nodo(maestro profe) {
            pro = profe;
            enlace = enlace1 = this;
        }

        public maestro getPro() {
            return pro;
        }

        public void setPro(maestro pro) {
            this.pro = pro;
        }

        public Nodo getEnlace() {
            return enlace;
        }

        public void setEnlace(Nodo enlace) {
            this.enlace = enlace;
        }

        public Nodo getEnlace1() {
            return enlace1;
        }

        public void setEnlace1(Nodo enlace1) {
            this.enlace1 = enlace1;
        }

    }

    public Nodo lc, lc1;
    public Nodo pFound;

    public controlMaestrosCircularDoble() {
        lc = lc1 = pFound = null;
    }

    public Nodo inserta_final(Nodo inicio, maestro profe) {
        Nodo nuevo = new Nodo(profe);
        nuevo.enlace = inicio;
        if (inicio == null) {
            lc1 = nuevo;
            lc1.enlace = null;
        }
        if (inicio != null) {
            inicio.enlace1 = nuevo;
        }
        inicio = nuevo;
        return inicio;
    }

    public Nodo getLc() {
        return lc;
    }

    public void setLc(Nodo lc) {
        this.lc = lc;
    }

    public Nodo getLc1() {
        return lc1;
    }

    public void setLc1(Nodo lc1) {
        this.lc1 = lc1;
    }

    public Nodo getpFound() {
        return pFound;
    }

    public void setpFound(Nodo pFound) {
        this.pFound = pFound;
    }
}
