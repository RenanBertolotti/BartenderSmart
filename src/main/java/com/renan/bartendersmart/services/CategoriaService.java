package com.renan.bartendersmart.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.renan.bartendersmart.entities.Categoria;
import com.renan.bartendersmart.repositories.CategoriaRepository;
import com.renan.bartendersmart.services.exception.DatabaseException;
import com.renan.bartendersmart.services.exception.ResourceNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	@GetMapping
	public Categoria findById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Categoria insert(Categoria obj) {
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
	
	private void updateData(Categoria entidade, Categoria categoria) {
		entidade.setName(categoria.getName());			
	}
	
	public Categoria update(Long id, Categoria categoria) {
		try {
			Categoria entidade = repository.getOne(id);
			
			updateData(entidade, categoria);
			
			return repository.save(entidade);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}
}
