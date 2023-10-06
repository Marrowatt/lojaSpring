package com.example.Loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Loja.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

	Tag findByName(String tag);
}
