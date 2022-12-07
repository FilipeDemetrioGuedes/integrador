package br.com.projeto.integrador.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.projeto.integrador.modelo.Cliente;
@DataJpaTest
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Test
	public void deveriaCarregarUmClienteAoBuscarPeloDocumento() {
		String numeroDocumento = "51306198038";
		Cliente cliente = clienteRepository.findByCPFCNPJ(numeroDocumento);
		Assert.assertNotNull(cliente);
		Assert.assertEquals(numeroDocumento, cliente.getCpfCnpj());
		
	}
	
	@Test
	public void NaoDeveriaCarregarUmClienteAoBuscarPeloDocumentoQueNaoEstaCadastrado() {
		String numeroDocumento = "00723648541";
		Cliente cliente = clienteRepository.findByCPFCNPJ(numeroDocumento);
		Assert.assertNull(cliente);

}
}
