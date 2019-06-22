package com.monteleandro.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.monteleandro.cursomc.domain.Pedido;
import com.monteleandro.cursomc.services.PedidoService;

import javassist.tools.rmi.ObjectNotFoundException;
//calsse controlador rest que ira responder pelo request mapping
@RestController // local para acessar caminhos
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET) //padrão rest, verbo http para recuperar ou colocar dados como exemplo
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		
		Pedido obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	
	}
}


