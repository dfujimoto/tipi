package com.petiteestudio.Beans;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Daniel on 22/07/2017.
 */
public class VentaEntregarBean {
    private Long id;
    private Long idCliente;
    private String nombre;
    private String fechaPactada;
    private String direccion;
    private String distrito;
    private String detalle;
    private String precioEstimado;
    private String tiempoEstimado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaPactada() {
        return fechaPactada;
    }

    public void setFechaPactada(String fechaPactada) {
        this.fechaPactada = fechaPactada;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getPrecioEstimado() {
        return precioEstimado;
    }

    public void setPrecioEstimado(String precioEstimado) {
        this.precioEstimado = precioEstimado;
    }

    public String getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(String tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }
}
