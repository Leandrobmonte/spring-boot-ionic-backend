package com.monteleandro.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//calsse controlador rest que ira responder pelo request mapping
@RestController // local para acessar caminhos
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET) //padrão rest, verbo http para recuperar ou colocar dados como exemplo
	public String listar() {
		return "Rest está funcioando";
	}
}
