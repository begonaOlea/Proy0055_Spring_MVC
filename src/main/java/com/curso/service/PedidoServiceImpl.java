package com.curso.service;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private ProductoRepository productoRepositorio;

    @Override
    public void generarPedido(String idProducto, int cantidad) {
        
    	//validar pararam entrda idProducro no null cntida < 1
    	
    	Producto producto = productoRepositorio.getProductoPorId(idProducto);

    	if (producto == null) {
    		throw new IllegalArgumentException("El producto no existe");
    	}
    	
        if (producto.getUnidadesEnStock()< cantidad) {
            throw new IllegalArgumentException("Fuera de Stock. "
                    + "Hay disponibles actualmente " 
                    + producto.getUnidadesEnStock()  +" unidades.");
        }

        producto.setUnidadesEnStock(producto.getUnidadesEnStock()- cantidad);
    }
}
