package br.com.projeto.integrador.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.integrador.controller.dto.ClienteDto;
import br.com.projeto.integrador.controller.exception.ClienteExistenteException;
import br.com.projeto.integrador.controller.form.AtualizacaoClienteForm;
import br.com.projeto.integrador.modelo.Cliente;
import br.com.projeto.integrador.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")

public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<ClienteDto> lista() {
		List<Cliente> clientes = clienteRepository.findAll();
		return ClienteDto.converter(clientes);
	}

	@PostMapping
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid Cliente cliente,
			UriComponentsBuilder uriBuilder)  {
		
		

		if (findByCPFCNPJ(cliente) != null) {

			if (cliente.getTipoPessoa().isFisica()) {

				throw new ClienteExistenteException("CPF Ja Cadastrado!", "CPF");
			} else {

				throw new ClienteExistenteException("CNPJ Ja Cadastrado!", "CNPJ");
			}

		}
			clienteRepository.save(cliente);
	
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> detalhar(@PathVariable String id) {
		Optional<Cliente> topico = clienteRepository.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new ClienteDto(topico.get()));
		}

		return ResponseEntity.notFound().build();
	}
	@GetMapping("/pendentes")
	public ResponseEntity<List<Cliente>> buscarPendente() {
		List<Cliente> topico = clienteRepository.findByStatus();
	
			return ResponseEntity.ok(topico);
		

	}
	
	
	@GetMapping("/excluir/{id}")
	public ResponseEntity<String> excluirApp(@PathVariable String id) {
		
		try {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok("Excluido");
		} catch (Exception e) {
			return ResponseEntity.ok("Falhou");
		}
			
		

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDto> atualizar(@PathVariable String id,
			@RequestBody @Valid AtualizacaoClienteForm form) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			Cliente cliente = form.atualizar(id, clienteRepository);
			return ResponseEntity.ok(new ClienteDto(cliente));
		}

		return ResponseEntity.notFound().build();

	}
	
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDto> atualizarApp(@PathVariable String id,
			@RequestBody @Valid AtualizacaoClienteForm form) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			Cliente cliente = form.atualizar(id, clienteRepository);
			return ResponseEntity.ok(new ClienteDto(cliente));
		}

		return ResponseEntity.notFound().build();

	}


	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDto> remover(@PathVariable String id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}

	public Cliente findByCPFCNPJ(Cliente cliente) {
		 Cliente cliente2 = clienteRepository.findByCPFCNPJ(cliente.getCpfCnpj());
		if (cliente2 != null) {
			return cliente2;
		}
		return null;
	}

}