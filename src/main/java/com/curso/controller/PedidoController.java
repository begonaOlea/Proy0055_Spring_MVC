package com.curso.controller;

import com.curso.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    public PedidoController() {
        System.out.println(".... inicializando PedidoController ");
    }
 
//    @RequestMapping("/pedido/P1234/2")
//    public String process() {
//    	
//        pedidoService.generarPedido("P1234", 2);
//        return "redirect:/productos";
//    }


    @RequestMapping("/pedido/{idProducto}/1")
    public String process(
    		@PathVariable("idProducto") String idProducto) {
    	
        pedidoService.generarPedido(idProducto, 1);
        return "redirect:/comercio/productos";
    }
    
}
