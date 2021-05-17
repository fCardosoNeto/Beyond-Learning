package net.codejava.curso.controller;

import java.util.List;

import net.codejava.curso.entities.Curso;
import net.codejava.curso.repository.CursoRepository;
import net.codejava.curso.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CursoController {
	
	
	private CursoService cursoService;
	

	private CursoRepository cursoRepository;

	@PostMapping("/registercurso")
	public String showCursoForm(Model model) {
		model.addAttribute("curso", new Curso());
		
		return "signup_formcurso";
	}

	@GetMapping("/cursos")
	public String listCurso(Model model) {
		List<Curso> listCurso = cursoRepository.findAll();
		model.addAttribute("listCurso", listCurso);

		return "cursos";
	}

	@GetMapping("/showNewCursoForm")
	public String showNewCursoForm(Model model) {
		model.addAttribute("curso", new Curso());

		return "add_curso";
	}

	@PostMapping("/saveCurso")
	public String saveCurso(@ModelAttribute("cursos") Curso cursos) {
		// salva o curso no banco
		cursoService.saveCurso(cursos);
		return "index";
	}

	@GetMapping("/showCursoForUpdate/{id}")
	public String showCursoForUpdate(@PathVariable(value = "id") long id, Model model) {

		// pega o usuário do bd
		Curso curso = cursoService.getCursoById(id);

		// set user as a model attribute to pre-populate the form
		model.addAttribute("curso", curso);
		return "update_curso";
	}

	@DeleteMapping("/deleteCurso/{id}")
	public String deleteCurso(@PathVariable(value = "id") long id) {

		// chama o método de deletar curso
		this.cursoService.deleteCursoById(id);
		return "index";

	}

}