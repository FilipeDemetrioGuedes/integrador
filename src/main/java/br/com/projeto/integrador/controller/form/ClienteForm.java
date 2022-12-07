package br.com.projeto.integrador.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import br.com.projeto.integrador.modelo.Cliente;
import br.com.projeto.integrador.repository.ClienteRepository;

public class ClienteForm {
	@NotNull @NotEmpty 
	private String nome;
	private String email;
	@NotNull @NotEmpty 
	private String telefone;

	@NotNull @NotEmpty @CPF
	private String cpfCnpj;
	
	@NotNull @NotEmpty 
	private String tipoPessoa;
	
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
	
	public String getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public Cliente converter(ClienteRepository clienteRepository) {
		
		return new Cliente(nome,email,telefone,cpfCnpj,tipoPessoa);
	}
	
}
