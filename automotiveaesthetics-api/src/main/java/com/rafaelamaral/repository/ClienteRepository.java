package com.rafaelamaral.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rafaelamaral.model.Cliente;
import com.rafaelamaral.repository.cliente.ClienteRepositoryQuery;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Integer> , ClienteRepositoryQuery{
	
	@Query("SELECT c FROM Cliente c WHERE c.cpf = ?1")
	public Cliente findByCpf(String cpf);
	
 
}
