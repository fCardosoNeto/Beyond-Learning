package net.codejava.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.codejava.curso.entities.Curso;



@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
//@Query("SELECT c FROM Curso c WHERE c.nomecurso = ?1")	
	List<Curso> findAll();

}
