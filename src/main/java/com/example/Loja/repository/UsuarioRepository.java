package com.example.Loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Loja.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByLogin(String login);
}
