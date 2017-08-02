package com.petiteestudio.services.venta;

import com.petiteestudio.dao.VentaDAO;
import com.petiteestudio.entities.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 21/07/2017.
 */
@Service
@Transactional(readOnly = false)
public class VentaServiceImp implements VentaService {

    @Autowired
    private VentaDAO ventaDAO;

    public List<Venta> ventasPorEntregar(){

        List<Venta> listaFiltrada = new ArrayList();
        Iterable<Venta> ventas = ventaDAO.findAll();

        for (Venta venta:ventas) {
            if(venta.getEstado().equals("E") && venta.getIndicadorEnvio().equals("S")){
                listaFiltrada.add(venta);
            }
        }

        return listaFiltrada;
    }

    public void actualizarVenta(Venta venta){
        ventaDAO.save(venta);
    }

    public Venta obtenerVenta(Long id){
        return ventaDAO.findOne(id);
    }
}
