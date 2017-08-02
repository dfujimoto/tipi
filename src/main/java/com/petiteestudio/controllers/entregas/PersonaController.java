package com.petiteestudio.controllers.entregas;

import com.petiteestudio.entities.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;

/**
 * Created by Daniel on 23/07/2017.
 */

@Controller
public class PersonaController {

    @RequestMapping("nueva/persona")
    public String nuevaPersona(Model model){

        model.addAttribute("persona", new Persona());
        return "personas/nueva";
    }
}
