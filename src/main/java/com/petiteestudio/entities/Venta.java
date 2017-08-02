package com.petiteestudio.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Daniel on 20/07/2017.
 */
@Entity
public class Venta implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String numeroBoleta;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;

    private Double totalVenta;
    private Integer cantidadTotal;
    private String nombreVendedor;

    private String indicadorEnvio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntregaPactada;

    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", fetch = FetchType.LAZY)
    private List<DetalleVenta> detalleVenta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getNumeroBoleta() {
        return numeroBoleta;
    }

    public void setNumeroBoleta(String numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getIndicadorEnvio() {
        return indicadorEnvio;
    }

    public void setIndicadorEnvio(String indicadorEnvio) {
        this.indicadorEnvio = indicadorEnvio;
    }

    public Date getFechaEntregaPactada() {
        return fechaEntregaPactada;
    }

    public void setFechaEntregaPactada(Date fechaEntregaPactada) {
        this.fechaEntregaPactada = fechaEntregaPactada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
