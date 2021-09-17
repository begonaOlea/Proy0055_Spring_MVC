
package com.curso.service;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import com.curso.excepcion.GestionProductoException;
import com.curso.excepcion.InventarioException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED,
			   rollbackFor = InventarioException.class)
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
        //tx begin
    	Producto p = productoRepositorio.getProductoPorId(producto.getIdProducto());
    	if( p != null) {
	    	throw new GestionProductoException(producto.getIdProducto(),
	     			   "*******  No pudo crear el Producto******. Ya existe uno con id ");
    	}

    	return productoRepositorio.crearProducto(producto); 
        //si no hay excepciones RuntimeExcetion COMMIT
    	//si hay excepciones RuntimeExcetion ROLLBAK
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
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,
	              noRollbackFor = GestionProductoException.class )
	public void cambiarPrecio(List<Producto> productos, double nuevoPrecio) {
		
		// cambio el precio de los productos que me llegan
		// pero solo si el nuevo precio es mayor que el actual
		
		
		// tx.begintransa
		for(Producto p: productos) {
			Producto pBD = getProductoPorId(p.getIdProducto());
			if (pBD == null) {
				throw new GestionProductoException("No se puede n modo....");
			}else if(pBD.getPrecioUnitario().equals(new BigDecimal(nuevoPrecio))){
			   	throw new GestionProductoException("No se puede n modo....");
			}else {
				pBD.setPrecioUnitario(new BigDecimal(nuevoPrecio));
				productoRepositorio.modificarProducto(pBD);
			}
		}
		/*
		 *  Aunque haya hecho algun cambio de precio
		 *  si alguno de los productos lanza una exepcion 
		 *  GestionProductoException que es RuntimeException
		 *  hace rollback de todo
		 */
		
	}
	
	
}
