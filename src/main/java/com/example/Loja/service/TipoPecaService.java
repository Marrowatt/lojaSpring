package com.example.Loja.service;

import java.util.List;

import com.example.Loja.model.TipoPeca;

public interface TipoPecaService {

    List<TipoPeca> findAll();

    TipoPeca findById(long id);

    TipoPeca save(TipoPeca tipo_peca);
    
    TipoPeca deleteById(long id);
}
