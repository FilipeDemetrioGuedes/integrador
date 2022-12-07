package br.com.projeto.integrador.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import br.com.projeto.integrador.controller.form.AtualizacaoClienteForm;
import br.com.projeto.integrador.controller.form.ClienteForm;
import br.com.projeto.integrador.modelo.Cliente;
import br.com.projeto.integrador.modelo.GerarEValidarCpfCnpj;
import br.com.projeto.integrador.modelo.TipoPessoa;
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
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm clienteForm,
			UriComponentsBuilder uriBuilder) {
		Cliente cliente = clienteForm.converter(clienteRepository);
		if (findByCPFCNPJ(clienteForm) !=null) {
			throw new DataIntegrityViolationException("CPJ ja Cadastrado!");
		}
		
		if(!verificarDocumento(cliente)) throw new DataIntegrityViolationException("CPF/CNPJ informado nao é Valido!");
		
		clienteRepository.save(cliente);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> detalhar(@PathVariable Long id) {
		Optional<Cliente> topico = clienteRepository.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new ClienteDto(topico.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id,
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
	public ResponseEntity<ClienteDto> remover(@PathVariable Long id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}
	public Cliente findByCPFCNPJ(ClienteForm clienteForm) {
		Cliente cliente = clienteRepository.findByCPFCNPJ(clienteForm.getCpfCnpj());
		if(cliente !=null) {
			return cliente;
		}
		return null;
	}
	
	
	public boolean verificarDocumento(Cliente cliente) {
		GerarEValidarCpfCnpj gerador = new GerarEValidarCpfCnpj();
		if (cliente.getTipoPessoa() == TipoPessoa.FISICA) {
			return gerador.isCPF(cliente.getCpfCnpj());
		} else if (cliente.getTipoPessoa() == TipoPessoa.JURIDICA) {
			return gerador.isCNPJ(cliente.getCpfCnpj());
		} else {
			return false;
		}
	}
	
}