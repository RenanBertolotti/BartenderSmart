package com.renan.bartendersmart.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.CrossOrigin;
=======
>>>>>>> eb9b36b2e95a5b57dbee7aa5560576f9173806f7
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
<<<<<<< HEAD

=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> eb9b36b2e95a5b57dbee7aa5560576f9173806f7
import com.renan.bartendersmart.entities.Categoria;
import com.renan.bartendersmart.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll(){		
		List<Categoria> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id){
		Categoria obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Categoria> insert(@RequestBody Categoria obj){
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
	public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria obj){
		obj = service.update(id, obj);
		
		return ResponseEntity.ok().body(obj);		
	}
}
