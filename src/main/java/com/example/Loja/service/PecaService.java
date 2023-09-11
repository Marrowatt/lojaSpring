package com.example.Loja.service;

import java.util.List;

import com.example.Loja.model.Peca;

public interface PecaService {

    List<Peca> findAll();

    Peca findById(long id);

    Peca save(Peca peca);

    Peca deleteById(long id);
}
