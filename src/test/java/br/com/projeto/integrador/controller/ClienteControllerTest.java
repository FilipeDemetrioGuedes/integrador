package br.com.projeto.integrador.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class ClienteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deveriaDarErroComStatusNulo() throws Exception {
		URI uri = new URI("/clientes");
		String json = "{\"nome\":\"ClienteTeste\", \"email\":\"emailteste@teste.com\",\"telefone\":\"11199552324\",\"tipoPessoa\":\"FISICA\",\"cpfCnpj\":\"41861187017}\"";
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void deveriaCadastrarClienteComSucesso() throws Exception {
		URI uri = new URI("/clientes");
		String json = "{\"nome\":\"ClienteTeste\", \"email\":\"emailteste@teste.com\",\"telefone\":\"11199552324\",\"tipoPessoa\":\"FISICA\",\"cpfCnpj\":\"41861187017\",\"status\":\"OK\"\"}";
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
}
