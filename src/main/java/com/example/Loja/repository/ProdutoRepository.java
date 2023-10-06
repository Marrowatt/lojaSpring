package com.example.Loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Loja.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
