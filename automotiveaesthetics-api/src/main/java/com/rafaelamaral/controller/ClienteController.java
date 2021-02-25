package com.rafaelamaral.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelamaral.event.RecursoCriadoEvent;
import com.rafaelamaral.model.Cliente;
import com.rafaelamaral.repository.ClienteRepository;
import com.rafaelamaral.repository.filter.ClienteFilter;
import com.rafaelamaral.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Cliente> listar(ClienteFilter clienteFilter) {
		return clienteRepository.filtrar(clienteFilter);

	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Cliente> buscarPeloCodigo(@PathVariable Integer codigo) {

		Optional<Cliente> clienteEncontrado = clienteRepository.findById(codigo);

		if (!clienteEncontrado.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(clienteEncontrado.get());
	}

	@PostMapping
	public ResponseEntity<Cliente> salvar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {

		Cliente clienteSalvo = clienteService.salvar(cliente);

		publisher.publishEvent(new RecursoCriadoEvent(clienteSalvo.getCodigo(), response, this));

		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);

	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Cliente> alterar(@PathVariable Integer codigo, @Valid @RequestBody Cliente cliente) {
		Cliente clienteAtualizado = clienteService.alterar(codigo, cliente);

		return ResponseEntity.ok(clienteAtualizado);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Integer codigo) {
		clienteRepository.deleteById(codigo);

		return ResponseEntity.noContent().build();
	}

}
