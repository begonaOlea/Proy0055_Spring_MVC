package com.curso.domain.repository;

import com.curso.domain.Usuario;
import com.curso.excepcion.UsuarioYaExisteException;

public interface UsuariosRepository {
	
	Usuario getUsuarioByNombre(String usuario);
	
	Usuario crear(Usuario usuario) throws UsuarioYaExisteException;
	

}
