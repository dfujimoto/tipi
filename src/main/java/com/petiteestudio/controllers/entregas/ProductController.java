package com.petiteestudio.controllers.entregas;


import com.petiteestudio.entities.Categoria;
import com.petiteestudio.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Product controller.
 */
@Controller
public class ProductController {

    /**
     * New product.
     *
     * @param model
     * @return
     */
    @RequestMapping("product/new")
    public String newProduct(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "entregas/productform";
    }


    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Categoria categoria) {
        //productService.saveProduct(product);
        return "redirect:/product/" + categoria.getId();
    }


}
