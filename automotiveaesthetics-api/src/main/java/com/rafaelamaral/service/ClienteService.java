package com.rafaelamaral.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rafaelamaral.model.Cliente;
import com.rafaelamaral.repository.ClienteRepository;
import com.rafaelamaral.service.exception.ClienteJaCadastradoException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvar(Cliente cliente) {
		//REGRA DE NEGÓCIO
		buscarPeloCpf(cliente.getCpf());
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente alterar(Integer codigo , Cliente cliente) {
		Cliente clienteExistente = buscarPeloCodigo(codigo);
		
		BeanUtils.copyProperties(cliente, clienteExistente , "codigo");
		
		return clienteRepository.save(clienteExistente);
		
	}
	
	public Cliente buscarPeloCodigo(Integer codigo) {
		Optional<Cliente> clienteExistente =clienteRepository.findById(codigo);
		
		if(!clienteExistente.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return clienteExistente.get();
	}
	
	private void buscarPeloCpf(String cpf) {
		Cliente clienteExistente = clienteRepository.findByCpf(cpf);
		
		if(clienteExistente != null) {
			throw new ClienteJaCadastradoException("Já existe um cliente com esse CPF.Por favor tente com outro CPF");
		}
		
	
		
		
	}

}
