package br.com.projeto.integrador.controller.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class ClienteExistenteException extends DataIntegrityViolationException {

	public String campo;
	private static final long serialVersionUID = 1L;


	
	public ClienteExistenteException (String mensagem ,String campo) {
		super(mensagem);
		this.campo = campo;
	}
}
