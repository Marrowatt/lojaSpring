package com.example.Loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Loja.model.Usuario;
import com.example.Loja.repository.UsuarioRepository;
import com.example.Loja.serviceImplement.CustomUserDetailsServiceImplement;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository uRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario user = uRepo.findByLogin(username);
		
		if (user == null) throw new UsernameNotFoundException("Não achei.");
		
		return new CustomUserDetailsServiceImplement(user);
	}

}
