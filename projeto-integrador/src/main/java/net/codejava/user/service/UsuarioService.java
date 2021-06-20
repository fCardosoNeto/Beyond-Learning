package net.codejava.user.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.codejava.user.entities.User;


public interface UsuarioService {

	public List<User> getAllUsuarios();
	void saveUsuarios(User usuarios);
	User getUsuariosById(long id);
	void deleteUsuariosById(long id);
	Page<User> findPaginated(int pageNo, int pageSize);
	
}