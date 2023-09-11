package com.example.Loja.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Loja.model.Marca;
import com.example.Loja.model.Tag;
import com.example.Loja.repository.MarcaRepository;
import com.example.Loja.repository.TagRepository;

@Component
public class Semeadora implements CommandLineRunner {

	@Autowired
	MarcaRepository mRepo;
	
	@Autowired
	TagRepository tRepo;
	
	@Override
	public void run(String... args) throws Exception {

		String[] marcas = {"Acer", "Samsung", "LG"};
		
		for (String m: marcas) {
			
			Marca marca = mRepo.findByName(m);
			
			if (marca == null) {
				marca = new Marca(m);
				mRepo.save(marca);
			}
			
		}
		
		String[] tags = {"novo", "usado", "ram", "giga"};
		
		for (String t: tags) {
			
			Tag tag = tRepo.findByName(t);
			
			if (tag == null) {
				tag = new Tag(t);
				tRepo.save(tag);
			}
			
		}
		
	}

}
