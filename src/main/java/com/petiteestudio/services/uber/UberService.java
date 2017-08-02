package com.petiteestudio.services.uber;

import com.uber.sdk.rides.client.model.PriceEstimate;
import com.uber.sdk.rides.client.model.Ride;
import com.uber.sdk.rides.client.model.TimeEstimate;

/**
 * Created by Daniel on 19/07/2017.
 */
public interface UberService {

    PriceEstimate precioEstimado(Float longitud, Float latitud) throws Exception;

    TimeEstimate tiempoEstimado() throws Exception;

    Ride solicitarServicioPrueba(Float longitud, Float latitud) throws Exception;
}
