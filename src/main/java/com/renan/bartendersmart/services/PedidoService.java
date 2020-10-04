package com.renan.bartendersmart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.renan.bartendersmart.entities.Pedido;
import com.renan.bartendersmart.repositories.PedidoRepository;

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
		
		return obj.get();
	}
}
