package com.petiteestudio.controllers;

import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;
import com.uber.sdk.rides.client.model.*;
import com.uber.sdk.rides.client.services.RidesService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import retrofit2.Response;


/**
 * Created by Daniel on 18/07/2017.
 */
@Controller
public class Hello {

    @RequestMapping("/")
    @ResponseBody
    public String hello() throws Exception{


        SessionConfiguration config = new SessionConfiguration.Builder()
                //.setClientId("wBUuLpI1bCzm-DryOCNYjEfRQteBwEi8")
                .setClientId("OO1MW0PHu9XEuENGAbzOFwoFAl6NulCn")
                .setServerToken("OM4dYFD1966ZBxoUTavyH5tOj7eetTyCPGHNHNpx")
                .build();

        ServerTokenSession session = new ServerTokenSession(config);

        // Create the Uber API service object once the User is authenticated
        UberRidesApi uberRidesApi = UberRidesApi.with(session).build();


        RidesService service = uberRidesApi.createService();

        Response<ProductsResponse> response= service.getProducts(-12.111122f, -77.003842f).execute();
        ProductsResponse products = response.body();
        List<Product> listProducts = products.getProducts();

        Response<PriceEstimatesResponse> response1 = service.getPriceEstimates(-12.111122f, -77.003842f, -12.128325f, -77.025040f).execute();
        PriceEstimatesResponse priceEstimatesResponse = response1.body();
        List<PriceEstimate> precios= priceEstimatesResponse.getPrices();


        String productId = "";// listProducts.get(0).getDescription();
        for (Product p:listProducts) {
            productId =productId + p.getProductId() + "|" +p.getDisplayName() + "|" + p.getDescription() + "||";
        }

        productId =productId + "***";
        for (PriceEstimate precio:precios) {
            productId =productId + precio.getDisplayName() + "|" +precio.getCurrencyCode() + "|" + precio.getLowEstimate() + "|" + precio.getHighEstimate()+ "|" + precio.getDuration() + "||";
        }

        Response<PriceEstimatesResponse> response2 = service.getPriceEstimates(-12.111122f, -77.003842f, -12.075265f, -77.077318f).execute();
        PriceEstimatesResponse priceEstimatesResponse2 = response1.body();
        List<PriceEstimate> precios2= priceEstimatesResponse.getPrices();

        productId =productId + "***";
        for (PriceEstimate precio:precios2) {
            productId =productId + precio.getDisplayName() + "|" +precio.getCurrencyCode() + "|" + precio.getLowEstimate() + "|" + precio.getHighEstimate()+ "|" + precio.getDuration() + "||";
        }

        return "Hello wold" +productId;
    }

}
