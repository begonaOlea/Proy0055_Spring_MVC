
package com.curso.domain.repository;

import com.curso.domain.Producto;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;


public interface ProductoRepository {
    List <Producto> getAllProductos();
    Producto getProductoPorId(String idProducto);
}
