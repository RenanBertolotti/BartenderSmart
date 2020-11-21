package com.renan.bartendersmart.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.renan.bartendersmart.entities.Mesa;
import com.renan.bartendersmart.services.MesaService;

@RestController
@RequestMapping(value = "/mesas")
public class MesaResource {
	
	@Autowired
	private MesaService service;
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Mesa>> findAll(){		
		List<Mesa> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseEntity<Mesa> findById(@PathVariable Long id){
		Mesa obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Mesa> insert(@RequestBody Mesa obj){
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin
	@PutMapping(value = "/{id}")
	public ResponseEntity<Mesa> update(@PathVariable Long id, @RequestBody Mesa obj){
		obj = service.update(id, obj);
		
		return ResponseEntity.ok().body(obj);		
	}
}
