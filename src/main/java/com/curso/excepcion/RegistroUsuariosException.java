package com.curso.excepcion;

public class RegistroUsuariosException extends Exception {


	public RegistroUsuariosException(String message) {
		super(message);
	}

	
	public RegistroUsuariosException(String message, Throwable cause) {
		super(message, cause);
	}

}
