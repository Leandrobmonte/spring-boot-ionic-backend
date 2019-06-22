package com.monteleandro.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monteleandro.cursomc.domain.Pedido;
import com.monteleandro.cursomc.repositories.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

//classe para a regra de negócio
@Service
public class PedidoService {
	
	@Autowired //dependecia aumoticamente instanciada pelo spring
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) throws ObjectNotFoundException{
		Optional<Pedido> obj = repo.findById(id);	
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

	}

}