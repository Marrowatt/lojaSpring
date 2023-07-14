package com.example.Loja.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Loja.model.Marca;
import com.example.Loja.service.MarcaService;
import com.example.Loja.repository.MarcaRepository;

@Service
public class MarcaServiceImplement implements MarcaService {
	@Autowired
	MarcaRepository marcaRepository;

	public List<Marca> findAll() {
		return marcaRepository.findAll();
	}

	public Marca findById(long id) {
		return marcaRepository.findById(id).get();
	}

	public Marca save(Marca marca) {
		return marcaRepository.save(marca);
	}
}
