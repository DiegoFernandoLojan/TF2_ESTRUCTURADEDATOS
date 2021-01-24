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
public class cursos implements Serializable {

    private String codigoCa;
    private String nombreCa;
    private String duracionCa;
    private int cantidadMaterias;
    private int pago;
    private double descuento;
    private double pago_total;

    public final void setRegistro(Object[] registro) {
        this.codigoCa = registro[0].toString();
        this.nombreCa = registro[1].toString();
        this.duracionCa = registro[2].toString();
        this.cantidadMaterias = Integer.parseInt(registro[3].toString());
        this.pago = Integer.parseInt(registro[4].toString());
        this.descuento = Double.parseDouble(registro[5].toString());
        this.pago_total = Double.parseDouble(registro[6].toString());
    }

    public Object[] getRegistro() {
        Object[] registro = {codigoCa, nombreCa, duracionCa, cantidadMaterias,pago, descuento, pago_total};
        return registro;
    }

    public cursos(Object[] registro) {
        this.setRegistro(registro);
    }


    public String getCodigoCa() {
        return codigoCa;
    }

    public void setCodigoCa(String codigoCa) {
        this.codigoCa = codigoCa;
    }

    public String getNombreCa() {
        return nombreCa;
    }

    public void setNombreCa(String nombreCa) {
        this.nombreCa = nombreCa;
    }

    public String getDuracionCa() {
        return duracionCa;
    }

    public void setDuracionCa(String duracionCa) {
        this.duracionCa = duracionCa;
    }

    public int getCantidadMaterias() {
        return cantidadMaterias;
    }

    public void setCantidadMaterias(int cantidadMaterias) {
        this.cantidadMaterias = cantidadMaterias;
    }

    public int getPago() {
        return pago;
    }
    
    public void setPago(int pago) {
        this.pago = pago;
    }

    public double getDescuento() {
        return descuento*0.20;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getPago_total() {
        return pago_total-getDescuento();
    }

    public void setPago_total(double pago_total) {
        this.pago_total = pago_total;
    }
    
    
    
}
