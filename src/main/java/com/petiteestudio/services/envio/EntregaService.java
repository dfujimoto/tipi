package com.petiteestudio.services.envio;

import com.petiteestudio.entities.Entrega;

import java.util.List;

/**
 * Created by Daniel on 22/07/2017.
 */
public interface EntregaService {

    public List<Entrega> entregasCourier();

    List<Entrega> entregas();

    public void registraEntrega(Entrega entrega);

    public Entrega obtenetEntrega(Long id);

}
