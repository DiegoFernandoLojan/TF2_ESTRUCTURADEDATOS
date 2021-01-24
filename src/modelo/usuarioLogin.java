/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author ferna
 */
public class usuarioLogin implements Serializable {

    private String usuario;
    private String password;

    public final void setRegistro(Object[] registro) {
        this.usuario = registro[0].toString();
        this.password = registro[1].toString();
    }

    //
    public Object[] getRegistro() {
        Object[] registro = {usuario, password};
        return registro;
    }

    //
    public usuarioLogin(Object[] registro) {
        this.setRegistro(registro);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
