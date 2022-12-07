package br.com.projeto.integrador.repository;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

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
	public void naoDeveriaCarregarUmClienteAoBuscarPeloDocumentoQueNaoEstaCadastrado() {
		String numeroDocumento = "00723648541";
		Cliente cliente = clienteRepository.findByCPFCNPJ(numeroDocumento);
		Assert.assertNull(cliente);

	}

}
