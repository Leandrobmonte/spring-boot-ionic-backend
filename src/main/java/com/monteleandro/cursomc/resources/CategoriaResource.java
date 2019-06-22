package com.monteleandro.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.monteleandro.cursomc.domain.Categoria;
import com.monteleandro.cursomc.dto.CategoriaDTO;
import com.monteleandro.cursomc.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;
//calsse controlador rest que ira responder pelo request mapping
@RestController // local para acessar caminhos
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET) //padrão rest, verbo http para recuperar ou colocar dados como exemplo
	public ResponseEntity<Categoria> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	//mappeamento para criar nova requisição de categoria
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) throws ObjectNotFoundException{
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE) //padrão rest, verbo http para recuperar ou colocar dados como exemplo
	public ResponseEntity<Categoria> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(method = RequestMethod.GET) //padrão rest, verbo http para recuperar ou colocar dados como exemplo
	public ResponseEntity<List<CategoriaDTO>> findAll(){		
		List<Categoria> list = service.findAll();
		//List<CategoriaDTO> listDto = (List<CategoriaDTO>) list.stream().map(obj -> ((Stream<Categoria>) new CategoriaDTO(obj)).collect(Collectors.toList()));
		List<CategoriaDTO> listDto = new ArrayList<CategoriaDTO>();
		for(Categoria c : list) {
			listDto.add(new CategoriaDTO(c));
			}
		return ResponseEntity.ok().body(listDto);	
	}	
}


