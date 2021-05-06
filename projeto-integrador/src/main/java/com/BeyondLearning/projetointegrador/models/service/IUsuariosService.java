package com.BeyondLearning.projetointegrador.models.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.BeyondLearning.projetointegrador.models.entity.Usuarios;

public interface IUsuariosService {

	public List<Usuarios> getAllUsuarios();
	void saveUsuarios(Usuarios usuarios);
	Usuarios getUsuariosById(long id);
	void deleteUsuariosById(long id);
	Page<Usuarios> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
}
