package com.rafaelamaral.repository.cliente;

import java.util.List;

import com.rafaelamaral.model.Cliente;
import com.rafaelamaral.repository.filter.ClienteFilter;

public interface ClienteRepositoryQuery {

	public List<Cliente> filtrar(ClienteFilter clienteFilter);
	
}
