package br.com.projeto.integrador.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.projeto.integrador.modelo.Cliente;

public class ClienteDto {

	private Long id;
	private String nome;
	private String email;
	private String tipoPessoa;
	private String telefone;
	private String cpfCnpj;
	
	public String getEmail() {
		return email;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}


	public Long getId() {
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
	}

	public static List<ClienteDto> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}

}
