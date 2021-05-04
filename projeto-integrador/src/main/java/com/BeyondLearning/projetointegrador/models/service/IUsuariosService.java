package com.BeyondLearning.projetointegrador.models.service;

import java.util.List;

import com.BeyondLearning.projetointegrador.models.entity.Usuarios;

public interface IUsuariosService {

	public List<Usuarios> listarTodos();
	public void guarda(Usuarios usuarios);
	public Usuarios buscarPorId(Long id);
	public void eliminar(Long id);
	
}
