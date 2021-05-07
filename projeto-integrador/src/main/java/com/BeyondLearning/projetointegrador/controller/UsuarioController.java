package com.BeyondLearning.projetointegrador.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.BeyondLearning.projetointegrador.models.entity.Usuarios;
import com.BeyondLearning.projetointegrador.models.service.IUsuariosService;

@Controller

public class UsuarioController {
	
	@Autowired
	private IUsuariosService usuariosService;
	
	// mostra a lista dos usuários
	@GetMapping("/")
	public String viewHomePage(Model model) {
	
		return findPaginated(1, model);
		
	}

	@GetMapping("/showNewUsuariosForm")	
	public String showNewUsuariosForm(Model model) {
		// create model attribute to bind form data
		Usuarios usuarios = new Usuarios();
		model.addAttribute("usuarios", usuarios);
		return "new_usuarios";		
	}
	
	
	@PostMapping("/saveUsuarios")
	public String saveUsuarios(@ModelAttribute("usuarios") Usuarios usuarios) {
		// salva o usuario no banco
		usuariosService.saveUsuarios(usuarios);
		return "index";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// pega o usuário do bd
		Usuarios usuarios = usuariosService.getUsuariosById(id);
		
		// set user as a model attribute to pre-populate the form
		model.addAttribute("usuarios",usuarios);
		return "update_usuarios";
	}
	
	
	@GetMapping("/deleteUsuarios/{id}")
	public String deleteUsuarios(@PathVariable (value = "id") long id) {
		
		// chama o método de deletar usuário
		this.usuariosService.deleteUsuariosById(id);
		return "index";
		
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
			Model model) {
		int pageSize = 5;
		
		Page<Usuarios> page = usuariosService.findPaginated(pageNo, pageSize);
		List<Usuarios> listUsuarios = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("listUsuarios", listUsuarios);
		
		return "index";
		
	}
	
	
}