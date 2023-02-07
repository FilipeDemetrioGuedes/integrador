package br.com.projeto.integrador.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.integrador.modelo.Cliente;
import br.com.projeto.integrador.modelo.TipoPessoa;

public class ClienteDto {

	private String id;
	private String nome;
	private String email;
	private TipoPessoa tipoPessoa;
	private String telefone;
	private String cpfCnpj;
	private String status;
	
	public String getStatus() {
		return status;
	}

	public String getEmail() {
		return email;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}


	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		this.cpfCnpj = cliente.getCpfCnpj();
		this.tipoPessoa = cliente.getTipoPessoa();
		this.status = cliente.getStatus();
	}

	public static List<ClienteDto> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}

}
