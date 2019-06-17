package com.monteleandro.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monteleandro.cursomc.domain.Cliente;
import com.monteleandro.cursomc.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

//classe para a regra de negócio
@Service
public class ClienteService {
	
	@Autowired //dependecia aumoticamente instanciada pelo spring
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) throws ObjectNotFoundException{
		Optional<Cliente> obj = repo.findById(id);	
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}

}
