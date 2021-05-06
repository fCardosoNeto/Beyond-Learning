package com.BeyondLearning.projetointegrador.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BeyondLearning.projetointegrador.models.entity.Usuarios;
import com.BeyondLearning.projetointegrador.models.repository.UsuariosRepository;

@Service
public class UsuariosServiceImpl implements IUsuariosService{

	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Override
	public List<Usuarios> getAllUsuarios() {
		return usuariosRepository.findAll();
	}

	@Override
	public void saveUsuarios(Usuarios usuarios) {
		this.usuariosRepository.save(usuarios);
		
	}

	@Override
	public Usuarios getUsuariosById(long id) {
		Optional<Usuarios> optional = usuariosRepository.findById(id);
		Usuarios usuarios = null;
		if(optional.isPresent()) {
			usuarios = optional.get();
		}else {
			throw new RuntimeException(" Usuário não Encontrado pelo ID :: "+ id);
		}
		return usuarios;
	}

	@Override
	public void deleteUsuariosById(long id) {
		
		this.usuariosRepository.deleteById(id);
		
	}

	@Override
	public Page<Usuarios> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.usuariosRepository.findAll(pageable);
	}

}
