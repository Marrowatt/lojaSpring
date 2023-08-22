package com.example.Loja.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Loja.model.Marca;
import com.example.Loja.repository.MarcaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/marca")
public class MarcaController {

	@Autowired
	MarcaRepository marcaRepository;
	
	@GetMapping
	public String getMarca(Model mav) {
//		ModelAndView mav = new ModelAndView("marcas/index");
		List<Marca> marca = marcaRepository.findAll();
		mav.addAttribute("marcas", marca);
		return "marcas/index";
	}
	
	@GetMapping("/{id}")
	public ModelAndView getMarcaId(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("marcas/marcaid"); 
		Optional<Marca> uma_so = marcaRepository.findById(id);
//		mav.addObject("marca", uma_so);
		mav.addObject("nome", uma_so.get().getName());
		return mav;
	}

	@PostMapping
	public String postMarca(@Valid Marca marca, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute("error", "Algum campo deve ser obrigatório!");
			return "redirect:/marca";
		}
		
		marca.setCreated_at(LocalDateTime.now());
		marca.setUpdated_at(LocalDateTime.now());
		
		marcaRepository.save(marca);
		
		return "redirect:/marca";
	}

	@PutMapping("/{id}")
	public String putMarca(@Valid Marca marca, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute("error", "Algum campo deve ser obrigatório!");
			return "redirect:/marca/" + marca.getId();
		}
		
		Marca marcaBASE = marcaRepository.findById(marca.getId()).orElse(null);
		
		marcaBASE.setName(marca.getName());
		marcaBASE.setUpdated_at(LocalDateTime.now());
		
		marcaRepository.save(marcaBASE);
		
		attr.addFlashAttribute("success", "Atualizado com sucesso!");
		
		return "redirect:/marca";
	}

	@GetMapping("/delete/{id}") // DELETE
	public String deleteMarca(@PathVariable("id") Long id, RedirectAttributes attr) {
		marcaRepository.deleteById(id);
		attr.addFlashAttribute("success", "Deletado com sucesso!");
		return "redirect:/marca";
	}
}
