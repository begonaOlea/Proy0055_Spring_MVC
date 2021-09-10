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
 
//    @RequestMapping("/pedido2/P1234/2")
//    public String process(Model model) {
//    	
//        pedidoService.generarPedido("P1234", 2);
//        
//        //model.addAttribute("productos", productoService.getTodosProductos());
//        //return "productos"; // /WEB-INF/jsp/productos.jsp
//        return "redirect:/comercio/productos";  //vuelve al navegador diciendole 
//                                       //que  vuelva allamar con esta url
//                                     // urlnav .... /productos
//    }
    

    @RequestMapping("/pedido/{idProducto}/{cantidad}")
    public String process(
    		@PathVariable("idProducto") String idProducto,
    		@PathVariable("cantidad") String cantidades) {
    	
    	int cantidad = Integer.parseInt(cantidades);
    	System.out.println("...... pidiendo " + idProducto  + " cantidad " + cantidades);
        pedidoService.generarPedido(idProducto, cantidad);
        return "redirect:/comercio/productos";
        
    }
    
    
    
}
