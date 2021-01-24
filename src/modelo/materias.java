package modelo;

import java.io.Serializable;

public class materias implements Serializable {

    String codigo_materia;
    String nombre_materia;
    String nombre_carrera;

    public final void setRegistro(Object[] registro) {
        this.codigo_materia = registro[0].toString();
        this.nombre_materia = registro[1].toString();
        this.nombre_carrera = registro[2].toString();

    }

    public Object[] getRegistro() {
        Object[] registro = {codigo_materia, nombre_materia,nombre_carrera};
        return registro;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public materias(Object[] registro) {
        this.setRegistro(registro);
    }

    public String getCodigo_materia() {
        return codigo_materia;
    }

    public void setCodigo_materia(String codigo_materia) {
        this.codigo_materia = codigo_materia;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }
    
    

}
