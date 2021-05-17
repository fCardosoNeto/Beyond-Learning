package net.codejava.user.controller;

import java.util.List;

import net.codejava.user.entities.User;
import net.codejava.user.repository.UserRepository;
import net.codejava.user.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
	
	private UsuarioService usuariosService;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userRepo.save(user);

		return "register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "users";
	}

	@GetMapping("/showNewUsuariosForm")
	public String showNewUsuariosForm(Model model) {
		model.addAttribute("user", new User());

		return "add_usuarios";
	}

	@PostMapping("/saveUsuarios")
	public String saveUsuarios(@ModelAttribute("usuarios") User usuarios) {
		// salva o usuario no banco
		usuariosService.saveUsuarios(usuarios);
		return "index";
	}
	
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		// pega o usuário do bd
		User usuarios = usuariosService.getUsuariosById(id);

		// set user as a model attribute to pre-populate the form
		model.addAttribute("usuarios", usuarios);
		return "update_usuarios";
	}

	@GetMapping("/deleteUsuarios/{id}")
	public String deleteUsuarios(@PathVariable(value = "id") long id) {

		// chama o método de deletar usuário
		this.usuariosService.deleteUsuariosById(id);
		return "index";

	}

}