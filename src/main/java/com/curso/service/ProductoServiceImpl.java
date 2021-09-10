
package com.curso.service;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
     @Autowired
    private ProductoRepository productoRepositorio;

    public ProductoServiceImpl() {
        System.out.println("........... intanciando ProductoServiceImpl");
    }

     
     
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
}
