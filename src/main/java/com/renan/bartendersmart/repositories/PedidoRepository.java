package com.renan.bartendersmart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renan.bartendersmart.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
