package com.BeyondLearning.projetointegrador.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BeyondLearning.projetointegrador.models.entity.Usuarios;
import com.BeyondLearning.projetointegrador.models.repository.UsuariosRepository;


@Service
public class UsuariosServiceImpl implements IUsuariosService {
	
	@Autowired
	private UsuariosRepository usuarioRepository;
	
	@Override
	public List<Usuarios> listarTodos() {
		return (List<Usuarios>) usuarioRepository.findAll();	
	}

	@Override
	public void guarda(Usuarios usuarios) {
		usuarioRepository.save(usuarios);
	}

	@Override
	public Usuarios buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Long id) {
		usuarioRepository.deleteById(id);
	}

}
