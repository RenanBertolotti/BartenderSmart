package com.renan.bartendersmart.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.renan.bartendersmart.entities.Mesa;
import com.renan.bartendersmart.repositories.MesaRepository;
import com.renan.bartendersmart.services.exception.DatabaseException;
import com.renan.bartendersmart.services.exception.ResourceNotFoundException;

@Service
public class MesaService {
	
	@Autowired
	private MesaRepository repository;
	
	@GetMapping
	public List<Mesa> findAll(){
		return repository.findAll();
	}
	
	@GetMapping
	public Mesa findById(Long id) {
		Optional<Mesa> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Mesa insert(Mesa obj) {
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
	
	private void updateData(Mesa entidade, Mesa mesa) {
		entidade.setName(mesa.getName());		
	}
	
	public Mesa update(Long id, Mesa mesa) {
		try {
			Mesa entidade = repository.getOne(id);
			
			updateData(entidade, mesa);
			
			return repository.save(entidade);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}
}
