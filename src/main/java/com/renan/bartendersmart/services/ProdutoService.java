package com.renan.bartendersmart.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.renan.bartendersmart.entities.Produto;
import com.renan.bartendersmart.repositories.ProdutoRepository;
import com.renan.bartendersmart.services.exception.DatabaseException;
import com.renan.bartendersmart.services.exception.ResourceNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public List<Produto> findAll(){
		return repository.findAll();
	}
	
	@GetMapping
	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Produto insert(Produto obj) {
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
	
	private void updateData(Produto entidade, Produto produto) {
		entidade.setName(produto.getName());
		entidade.setDescricao(produto.getDescricao());
		entidade.setPrice(produto.getPrice());
	}
	
	public Produto update(Long id, Produto produto) {
		try {
			Produto entidade = repository.getOne(id);
			
			updateData(entidade, produto);
			
			return repository.save(entidade);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}
}
