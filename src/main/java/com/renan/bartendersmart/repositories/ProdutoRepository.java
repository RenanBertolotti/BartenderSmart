package com.renan.bartendersmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renan.bartendersmart.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
