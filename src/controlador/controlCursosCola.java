/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import modelo.cursos;

/**
 *
 * @author ferna
 */
public class controlCursosCola implements Serializable {

    //CLASE NODO DONDE HACEMOS EL NODO PARA LA COLA
    public class nodoCola implements Serializable {

        private cursos mat;
        private nodoCola sig;

        public nodoCola(cursos materiaas) {
            this.mat = materiaas;
            this.sig = null;
        }

        public cursos getMat() {
            return mat;
        }

        public void setMat(cursos mat) {
            this.mat = mat;
        }

        public nodoCola getSig() {
            return sig;
        }

        public void setSig(nodoCola sig) {
            this.sig = sig;
        }
    }

    //Metodos que tendra la clase ColaMaterias
    public nodoCola inicio, fin;
    public nodoCola nodo1;
    public nodoCola nodo2;
    int num = 0;

    public controlCursosCola() {
        this.fin = null;
    }

    //Metodo para verificar su la COLA esta vacia
    public boolean estaVacia() {
        return (this.inicio == null);
    }

    //METODO PARA ENCOLAR
    public void encolar(cursos materia1) {
        nodoCola nuevo = new nodoCola(materia1);
        if (estaVacia()) {
            inicio = nuevo;
        } else {
            fin.setSig(nuevo);
        }
        fin = nuevo;
        fin.setSig(null);
    }

    //PARA DESENCOLAR O ELIMINAR
    public String eliminar() {
        String eliminado = "";
        nodoCola aux = inicio;
        String co = aux.mat.getCodigoCa();
        String nomC = aux.mat.getNombreCa();
        String duraC = aux.mat.getDuracionCa();
        int cantiM = aux.mat.getCantidadMaterias();
        int pag = aux.mat.getPago();
        double des = aux.mat.getDescuento();
        double pagt = aux.mat.getPago_total();
        eliminado = co + "," + nomC + "," + duraC + "," + cantiM + "," + String.valueOf(pag) + "," + String.valueOf(des) + "," + String.valueOf(pagt);
        inicio = inicio.getSig();
        return eliminado;
    }

    //METODOS PARA BUSCAR
    public nodoCola buscar(nodoCola frente, String cod) {
        nodoCola pos = frente;
        while (pos != null && !cod.equalsIgnoreCase(pos.mat.getCodigoCa())) {
            pos = pos.sig;
        }
        return pos;
    }

    public nodoCola buscar2(nodoCola frente, String mat) {
        nodoCola pos = frente;
        while (pos != null && !mat.equalsIgnoreCase(pos.mat.getNombreCa())) {
            pos = pos.sig;
        }
        return pos;
    }

    //GETTERS Y SETTERS
    public nodoCola getInicio() {
        return inicio;
    }

    public void setInicio(nodoCola inicio) {
        this.inicio = inicio;
    }

    public nodoCola getFin() {
        return fin;
    }

    public void setFin(nodoCola fin) {
        this.fin = fin;
    }

    public nodoCola getNodo1() {
        return nodo1;
    }

    public void setNodo1(nodoCola nodo1) {
        this.nodo1 = nodo1;
    }

    public nodoCola getNodo2() {
        return nodo2;
    }

    public void setNodo2(nodoCola nodo2) {
        this.nodo2 = nodo2;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
