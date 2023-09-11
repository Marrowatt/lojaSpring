package com.example.Loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Loja.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value="select * from usuario where login = :login and password = :password", nativeQuery=true)
	Usuario logar(String login, String password);
	
}
