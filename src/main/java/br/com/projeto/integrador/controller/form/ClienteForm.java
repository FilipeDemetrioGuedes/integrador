package br.com.projeto.integrador.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.projeto.integrador.modelo.Cliente;
import br.com.projeto.integrador.modelo.TipoPessoa;
import br.com.projeto.integrador.repository.ClienteRepository;

public class ClienteForm {
	@NotNull @NotEmpty 
	private String nome;
	private String email;
	@NotNull @NotEmpty 
	private String telefone;

	@NotNull @NotEmpty 
	private String cpfCnpj;
	
	@NotNull @NotEmpty 
	private TipoPessoa tipoPessoa;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public Cliente converter(ClienteRepository clienteRepository) {
		
		return new Cliente(nome,email,telefone,cpfCnpj,tipoPessoa);
	}
	
}
