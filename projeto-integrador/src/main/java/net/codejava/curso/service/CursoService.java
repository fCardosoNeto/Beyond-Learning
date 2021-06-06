package net.codejava.curso.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.codejava.curso.entities.Curso;


public interface CursoService {
	
	public List<Curso> getAllCurso();
	void saveCurso(Curso curso);
	Curso getCursoById(long id);
	void deleteCursoById(long id);
	Page<Curso> findPaginated(int pageNo, int pageSize);
}