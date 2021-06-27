package net.codejava.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.codejava.user.entities.User;
import net.codejava.user.repository.UserRepository;
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

	@Autowired
	private UserRepository usuariosService;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/index")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		return "login";
	}
	
	// finaliza sessão
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
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
		usuariosService.save(usuarios);
		return "redirect:/users";
	}
	
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		// pega o usuário do bd
		User usuarios = usuariosService.getOne(id);

		// set user as a model attribute to pre-populate the form
		model.addAttribute("usuarios", usuarios);
		return "update_usuarios";
	}

	@GetMapping("/deleteUsuarios/{id}")
	public String deleteUsuarios(@PathVariable(value = "id") long id) {

		// chama o método de deletar usuário
		this.usuariosService.deleteById(id);
		return "redirect:/users";

	} 
	@GetMapping("/calendar")
	public String viewCalendarPage() {
		return "calendar";
	}
	
	@GetMapping("/menu_aluno")
	public String viewMenuPage() {
		return "menu_aluno";
	}

}