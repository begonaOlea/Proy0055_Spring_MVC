
package com.curso.service;

import com.curso.domain.Producto;
import java.util.List;

public interface ProductoService {
    
    List <Producto> getTodosProductos();
    List<Producto> getProductosPorCategoria(String categoria); 
    Producto getProductoPorId(String idProducto);
    Producto crearProducto(Producto producto);
    Producto modificar(Producto producto);
    void borrar(String id);
}
