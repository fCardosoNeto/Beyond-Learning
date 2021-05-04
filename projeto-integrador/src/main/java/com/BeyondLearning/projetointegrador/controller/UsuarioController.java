package com.BeyondLearning.projetointegrador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.BeyondLearning.projetointegrador.models.entity.Usuarios;
import com.BeyondLearning.projetointegrador.models.service.IUsuariosService;

@Controller
@RequestMapping("views/usuarios")
public class UsuarioController {
	
	@Autowired
	private IUsuariosService usuariosService;
	
	@GetMapping("/")
	public String listarUser(Model model) {
		
		List<Usuarios> listadosUsuarios = usuariosService.listarTodos();
		
		model.addAttribute("titulo", "Lista de Usuarios");
		model.addAttribute("usuarios", listadosUsuarios);
		
		return "/views/usuarios/listar";
	}
	
}
