package com.monteleandro.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monteleandro.cursomc.domain.Categoria;
import com.monteleandro.cursomc.repositories.CategoriaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

//classe para a regra de negócio
@Service
public class CategoriaService {
	
	@Autowired //dependecia aumoticamente instanciada pelo spring
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) throws ObjectNotFoundException{
		Optional<Categoria> obj = repo.findById(id);	
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}

}
