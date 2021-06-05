package net.codejava.curso.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.codejava.curso.entities.Curso;
import net.codejava.curso.repository.CursoRepository;
import net.codejava.upload.entities.Document;
import net.codejava.upload.repository.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CursoController {
	
	@Autowired
	private DocumentRepository repo;
	
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
		return "redirect:/cursos";

	}
	
	
	// não remover, precisei mapear o catálogo de cursos, sem esse @getmapping /menu_cursos ele não "encontra" a html
	@GetMapping("/menu_cursos")
	public String listCurso1(Model model) {
		List<Curso> listCurso = cursoRepository.findAll();
		model.addAttribute("listCurso", listCurso);

		return "menu_cursos";
	}
	
	@GetMapping("/teste1")
    public void downloadFile(@Param("id") Long id, HttpServletResponse response) throws Exception {
        Optional<Document> result = repo.findById(id);
        if (!result.isPresent()) {
            throw new Exception("Não foi possível achar documento com o ID: " +id);
        }

        Document document = result.get();


        // dentro do IF precisa colocar no "setContentType" o tipo de arquivo, atualmente só está "reconhecendo" para download arquivos PNG e PDF
        if(document.getName().startsWith("matematica")) {
            response.setContentType("text/plain"); // tipo de conteudo imagem png

        }else if(document.getName().endsWith("pdf")){
            response.setContentType("application/pdf"); // tipo de conteudo pdf

        }else if (document.getName().endsWith("mp4")) { // conteudo arquivo mp4
            response.setContentType("video/mp4");
        }else if (document.getName().endsWith("jpeg")) { // conteudo arquivo imagem jpeg
            response.setContentType("image/jpeg");
        }

        String headerKey = "Content-Disponsion";
        String headerValue = "attachment; filename=" +document.getName();

        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream(); // para mandar um documento para a data binária

        outputStream.write(document.getContent());
        outputStream.close();
    }
	

    // mapeamento para mostrar os arquivos na tela teste 
    @GetMapping("/teste")
    public String viewHomePage1(Model model) {
        List<Document> listDocs = repo.findAll(); // para mostrar os arquivos na tela
        model.addAttribute("listDocs", listDocs);
        return "teste"; // direcionar para a pagina home 
    }
	

}