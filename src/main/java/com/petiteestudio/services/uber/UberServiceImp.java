package com.petiteestudio.services.uber;

import com.petiteestudio.services.uber.UberService;
import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;
import com.uber.sdk.rides.client.model.*;
import com.uber.sdk.rides.client.services.RidesService;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.List;

/**
 * Created by Daniel on 19/07/2017.
 */
@Service
public class UberServiceImp implements UberService {


    private RidesService crearSesion(){
        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId("wBUuLpI1bCzm-DryOCNYjEfRQteBwEi8")
                .setServerToken("OM4dYFD1966ZBxoUTavyH5tOj7eetTyCPGHNHNpx")
                .build();

        ServerTokenSession session = new ServerTokenSession(config);

        // Create the Uber API service object once the User is authenticated
        UberRidesApi uberRidesApi = UberRidesApi.with(session).build();
        RidesService service = uberRidesApi.createService();

        return service;
    }

    private RidesService crearSesionPrueba(){
        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId("wBUuLpI1bCzm-DryOCNYjEfRQteBwEi8")
                .setServerToken("OM4dYFD1966ZBxoUTavyH5tOj7eetTyCPGHNHNpx")
                .setEnvironment(SessionConfiguration.Environment.SANDBOX)
                .build();

        ServerTokenSession session = new ServerTokenSession(config);

        // Create the Uber API service object once the User is authenticated
        UberRidesApi uberRidesApi = UberRidesApi.with(session).build();
        RidesService service = uberRidesApi.createService();

        return service;
    }

    public List<Product> productosDisponibles() throws Exception{

        RidesService service = crearSesion();

        Response<ProductsResponse> response= service.getProducts(-12.111122f, -77.003842f).execute();
        ProductsResponse products = response.body();
        List<Product> listProducts = products.getProducts();

        return listProducts;
    }
    //longitud: -12.128325f
    //latitud: -77.025040f
    public PriceEstimate precioEstimado(Float longitud, Float latitud) throws Exception{

        RidesService service = crearSesion();

        Response<PriceEstimatesResponse> response = service.getPriceEstimates(-12.069985f, -77.081996f, longitud, latitud).execute();
        PriceEstimatesResponse priceEstimatesResponse = response.body();
        List<PriceEstimate> precios= priceEstimatesResponse.getPrices();

        for (PriceEstimate priceEstimate:precios) {
            if(priceEstimate.getDisplayName().equals("uberX")){
                return priceEstimate;
            }
        }
        return null;
    }

    public TimeEstimate tiempoEstimado() throws Exception{

        RidesService service = crearSesion();

        Response<TimeEstimatesResponse> response = service.getPickupTimeEstimate(-12.103744f,  -76.963331f, "9a92d146-64dc-48a3-9580-f786b263ba79").execute();
        TimeEstimatesResponse timeEstimatesResponse = response.body();
        List<TimeEstimate> tiempos= timeEstimatesResponse.getTimes();

        for (TimeEstimate timeEstimate:tiempos) {
            if(timeEstimate.getDisplayName().equals("uberX")){
                return timeEstimate;
            }
        }
        return null;
    }

    public String solicitarServicio(Float longitud, Float latitud) throws Exception{
        RidesService service = crearSesion();

        RideRequestParameters rideRequestParameters = new RideRequestParameters.Builder().setPickupCoordinates(-12.111122f, -77.003842f)
                .setProductId("9a92d146-64dc-48a3-9580-f786b263ba79")
                .setDropoffCoordinates(longitud, latitud)
                .build();
        Ride ride = service.requestRide(rideRequestParameters).execute().body();
        String rideId = ride.getRideId();

        return rideId;

    }

    public Ride solicitarServicioPrueba(Float longitud, Float latitud) throws Exception{
        RidesService service = crearSesionPrueba();

        RideRequestParameters rideRequestParameters = new RideRequestParameters.Builder().setPickupCoordinates(-12.111122f, -77.003842f)
                .setProductId("9a92d146-64dc-48a3-9580-f786b263ba79")
                .setDropoffCoordinates(longitud, latitud)
                .build();
        Ride ride = service.requestRide(rideRequestParameters).execute().body();
        String rideId = ride.getRideId();

        SandboxRideRequestParameters sandboxRideRequestParameters = new SandboxRideRequestParameters.Builder()
                .setStatus("acepted")
                .build();
        Response<Void> response = service.updateSandboxRide(rideId, sandboxRideRequestParameters).execute();

        return ride;

    }

    public String obtenerDatosServicio(String rideId) throws Exception{
        RidesService service = crearSesion();
        Response<RideMap> response = service.getRideMap(rideId).execute();

        RideMap rideMap = response.body();

        return rideMap.getHref();
    }

    public void cancelarServicio(String rideId){
        RidesService service = crearSesion();

        service.cancelRide(rideId);
    }

}
