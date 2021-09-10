package com.curso.controller;

import com.curso.domain.Producto;
import com.curso.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("comercio")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	public ProductoController() {
		System.out.println("... iniciando ProductController");
	}

	@RequestMapping("/productos")
	public String list(Model model) {

		model.addAttribute("productos", productoService.getTodosProductos());

		return "productos";
	}

	@RequestMapping("/productos/{categoria}")
	public String getProductosPorCategoria(Model model, @PathVariable("categoria") String categoriaProducto) {

		model.addAttribute("productos", productoService.getProductosPorCategoria(categoriaProducto));
		return "productos";
	}

	
	//   locahost:8080/NombrProy/producto?id=P234
	
	@RequestMapping("/producto")
	//@RequestMapping(method = RequestMethod.GET, path = "/producto")
	//@GetMapping("/producto")
	public String getProductoPorId(
			@RequestParam("id") String productId, 
			Model model) {
		model.addAttribute("producto", 
				 productoService.getProductoPorId(productId));
		return "producto";
	}

//PRÁCTICA 7
	
	
	
	// mostra el fomulario
	@RequestMapping(value = "/productos/nuevo", method = RequestMethod.GET)
	public String getCrearNuevoProductoFormulario(Model model) {
		Producto nuevoProducto = new Producto();
		nuevoProducto.setDescripcion("nuevo");
		model.addAttribute("nuevoProducto", nuevoProducto);
		return "crear-producto";
	}

	
	
	// tratara los datos recibidos del formulario
	@RequestMapping(value = "/productos/nuevo", method = RequestMethod.POST)
	public String procesarCrearNuevoProductoFormulario(
			@ModelAttribute("nuevoProducto") Producto nuevoProducto) {
		
		productoService.crearProducto(nuevoProducto);
		// model.addAttribute("productos",
		// productoService.getTodosProductos());
		// return "productos";
		return "redirect:/comercio/productos"; 
	}

}
