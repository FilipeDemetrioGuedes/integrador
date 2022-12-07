package br.com.projeto.integrador.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

@Entity
@GroupSequenceProvider(ClienteGroupSequenceProvider.class)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	@Enumerated (EnumType.STRING)
	private TipoPessoa tipoPessoa;

	private String telefone;
	@CNPJ(groups = CnpjGroup.class)
	@CPF(groups = CpfGroup.class)
	private String cpfCnpj;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Cliente() {
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Cliente(String nome, String email, String telefone, String cpfCnpj, TipoPessoa tipoPessoa) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cpfCnpj = cpfCnpj;
		this.tipoPessoa = tipoPessoa;
	}

}
