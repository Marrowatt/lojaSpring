package com.example.Loja.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Loja.model.Usuario;
import com.example.Loja.repository.UsuarioRepository;
import com.example.Loja.service.UsuarioService;

public class UsuarioServiceImplement implements UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findById(long id) {
		return usuarioRepository.findById(id).get();
	}

	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Usuario deleteById(long id) {
		return deleteById(id);
	}
}
