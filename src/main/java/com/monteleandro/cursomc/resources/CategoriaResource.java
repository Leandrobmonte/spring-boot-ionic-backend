package com.monteleandro.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.monteleandro.cursomc.domain.Categoria;
//calsse controlador rest que ira responder pelo request mapping
@RestController // local para acessar caminhos
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET) //padrão rest, verbo http para recuperar ou colocar dados como exemplo
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1,"Infromática");
		Categoria cat2 = new Categoria(2,"Escritório");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
	}
}
