package net.codejava.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import net.codejava.curso.entities.Curso;
import net.codejava.curso.repository.CursoRepository;


public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	@Override
	public List<Curso> getAllCurso() {
		return cursoRepository.findAll();
	}

	@Override
	public void saveCurso(Curso curso) {
		this.cursoRepository.save(curso);

	}

	@Override
	public Curso getCursoById(long id) {
		Optional<Curso> optional = cursoRepository.findById(id);
		Curso curso = null;
		if (optional.isPresent()) {
			curso = optional.get();
		} else {
			throw new RuntimeException(" Curso não Encontrado pelo ID :: " + id);
		}
		return curso;
	}

	@Override
	public void deleteCursoById(long id) {

		this.cursoRepository.deleteById(id);

	}

	@Override
	public Page<Curso> findPaginated(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.cursoRepository.findAll(pageable);
	}

}
