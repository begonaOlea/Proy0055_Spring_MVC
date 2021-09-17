package com.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.domain.Usuario;
import com.curso.domain.repository.UsuariosRepository;
import com.curso.excepcion.RegistroUsuariosException;
import com.curso.excepcion.UsuarioYaExisteException;

@Service
public class LoginServiceImp implements LoginService {

	// @Autowired
	private UsuariosRepository repositorio;

	public void setRepositorio(UsuariosRepository repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public boolean login(String nombre, String clave) {

		Usuario u = repositorio.getUsuarioByNombre(nombre);
		if (u == null || !u.getClave().equals(clave))
			return false;
		else
			return true;
	}

	@Override
	public void registrarUsuario(Usuario u) throws RegistroUsuariosException {
		
			if(u == null ) throw new RegistroUsuariosException("Debe introducir un usuario");
			
			if(u.getNombre() == null ||  u.getNombre().trim().length() == 0 ) 
				throw new RegistroUsuariosException("Debe introducir un nombre");
			
			if(u.getClave() == null ||  u.getClave().trim().length() == 0 ) 
				throw new RegistroUsuariosException("Debe introducir un nombre");
			
	
			
			try {
				repositorio.crear(u);
			} catch (UsuarioYaExisteException e) {
				throw new RegistroUsuariosException("El usuario ya existe");
			}
			

	}

}
