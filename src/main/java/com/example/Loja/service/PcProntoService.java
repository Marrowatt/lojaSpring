package com.example.Loja.service;

import java.util.List;

import com.example.Loja.model.PcPronto;

public interface PcProntoService {

    List<PcPronto> findAll();

    PcPronto findById(long id);

    PcPronto save(PcPronto pc_pronto);

    PcPronto deleteById(long id);
}
