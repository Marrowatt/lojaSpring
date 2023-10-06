package com.example.Loja.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Loja.model.Produto;
import com.example.Loja.repository.ProdutoRepository;
import com.example.Loja.service.ProdutoService;

public class ProdutoServiceImplement implements ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto findById(long id) {
		return produtoRepository.findById(id).get();
	}

	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto deleteById(long id) {
		return deleteById(id);
	}
}
