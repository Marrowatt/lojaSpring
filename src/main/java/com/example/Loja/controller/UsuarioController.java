package com.example.Loja.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Loja.model.Usuario;
import com.example.Loja.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository uRepo;
	
	@GetMapping
	public String getUsuario(Model mav) {
		List<Usuario> u = uRepo.findAll();
		mav.addAttribute("usuarios", u);
		return "usuario/index";
	}
	
	@GetMapping("/cadastro")
	public String createUsuario(Model mav) {
		return "usuario/cadastro";
	}
	
	@GetMapping("/{id}")
	public String getUsuarioId(Model mav, @PathVariable("id") Long id) {
		Optional<Usuario> user = uRepo.findById(id);
		mav.addAttribute("usuario", user.get());
		return "usuario/usuarioid";
	}

	@PostMapping
	public String postUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute("error", "Algum campo deve ser obrigatório!");
			return "redirect:/usuario/cadastro";
		}
		
		usuario.setCreated_at(LocalDateTime.now());
		usuario.setUpdated_at(LocalDateTime.now());
		
		uRepo.save(usuario);
		
		return "redirect:/usuario";
	}

	@PutMapping("/{id}")
	public String putUsuario(Usuario usuario, String password, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute("error", "Algum campo deve ser obrigatório!");
			return "redirect:/usuario/" + usuario.getId();
		}
		
		Usuario usuarioBASE = uRepo.findById(usuario.getId()).orElse(null);
		
		usuarioBASE.setName(usuario.getName());
		usuarioBASE.setLogin(usuario.getLogin());
		if(password != null) {
			usuarioBASE.setPassword(password);
		} else {
			usuarioBASE.setPassword(usuarioBASE.getPassword()); // gambiarra
		}
		usuarioBASE.setUpdated_at(LocalDateTime.now());
		
		uRepo.save(usuarioBASE);
		
		attr.addFlashAttribute("success", "Atualizado com sucesso!");
		
		return "redirect:/usuario";
	}

	@GetMapping("/delete/{id}") // DELETE
	public String deleteUsuario(@PathVariable("id") Long id, RedirectAttributes attr) {
		uRepo.deleteById(id);
		attr.addFlashAttribute("success", "Deletado com sucesso!");
		return "redirect:/usuario";
	}
	
}
