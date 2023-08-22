package com.example.Loja.service;

import java.util.List;

import com.example.Loja.model.Tag;

public interface TagService {

    List<Tag> findAll();

    Tag findById(long id);

    Tag save(Tag tag);
}
