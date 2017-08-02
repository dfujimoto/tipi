package com.petiteestudio.services.envio;

import com.petiteestudio.dao.EntregaDAO;
import com.petiteestudio.entities.Entrega;
import com.petiteestudio.utilitarios.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 22/07/2017.
 */
@Service
@Transactional(readOnly = false)
public class EntregaServiceImp implements EntregaService {

    @Autowired
    private EntregaDAO entregaDAO;

    public List<Entrega> entregasCourier(){

        List<Entrega> listaFiltrada = new ArrayList<>();
        Iterable<Entrega> entregas = entregaDAO.findAll();

        for (Entrega entrega:entregas) {
            if(!Util.isEmpty(entrega.getCabifyID())){
                listaFiltrada.add(entrega);
            }
        }

        return listaFiltrada;
    }


    public List<Entrega> entregas(){

        List<Entrega> lista = new ArrayList<>();
        Iterable<Entrega> entregas = entregaDAO.findAll();

        for (Entrega entrega:entregas) {
            lista.add(entrega);
        }

        return lista;
    }

    public void registraEntrega(Entrega entrega){

        entregaDAO.save(entrega);
    }

    public Entrega obtenetEntrega(Long id){
        return entregaDAO.findOne(id);
    }
}
