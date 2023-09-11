package com.example.Loja.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Loja.model.PcPronto;
import com.example.Loja.repository.PcProntoRepository;
import com.example.Loja.service.PcProntoService;

public class PcProntoServiceImplement implements PcProntoService {
	@Autowired
	PcProntoRepository pcProntoRepository;

	public List<PcPronto> findAll() {
		return pcProntoRepository.findAll();
	}

	public PcPronto findById(long id) {
		return pcProntoRepository.findById(id).get();
	}

	public PcPronto save(PcPronto pc_pronto) {
		return pcProntoRepository.save(pc_pronto);
	}

	public PcPronto deleteById(long id) {
		return deleteById(id);
	}
	
}
