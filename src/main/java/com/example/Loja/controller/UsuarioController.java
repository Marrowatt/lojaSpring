package com.example.Loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Loja.model.Usuario;
import com.example.Loja.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping("/logar")
	String logar (Usuario usuario) {
		Usuario user = usuarioRepository.logar(usuario.getLogin(), usuario.getPassword());
		
		if (user != null) {
			return "redirect:/marca";
		}
		
		return "redirect:/logar";
	}
	
}
