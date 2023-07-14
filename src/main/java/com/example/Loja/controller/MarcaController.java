package com.example.Loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.Loja.model.Marca;
import com.example.Loja.repository.MarcaRepository;

@Controller
public class MarcaController {

	@Autowired
	MarcaRepository marcaRepository;
	
	@RequestMapping(value="/marca", method=RequestMethod.GET)
	public ModelAndView getMarca() {
		ModelAndView mav = new ModelAndView("marca");
		List<Marca> marca = marcaRepository.findAll();
		mav.addObject("marcas", marca);
		return mav;
	}
	
	
}
