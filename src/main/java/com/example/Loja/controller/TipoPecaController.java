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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Loja.model.TipoPeca;
import com.example.Loja.repository.TipoPecaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tipopeca")
public class TipoPecaController {
	
	@Autowired
	TipoPecaRepository tpRepo;
	
	@GetMapping
	public String getTipoPeca(Model mav) {
		List<TipoPeca> tps = tpRepo.findAll();
		mav.addAttribute("tipopecas", tps);
		return "tipopeca/index";
	}
	
	@GetMapping("/{id}")
	public String getTipoPecaId(Model mav, @PathVariable("id") Long id) {
		Optional<TipoPeca> tp = tpRepo.findById(id);
		mav.addAttribute("tipopeca", tp.get());
		return "tipopeca/tipopecaid";
	}

	@PostMapping
	public String postTipoPeca(@Valid TipoPeca tipopeca, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute("error", "Algum campo deve ser obrigatório!");
			return "redirect:/marca";
		}
		
		tipopeca.setCreated_at(LocalDateTime.now());
		tipopeca.setUpdated_at(LocalDateTime.now());
		
		tpRepo.save(tipopeca);
		
		return "redirect:/tipopeca";
	}

	@PutMapping("/{id}")
	public String putTipoPeca(@Valid TipoPeca tipopeca, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute("error", "Algum campo deve ser obrigatório!");
			return "redirect:/tipopeca/" + tipopeca.getId();
		}
		
		TipoPeca tipopecaBASE = tpRepo.findById(tipopeca.getId()).orElse(null);
		
		tipopecaBASE.setName(tipopeca.getName());
		tipopecaBASE.setUpdated_at(LocalDateTime.now());
		
		tpRepo.save(tipopecaBASE);
		
		attr.addFlashAttribute("success", "Atualizado com sucesso!");
		
		return "redirect:/tipopeca";
	}

	@GetMapping("/delete/{id}") // DELETE
	public String deleteTipoPeca(@PathVariable("id") Long id, RedirectAttributes attr) {
		tpRepo.deleteById(id);
		attr.addFlashAttribute("success", "Deletado com sucesso!");
		return "redirect:/tipopeca";
	}
}
