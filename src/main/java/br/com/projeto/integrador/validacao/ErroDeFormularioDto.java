package br.com.projeto.integrador.validacao;

public class ErroDeFormularioDto {

	private String campo;
	private String erro;

	public ErroDeFormularioDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public ErroDeFormularioDto(long currentTimeMillis, int i, String message) {
		this.erro = message;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
