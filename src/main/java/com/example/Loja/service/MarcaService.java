package com.example.Loja.service;

import java.util.List;
import com.example.Loja.model.Marca;

public interface MarcaService {
    List<Marca> findAll();

    Marca findById(long id);

    Marca save(Marca marca);
    
    Marca deleteById(long id);
}
