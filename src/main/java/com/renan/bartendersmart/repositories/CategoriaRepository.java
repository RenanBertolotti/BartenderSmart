package com.renan.bartendersmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renan.bartendersmart.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
