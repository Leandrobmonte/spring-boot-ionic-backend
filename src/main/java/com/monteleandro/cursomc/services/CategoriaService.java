package com.monteleandro.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.monteleandro.cursomc.domain.Categoria;
import com.monteleandro.cursomc.repositories.CategoriaRepository;
import com.monteleandro.cursomc.services.excepction.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

//classe para a regra de negócio
@Service
public class CategoriaService {
	
	@Autowired //dependecia aumoticamente instanciada pelo spring
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) throws ObjectNotFoundException{
		Optional<Categoria> obj = repo.findById(id);	
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) throws ObjectNotFoundException {
		find(obj.getId());		
		return repo.save(obj);
	}
	
	public void delete(Integer id){		
		try {
			find(id);
			repo.deleteById(id);
		}catch(DataIntegrityViolationException | ObjectNotFoundException e) {
			throw new DataIntegrityException("não é possivel excluir uma categoria que possui produtos");
		}
	}
}
