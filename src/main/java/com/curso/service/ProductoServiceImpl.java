
package com.curso.service;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import com.curso.excepcion.GestionProductoException;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
  
	@Autowired
    private ProductoRepository productoRepositorio;
     
    @Override
    public List<Producto> getTodosProductos() {
       return productoRepositorio.getAllProductos();
    }
    
    
    
    
    
    @Override
    public List<Producto> getProductosPorCategoria(String categoria) {
      return productoRepositorio.getProductosPoCategoria(categoria);
    }

    

    @Override
    public Producto getProductoPorId(String idProducto) {
      Producto producto =productoRepositorio.getProductoPorId(idProducto);
      return producto;
    }
    
    @Override
    public void crearProducto(Producto producto) {
    	
    	try {
    		Producto p = productoRepositorio.getProductoPorId(producto.getIdProducto());
    		throw new GestionProductoException(producto.getIdProducto(),
     			   "No pudo crear . ya existe el producto con id ");
    	}catch(IllegalArgumentException e) { //el id no existe
    		//correcto como no hay creo
    		 productoRepositorio.crearProducto(producto); 
       }
 
    }
}
