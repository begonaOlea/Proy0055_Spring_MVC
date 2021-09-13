
package com.curso.service;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import com.curso.excepcion.GestionProductoException;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
  
	@Autowired
	//@Qualifier("InMemoryProductosRepository")
	@Qualifier("JPAProductosRepository")
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
    public Producto crearProducto(Producto producto) {

    	Producto p = productoRepositorio.getProductoPorId(producto.getIdProducto());
    	if( p != null) {
	    	throw new GestionProductoException(producto.getIdProducto(),
	     			   "No pudo crear . ya existe el producto con id ");
    	}

    	return productoRepositorio.crearProducto(producto); 

    }


	@Override
	public Producto modificar(Producto producto) {
		return productoRepositorio.modificarProducto(producto);
	}


	@Override
	public void borrar(String id) {
		//borrar producto . e interario
		productoRepositorio.borrarProducto(id);
		
		
	}
}
