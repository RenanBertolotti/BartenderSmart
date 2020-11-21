package com.renan.bartendersmart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.renan.bartendersmart.entities.ItemPedido;
import com.renan.bartendersmart.repositories.ItemPedidoRepository;
import com.renan.bartendersmart.services.exception.DatabaseException;
import com.renan.bartendersmart.services.exception.ResourceNotFoundException;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository repository;
	
	@GetMapping
	public List<ItemPedido> findAll(){
		return repository.findAll();
	}
	
	@GetMapping
	public ItemPedido findById(Long id) {
		Optional<ItemPedido> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public ItemPedido insert(ItemPedido obj) {
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
}
