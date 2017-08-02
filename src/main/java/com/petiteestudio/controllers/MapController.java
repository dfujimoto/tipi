package com.petiteestudio.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Daniel on 25/07/2017.
 */
public class MapController {

    @RequestMapping("/mapa")
    public String abrirMapa(){

        return "mapa/mapa";
    }
}
