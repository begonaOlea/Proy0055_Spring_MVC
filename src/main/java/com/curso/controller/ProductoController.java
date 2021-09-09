package com.curso.controller;
import com.curso.domain.Producto;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ProductoController {
    
     @RequestMapping("/productos")
     public String list(Model model) {
        Producto iphone = new Producto("P1234","iPhone 5s",
                new  BigDecimal(500));
        iphone.setDescripcion("Apple iPhone 5s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
        iphone.setCategoria("Smart Phone");
        iphone.setFabricante("Apple");
        iphone.setUnidadesEnPedido(1000);

        model.addAttribute("producto", iphone);

        return "productos";
    }

    public ProductoController() {
        System.out.println("... iniciando ProductController");
    }
    
}
