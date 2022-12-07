package br.com.projeto.integrador.modelo;

public enum TipoPessoa {

	
	FISICA("Física", "CPF" , "000.000.000-00" , CpfGroup.class),
	JURIDICA("Júridica", "CNPJ" , "00.000.000/0000-00" , CnpjGroup.class);
	
	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> group;
	
	private TipoPessoa() {
		
	}
	private TipoPessoa(String descricao, String documento, String mascara, Class<?> group) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.group = group;
	}


	public String getDescricao() {
		return descricao;
	}


	public String getDocumento() {
		return documento;
	}


	public String getMascara() {
		return mascara;
	}


	public Class<?> getGroup() {
		return group;
	}
	
	public boolean isFisica() {
		return this== FISICA;
	}
	
}
