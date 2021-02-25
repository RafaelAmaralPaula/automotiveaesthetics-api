package com.rafaelamaral.repository.cliente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.rafaelamaral.model.Cliente;
import com.rafaelamaral.repository.filter.ClienteFilter;

public class ClienteRepositoryQueryImpl implements ClienteRepositoryQuery{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<Cliente> filtrar(ClienteFilter clienteFilter) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		Root<Cliente> root = criteriaQuery.from(Cliente.class);
		
		
		
		
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	

}
