package net.codejava;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuariosServiceImpl implements UsuarioService{

	
	@Autowired
	private UserRepository usuariosRepository;
	
	@Override
	public List<User> getAllUsuarios() {
		return usuariosRepository.findAll();
	}

	@Override
	public void saveUsuarios(User usuarios) {
		this.usuariosRepository.save(usuarios);
		
	}

	@Override
	public User getUsuariosById(long id) {
		Optional<User> optional = usuariosRepository.findById(id);
		User usuarios = null;
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
	public Page<User> findPaginated(int pageNo, int pageSize) {
		
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.usuariosRepository.findAll(pageable);
	}

}