package net.codejava.upload.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.util.StringUtils;

import net.codejava.upload.entities.Document;
import net.codejava.upload.repository.DocumentRepository;

@Controller
public class UploadController {

	

		@Autowired
		private DocumentRepository repo;
		
		@GetMapping("/")
		public String viewHomePage(Model model) {
			List<Document> listDocs = repo.findAll(); // para mostrar os arquivos na tela
			model.addAttribute("listDocs", listDocs);
			return "home"; // direcionar para a pagina home 
		}
		
		@PostMapping("/upload")
		public String uploadFile(@RequestParam("document") MultipartFile multipartFile,
				RedirectAttributes ra) throws IOException { //redirecionar o botão, usando referencia para o atributo
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename()); //utilizado uma classe util do spring para limpar
			
			Document document = new Document ();
			document.setName(fileName);
			document.setContent(multipartFile.getBytes());
			document.setSize(multipartFile.getSize());
			document.setUploadTime(new Date());
			
			repo.save(document);
			
			ra.addFlashAttribute("message", "O arquivo foi upado com sucesso!");
			
			return "redirect:/upload";
			
		} 
		
		@GetMapping("/download")
		public void downloadFile(@Param("id") Long id, HttpServletResponse response) throws Exception {
			Optional<Document> result = repo.findById(id);
			if (!result.isPresent()) {
				throw new Exception("Não foi possível achar documento com o ID: " +id);
			}
			
			Document document = result.get();
			
			response.setContentType("application/octet-stream"); // tipo de conteudo 
			String headerKey = "Content-Disponsion";
			String headerValue = "attachment; filename=" +document.getName();
			
			response.setHeader(headerKey, headerValue);
			
			ServletOutputStream outputStream = response.getOutputStream(); // para mandar um documento para a data binária
			
			outputStream.write(document.getContent());
			outputStream.close();
		}
	}
