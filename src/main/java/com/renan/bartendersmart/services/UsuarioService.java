package com.renan.bartendersmart.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.renan.bartendersmart.entities.Usuario;
import com.renan.bartendersmart.repositories.UsuarioRepository;
import com.renan.bartendersmart.services.exception.DatabaseException;
import com.renan.bartendersmart.services.exception.ResourceNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	@GetMapping
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Usuario insert(Usuario obj) {
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
	
	public void check(Usuario obj) {
		Optional<Usuario> result = repository.findByEmail(obj.getEmail());
		
		if(result.isPresent()) {
			throw new ResourceNotFoundException(obj);
		}
	}
	
	private void updateData(Usuario entidade, Usuario usuario) {
		entidade.setNome(usuario.getNome());		
	}
	
	public Usuario update(Long id, Usuario usuario) {
		try {
			Usuario entidade = repository.getOne(id);
			
			updateData(entidade, usuario);
			
			return repository.save(entidade);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}
}
