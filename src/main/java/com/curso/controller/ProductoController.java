package com.curso.controller;

import com.curso.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
     @RequestMapping("/productos")
     public String list(Model model) {

        model.addAttribute("productos", productoService.getTodosProductos());

        return "productos";
    }

    
}
