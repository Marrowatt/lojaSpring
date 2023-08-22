package com.example.Loja.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Loja.model.Tag;
import com.example.Loja.repository.TagRepository;
import com.example.Loja.service.TagService;

public class TagServiceImplement implements TagService {
	@Autowired
	TagRepository tagRepository;

	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	public Tag findById(long id) {
		return tagRepository.findById(id).get();
	}

	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}
}
