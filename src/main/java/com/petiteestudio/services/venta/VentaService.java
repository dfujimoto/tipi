package com.petiteestudio.services.venta;

import com.petiteestudio.entities.Venta;

import java.util.List;

/**
 * Created by Daniel on 21/07/2017.
 */
public interface VentaService {

    public List<Venta> ventasPorEntregar();

    public void actualizarVenta(Venta venta);

    public Venta obtenerVenta(Long id);
}
