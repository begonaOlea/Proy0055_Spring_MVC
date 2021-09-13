package com.curso.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import com.curso.excepcion.GestionProductoException;

@RestController
public class ProductoRESTController {

	@Autowired
	@Qualifier("JPAProductosRepository")
	private ProductoRepository repositorio;


	
	  @GetMapping("/products")
	  public List<Producto> all() {
	    return repositorio.getAllProductos();
	  }
	  

	  @PostMapping("/products")
	  public  Producto nuevoProducto(@RequestBody Producto nuevoProducto) {
	    return repositorio.crearProducto(nuevoProducto);
	  }

	  // Single item
	  
	  @GetMapping("/products/{id}")
	  public  Producto getProducto(@PathVariable String id) {
	    
	    Producto producto = repositorio.getProductoPorId(id);
	    if (producto == null) {
	    	throw new GestionProductoException(id, "Producto no encontrado");
	    }
	    return producto;
	  }

	  @PutMapping("/products/{id}")
	  public  Producto  modificarProdu(@RequestBody Producto productoModificado, 
			                           @PathVariable String id) {
	    
	    //TODO: implementar
		  return null;
	  }

	  @DeleteMapping("/products/{id}")
	  public void deleteEmployee(@PathVariable String id) {
	    //TODO implementar
		  
	  }


}
