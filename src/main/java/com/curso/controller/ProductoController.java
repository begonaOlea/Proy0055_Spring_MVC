package com.curso.controller;

import com.curso.domain.Producto;
import com.curso.excepcion.GestionProductoException;
import com.curso.service.ProductoService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	@GetMapping(value = "/productos/nuevo")
	public String getCrearNuevoProductoFormulario(Model model) {
		Producto nuevoProducto = new Producto();
		nuevoProducto.setDescripcion("nuevo");
		model.addAttribute("nuevoProducto", nuevoProducto);
		return "crear-producto";
	}

	
	
	// tratara los datos recibidos del formulario
	@RequestMapping(value = "/productos/nuevo", 
			        method = RequestMethod.POST)
	public String procesarCrearNuevoProductoFormulario(
			@ModelAttribute("nuevoProducto") 
			@Valid Producto nuevoProducto,
			BindingResult bindingResult) {
		
		
		
		//comprobar que es valido 
		if (bindingResult.hasErrors()) {
			//nuevoProducto.setDescripcion("error");
			return "crear-producto";  //no usar redirect se pierden los erros
		}

		 productoService.crearProducto(nuevoProducto);

		// model.addAttribute("productos",
		// productoService.getTodosProductos());
		// return "productos";
		return "redirect:/comercio/productos"; 
	}
	
	@ExceptionHandler(GestionProductoException.class)
    public ModelAndView handleError(
    		HttpServletRequest req,
    		GestionProductoException exception) {
 
        ModelAndView mav = new ModelAndView();
        mav.addObject("idProductoNoEncontrado", 
                exception.getIdProducto());
        mav.addObject("claveMensage", 
                exception.getClaveMensaje());
        
        mav.setViewName("producto-exception");
        return mav;
    }
	
	

}
