/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import modelo.usuarioLogin;

/**
 *
 * @author ferna
 */
public class controlUsuarioPila implements Serializable {

    //CLASE PARA EL NODO
    public class Nodo implements Serializable {

        private usuarioLogin usu;
        private Nodo sigui;

        //CONSTRUCTOR
        public Nodo(usuarioLogin usuarios) {
            this.usu = usuarios;
            this.sigui = null;
        }

        //GETTERS Y SETTERS
        public usuarioLogin getUsu() {
            return usu;
        }

        public void setUsu(usuarioLogin usu) {
            this.usu = usu;
        }

        public Nodo getSigui() {
            return sigui;
        }

        public void setSigui(Nodo sigui) {
            this.sigui = sigui;
        }
    }

    //METODOS
    public Nodo inicio;
    public Nodo nodo1;
    public Nodo nodo2;
    public int tam;

    //constructor
    public controlUsuarioPila() {
        this.inicio = null;
        this.tam = 0;
    }

    //Metodo para verificar su la lista esta vacia
    public boolean estaVacia() {
        return (this.inicio == null);
    }

    //insertarNodoPila -- PUSH
    public void push(usuarioLogin usuarios) {
        Nodo nuevo = new Nodo(usuarios);
        if (estaVacia()) {
            inicio = nuevo;
        } else {
            nuevo.setSigui(inicio);
            inicio = nuevo;
        }
        tam++;
    }

    //DESAPILAR -- POP
    public void desapilar() {
        if (!estaVacia()) {
            inicio = inicio.getSigui();
            tam--;
        }
    }

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public Nodo getNodo1() {
        return nodo1;
    }

    public void setNodo1(Nodo nodo1) {
        this.nodo1 = nodo1;
    }

    public Nodo getNodo2() {
        return nodo2;
    }

    public void setNodo2(Nodo nodo2) {
        this.nodo2 = nodo2;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

}
