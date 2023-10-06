package com.example.Loja.service;

import java.util.List;

import com.example.Loja.model.Produto;

public interface ProdutoService {
    List<Produto> findAll();

    Produto findById(long id);

    Produto save(Produto produto);

    Produto deleteById(long id);
}
