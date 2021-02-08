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
public class maestro extends persona implements Serializable {

    public String sueldo;
    public String rol;  //tambien se puede poner string acceso
    public String carrera;
    public String materia;

    public final void setRegistro(Object[] registro) {
        this.codigoP = Integer.parseInt(registro[0].toString());
        this.nombre = registro[1].toString();
        this.apellido = registro[2].toString();
        this.cedula = Integer.parseInt(registro[3].toString());
        this.telefono = Integer.parseInt(registro[4].toString());
        this.direccion = registro[5].toString();
        this.rol = registro[6].toString();
        this.sueldo = registro[7].toString();
        this.carrera = registro[8].toString();
        this.materia = registro[9].toString();
    }

    public Object[] getRegistro() {
        Object[] registro = {codigoP, nombre, apellido, cedula, telefono, direccion, rol, sueldo, carrera, materia};
        return registro;
    }

    public maestro(Object[] registro) {
        this.setRegistro(registro);
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }



    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }



    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(int codigoP) {
        this.codigoP = codigoP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
