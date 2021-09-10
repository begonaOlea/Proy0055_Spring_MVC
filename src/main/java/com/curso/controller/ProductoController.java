package com.curso.controller;

import com.curso.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("comercio")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @RequestMapping("/productos")
    public String list(Model model) {

        model.addAttribute("productos", productoService.getTodosProductos());

        return "productos";
    }

    @RequestMapping("/productos/{categoria}")
    public String getProductosPorCategoria(Model model,
            @PathVariable("categoria") String categoriaProducto) {
        
        model.addAttribute("productos",
                productoService.getProductosPorCategoria(categoriaProducto));
        return "productos";
    }
    
    
    @RequestMapping("/producto") 
    public String getProductoPorId(
            @RequestParam("id") String productId, 
            Model model) { 
         model.addAttribute("producto",   
                         productoService.getProductoPorId(productId)); 
         return "producto"; 
      } 


    

    public ProductoController() {
        System.out.println("... iniciando ProductController");
    }

}

