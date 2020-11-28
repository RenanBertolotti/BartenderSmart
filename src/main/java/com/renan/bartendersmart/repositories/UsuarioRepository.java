package com.renan.bartendersmart.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renan.bartendersmart.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
	
}
