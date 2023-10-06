package com.example.Loja.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Loja.model.TipoPeca;
import com.example.Loja.repository.TipoPecaRepository;
import com.example.Loja.service.TipoPecaService;

public class TipoPecaServiceImplement implements TipoPecaService {
	@Autowired
	TipoPecaRepository tipoPecaRepository;

	public List<TipoPeca> findAll() {
		return tipoPecaRepository.findAll();
	}

	public TipoPeca findById(long id) {
		return tipoPecaRepository.findById(id).get();
	}

	public TipoPeca save(TipoPeca tipo_peca) {
		return tipoPecaRepository.save(tipo_peca);
	}

	public TipoPeca deleteById(long id) {
		return deleteById(id);
	}
}
