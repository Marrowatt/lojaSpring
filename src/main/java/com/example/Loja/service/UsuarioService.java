package com.example.Loja.service;

import java.util.List;

import com.example.Loja.model.Usuario;

public interface UsuarioService {

    List<Usuario> findAll();

    Usuario findById(long id);

    Usuario save(Usuario usuario);
}
