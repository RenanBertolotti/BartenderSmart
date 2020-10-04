package com.renan.bartendersmart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.renan.bartendersmart.entities.Produto;
import com.renan.bartendersmart.repositories.ProdutoRepository;

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
		
		return obj.get();
	}
}
