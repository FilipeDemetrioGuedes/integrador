package br.com.projeto.integrador.controller.form;

import javax.validation.constraints.NotEmpty;

import br.com.projeto.integrador.modelo.Cliente;
import br.com.projeto.integrador.repository.ClienteRepository;

public class AtualizacaoClienteForm {

	private String email;
	@NotEmpty
	private String status;
	@NotEmpty
	private String telefone;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Cliente atualizar(String id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getOne(id);
		cliente.setEmail(this.email);
		cliente.setTelefone(this.telefone);
		cliente.setStatus(this.status);
		return cliente;
	}
}
