package com.example.Loja.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Loja.model.Peca;
import com.example.Loja.repository.PecaRepository;
import com.example.Loja.service.PecaService;

public class PecaServiceImplement implements PecaService {
	@Autowired
	PecaRepository pecaRepository;

	public List<Peca> findAll() {
		return pecaRepository.findAll();
	}

	public Peca findById(long id) {
		return pecaRepository.findById(id).get();
	}

	public Peca save(Peca peca) {
		return pecaRepository.save(peca);
	}

	public Peca deleteById(long id) {
		return deleteById(id);
	}
}
