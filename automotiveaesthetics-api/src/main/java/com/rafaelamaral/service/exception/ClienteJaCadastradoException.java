package com.rafaelamaral.service.exception;

public class ClienteJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ClienteJaCadastradoException(String mensagem) {
		super(mensagem);
	}
	
	public ClienteJaCadastradoException(String mensagem , Throwable causa) {
		super(mensagem , causa);
	}
	

}
