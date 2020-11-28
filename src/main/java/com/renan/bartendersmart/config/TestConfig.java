package com.renan.bartendersmart.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.renan.bartendersmart.entities.Categoria;
import com.renan.bartendersmart.entities.ItemPedido;
import com.renan.bartendersmart.entities.Mesa;
import com.renan.bartendersmart.entities.Pagamento;
import com.renan.bartendersmart.entities.Pedido;
import com.renan.bartendersmart.entities.Produto;
import com.renan.bartendersmart.entities.enums.PedidoStatus;
import com.renan.bartendersmart.repositories.CategoriaRepository;
import com.renan.bartendersmart.repositories.ItemPedidoRepository;
import com.renan.bartendersmart.repositories.MesaRepository;
import com.renan.bartendersmart.repositories.PedidoRepository;
import com.renan.bartendersmart.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	 
	@Autowired
	private MesaRepository mesaRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Bedida Alcolica");
		Categoria cat2 = new Categoria(null, "Bebida");
		Categoria cat3 = new Categoria(null, "Lanche"); 		
		
		Produto po1 = new Produto(null, "Caipirinha de Morango", "Uma deliciosa caipirinha com morango.", 14.50);
		Produto po2 = new Produto(null, "Chopp", "Chop da Brahma.", 10.00);
		Produto po3 = new Produto(null, "X-Burguer", "Pao e Hamburger", 13.00);
		Produto po4 = new Produto(null, "Coca-Cola", "Refrigerante de Cola", 5.00);
		Produto po5 = new Produto(null, "Agua com gás", "Aguá gaseficada", 3.00); 
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));		
		produtoRepository.saveAll(Arrays.asList(po1, po2, po3, po4, po5));
		
		po1.getCategorias().add(cat1);
		po2.getCategorias().add(cat1);
		po3.getCategorias().add(cat3);
		po4.getCategorias().add(cat2);
		po5.getCategorias().add(cat2);
		
		produtoRepository.saveAll(Arrays.asList(po1, po2, po3, po4, po5));
		
		Mesa m1 = new Mesa(null, "Mesa1");
		Mesa m2 = new Mesa(null, "Mesa2");
		
		//Pedido p1 = new Pedido(null, Instant.parse("2020-06-20T19:53:07Z"),PedidoStatus.WAITING_PRODUCTION, m1);
		//Pedido p2 = new Pedido(null, Instant.parse("2020-07-21T03:42:10Z"),PedidoStatus.READY, m2);
		//Pedido p3 = new Pedido(null, Instant.parse("2020-07-22T15:21:22Z"),PedidoStatus.DELIVERED, m1); 
		
		Pedido p1 = new Pedido(null, Instant.parse("2020-06-20T19:53:07Z"), m1);
		Pedido p2 = new Pedido(null, Instant.parse("2020-07-21T03:42:10Z"), m2);
		Pedido p3 = new Pedido(null, Instant.parse("2020-07-22T15:21:22Z"), m1); 
		
		mesaRepository.saveAll(Arrays.asList(m1, m2));
		pedidoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		ItemPedido ip1 = new ItemPedido(null, p1, po1, 2, po1.getPrice());
		ItemPedido ip2 = new ItemPedido(null, p1, po3, 1, po3.getPrice());
		ItemPedido ip3 = new ItemPedido(null, p2, po3, 2, po3.getPrice());
		ItemPedido ip4 = new ItemPedido(null, p3, po5, 2, po5.getPrice()); 
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2, ip3, ip4));
		
		Pagamento pag1 = new Pagamento(null, Instant.parse("2019-06-21T19:53:07Z"), p1);
		p1.setPagamento(pag1);
		
		pedidoRepository.save(p1);
	}	
	
}
