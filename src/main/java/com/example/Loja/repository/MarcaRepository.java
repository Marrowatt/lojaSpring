package com.example.Loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Loja.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{

	Marca findByName(String marca);
	
}
