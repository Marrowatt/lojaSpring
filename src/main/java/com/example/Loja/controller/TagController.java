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

import com.example.Loja.model.Tag;
import com.example.Loja.repository.TagRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	TagRepository tRepo;
	
	@GetMapping
	public String getTags(Model mav) {
		List<Tag> t = tRepo.findAll();
		mav.addAttribute("tags", t);
		return "tag/index";
	}
	
	@GetMapping("/{id}")
	public String getTagId(Model mav, @PathVariable("id") Long id) {
		Optional<Tag> tag = tRepo.findById(id);
		mav.addAttribute("tag", tag.get());
		return "tag/tagid";
	}

	@PostMapping
	public String postTag(@Valid Tag tag, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute("error", "Algum campo deve ser obrigatório!");
			return "redirect:/marca";
		}
		
		tag.setCreated_at(LocalDateTime.now());
		tag.setUpdated_at(LocalDateTime.now());
		
		tRepo.save(tag);
		
		return "redirect:/tag";
	}

	@PutMapping("/{id}")
	public String putTag(@Valid Tag tag, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			attr.addFlashAttribute("error", "Algum campo deve ser obrigatório!");
			return "redirect:/marca/" + tag.getId();
		}
		
		Tag tagBASE = tRepo.findById(tag.getId()).orElse(null);
		
		tagBASE.setName(tag.getName());
		tagBASE.setUpdated_at(LocalDateTime.now());
		
		tRepo.save(tagBASE);
		
		attr.addFlashAttribute("success", "Atualizado com sucesso!");
		
		return "redirect:/tag";
	}

	@GetMapping("/delete/{id}") // DELETE
	public String deleteTag(@PathVariable("id") Long id, RedirectAttributes attr) {
		tRepo.deleteById(id);
		attr.addFlashAttribute("success", "Deletado com sucesso!");
		return "redirect:/tag";
	}
}
