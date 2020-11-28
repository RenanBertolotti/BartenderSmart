package com.renan.bartendersmart.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.renan.bartendersmart.entities.Pedido;
import com.renan.bartendersmart.repositories.PedidoRepository;
import com.renan.bartendersmart.services.exception.DatabaseException;
import com.renan.bartendersmart.services.exception.ResourceNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	@GetMapping
	public List<Pedido> findAll(){
		return repository.findAll();
	}
	
	@GetMapping
	public Pedido findById(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Pedido insert(Pedido obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
			
	}
	
	private void updateData(Pedido entidade, Pedido pedido) {
		entidade.setMesa(pedido.getMesa());		
		entidade.setMoment(pedido.getMoment());	
		//entidade.setPedidoStatus(pedido.getPedidoStatus());
	}
	
	public Pedido update(Long id, Pedido pedido) {
		try {
			Pedido entidade = repository.getOne(id);
			
			updateData(entidade, pedido);
			
			return repository.save(entidade);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}
}
