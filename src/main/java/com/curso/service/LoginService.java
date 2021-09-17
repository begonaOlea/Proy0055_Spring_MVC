package com.curso.service;

import com.curso.domain.Usuario;
import com.curso.domain.repository.UsuariosRepository;
import com.curso.excepcion.RegistroUsuariosException;
import com.curso.excepcion.UsuarioYaExisteException;

public interface LoginService {
	
	boolean login(String nombre, String clave );
	void setRepositorio(UsuariosRepository repo);
	
	void registrarUsuario(Usuario u) throws RegistroUsuariosException;

}


/**

   HISTORIA USUARIO:  Registrar un nuevo usuarios
   
   . usuario es null ->  RegistroUsuariosException("Debes indicar un usario")
   . no me has indicado nombre  (null o "") -> RegistroUsuariosException("Indicar nombre")
   . no me has indicado clave  (null o "") -> RegistroUsuariosException("Indicar clave")
   . usuario ya existe -> RegistroUsuariosException("Ya existe")
   . usuario con nombre y clave y no existe -> lo crea
   
    
    

*/