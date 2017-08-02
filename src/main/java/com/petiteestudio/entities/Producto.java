package com.petiteestudio.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel on 20/07/2017.
 */
@Entity
public class Producto implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String descripcion;
    private Long cantidadTotal;
    private Double costoFabricacion;
    private Double precioVenta;
    private String permiteEnvio;
    private String estado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_diseño")
    private Diseño diseño;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<CategoriaProducto> categoriaProductos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Long cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Double getCostoFabricacion() {
        return costoFabricacion;
    }

    public void setCostoFabricacion(Double costoFabricacion) {
        this.costoFabricacion = costoFabricacion;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getPermiteEnvio() {
        return permiteEnvio;
    }

    public void setPermiteEnvio(String permiteEnvio) {
        this.permiteEnvio = permiteEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Diseño getDiseño() {
        return diseño;
    }

    public void setDiseño(Diseño diseño) {
        this.diseño = diseño;
    }

    public List<CategoriaProducto> getCategoriaProductos() {
        return categoriaProductos;
    }

    public void setCategoriaProductos(List<CategoriaProducto> categoriaProductos) {
        this.categoriaProductos = categoriaProductos;
    }
}
