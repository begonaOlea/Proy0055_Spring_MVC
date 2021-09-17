package com.curso.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.curso.domain.Usuario;
import com.curso.domain.repository.UsuariosRepository;
import com.curso.excepcion.RegistroUsuariosException;
import com.curso.excepcion.UsuarioYaExisteException;


@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImpTest {

	LoginService servicio;
	
	@Mock
	UsuariosRepository repositorio;
	
	@Before
	public void antesDeCadaTest() {
		servicio = new LoginServiceImp();
		servicio.setRepositorio(repositorio);
		System.out.println("--- repo es "  + repositorio);
	}
	
	@Test(expected = RegistroUsuariosException.class)
	public void testRegistroUsurioNulo() throws RegistroUsuariosException{
		
		Usuario u = null;
		servicio.registrarUsuario(u);
	}
	
	@Test(expected = RegistroUsuariosException.class)
	public void testRegistroUsurioSinNombre() throws RegistroUsuariosException{
		Usuario u = new Usuario(1,null,"ass");
		servicio.registrarUsuario(u);
	}
	
	@Test(expected = RegistroUsuariosException.class)
	public void testRegistroUsurioSinClave() throws RegistroUsuariosException{
		Usuario u = new Usuario(1,"luis","");
		servicio.registrarUsuario(u);
	}

	@Test(expected = RegistroUsuariosException.class)
	public void testRegistroUsuarioYaExistePorNombre() throws UsuarioYaExisteException, RegistroUsuariosException{
		Usuario u = new Usuario(1,"Luis","1234");
		Mockito.when(repositorio.crear(u))
	       .thenThrow(new UsuarioYaExisteException());
		servicio.registrarUsuario(u);
	}
	
	@Test
	public void testRegistroUsuarioOk() throws UsuarioYaExisteException, RegistroUsuariosException{
		Usuario u = new Usuario(1,"Laura","1234");
		Mockito.when(repositorio.crear(u))
		       .thenReturn(u);
		servicio.registrarUsuario(u);
	}

}
