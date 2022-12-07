package br.com.projeto.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projeto.integrador.modelo.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT cliente FROM Cliente cliente WHERE cliente.cpfCnpj =:cpfCnpj")
	Cliente findByCPFCNPJ(@Param("cpfCnpj")String cpfCnpj);

	
}
