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
public class alumno extends persona implements Serializable {

    //todo heredado de persona
    public String carrera;
    public String materia1;
    public String materia2;
    public String materia3;

    public final void setRegistro(Object[] registro) {
        this.codigoP = Integer.parseInt(registro[0].toString());
        this.nombre = registro[1].toString();
        this.apellido = registro[2].toString();
        this.cedula = Integer.parseInt(registro[3].toString());
        this.telefono = Integer.parseInt(registro[4].toString());
        this.direccion = registro[5].toString();
        this.carrera = registro[6].toString();
        this.materia1 = registro[7].toString();
        this.materia2 = registro[8].toString();
        this.materia3 = registro[9].toString();

    }

    public Object[] getRegistro() {
        Object[] registro = {codigoP, nombre, apellido, cedula, telefono, direccion, carrera, materia1, materia2, materia3};
        return registro;
    }

    public alumno(Object[] registro) {
        this.setRegistro(registro);
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getMateria1() {
        return materia1;
    }

    public void setMateria1(String materia1) {
        this.materia1 = materia1;
    }

    public String getMateria2() {
        return materia2;
    }

    public void setMateria2(String materia2) {
        this.materia2 = materia2;
    }

    public String getMateria3() {
        return materia3;
    }

    public void setMateria3(String materia3) {
        this.materia3 = materia3;
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
