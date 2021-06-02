package net.codejava.curso.controller;

import java.util.List;

import net.codejava.curso.entities.Curso;
import net.codejava.curso.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CursoController {
	
	
	//private CursoService cursoService;
	
	@Autowired
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
		cursoRepository.save(cursos);
		return "redirect:/cursos";
	}

	@GetMapping("/showCursoForUpdate/{id}")
	public String showCursoForUpdate(@PathVariable(value = "id") long id, Model model) {

		// pega o curso do bd
		Curso curso = cursoRepository.getOne(id);

		// set user as a model attribute to pre-populate the form
		model.addAttribute("curso", curso);
		return "update_curso";
	}

	@GetMapping("/deleteCurso/{id}")
	public String deleteCurso(@PathVariable(value = "id") long id, Model model) {

		// chama o método de deletar cursos
		this.cursoRepository.deleteById(id);
		return "index";

	}
	
	// não remover, precisei mapear o catálogo de cursos, sem esse @getmapping /menu_cursos ele não "encontra" a html
	@GetMapping("/menu_cursos")
	public String listCurso1(Model model) {
		List<Curso> listCurso = cursoRepository.findAll();
		model.addAttribute("listCurso", listCurso);

		return "menu_cursos";
	}
	

}