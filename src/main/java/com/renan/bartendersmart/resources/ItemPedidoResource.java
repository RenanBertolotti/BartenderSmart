package com.renan.bartendersmart.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renan.bartendersmart.entities.ItemPedido;
import com.renan.bartendersmart.services.ItemPedidoService;

@RestController
@RequestMapping(value = "/itemPedidos")
public class ItemPedidoResource {
	
	@Autowired
	private ItemPedidoService service;

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<ItemPedido>> findAll(){		
		List<ItemPedido> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseEntity<ItemPedido> findById(@PathVariable Long id){
		ItemPedido obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<ItemPedido> insert(@RequestBody ItemPedido obj){
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getPedido()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
