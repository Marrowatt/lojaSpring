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

import com.example.Loja.model.Marca;
import com.example.Loja.model.PcPronto;
import com.example.Loja.model.Peca;
import com.example.Loja.model.Produto;
import com.example.Loja.model.Tag;
import com.example.Loja.model.TipoPeca;
import com.example.Loja.model.Usuario;
import com.example.Loja.repository.MarcaRepository;
import com.example.Loja.repository.PcProntoRepository;
import com.example.Loja.repository.PecaRepository;
import com.example.Loja.repository.ProdutoRepository;
import com.example.Loja.repository.TagRepository;
import com.example.Loja.repository.TipoPecaRepository;
import com.example.Loja.repository.UsuarioRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoRepository rep;

	@Autowired
	MarcaRepository mar_rep;

	@Autowired
	TipoPecaRepository tipo_pec_rep;

	@Autowired
	TagRepository tag_rep;

	@Autowired
	PcProntoRepository pc_rep;
	
	@Autowired
	PecaRepository pec_rep;

	@Autowired
	UsuarioRepository user_rep;
	
	@GetMapping
	public String getProduto(Model mav) {
		List<Produto> prod = rep.findAll();
		mav.addAttribute("produtos", prod);
		return "produtos/index";
	}
	
	@GetMapping("/cadastro")
	public String createProduto(Model mav) {
		List<Marca> mar = mar_rep.findAll();
		List<TipoPeca> tip = tipo_pec_rep.findAll();
		List<Tag> tag = tag_rep.findAll();
		mav.addAttribute("mar", mar);
		mav.addAttribute("tip", tip);
		mav.addAttribute("tag", tag);
		return "produtos/cadastro";
	}
	
	@GetMapping("/{id}")
	public String getProdutoId(@PathVariable("id") Long id, Model mav) {
		Optional<Produto> pr = rep.findById(id);
		mav.addAttribute("produto", pr.get());
		return "produtos/visualizar";
	}

	@PostMapping
	public String postProduto(String is_pc_pronto, Produto produto, PcPronto pc_pronto, 
			 Peca peca, BindingResult result, RedirectAttributes attr) {
	
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			attr.addFlashAttribute("error", "Algum campo deve ser obrigatório!");
			return "redirect:/produto/cadastro";
		}
		
		if (is_pc_pronto.equals("pc_pronto")) {
			pc_pronto.setCreated_at(LocalDateTime.now());
			pc_pronto.setUpdated_at(LocalDateTime.now());
			
			produto.setPc_pronto(pc_rep.save(pc_pronto));
			produto.setIsPcPronto(true);
		} else {
			peca.setCreated_at(LocalDateTime.now());
			peca.setUpdated_at(LocalDateTime.now());
			
			produto.setPeca(pec_rep.save(peca));
			produto.setIsPcPronto(false);
		}
		
		Usuario user = user_rep.findById((long) 1).get();
		
		produto.setCreated_at(LocalDateTime.now());
		produto.setUpdated_at(LocalDateTime.now());
		produto.setUsuario(user);
		
		rep.save(produto);

		attr.addFlashAttribute("success", "Criado com sucesso!");
		
		return "redirect:/produto";
	}
	
	@GetMapping("/delete/{id}") // DELETE
	public String deleteProduto(@PathVariable("id") Long id, RedirectAttributes attr) {
		rep.deleteById(id);
		attr.addFlashAttribute("success", "Deletado com sucesso!");
		return "redirect:/produto";
	}
	
	@GetMapping("/editar/{id}")
	public String editProduto(Model mav, @PathVariable("id") Long id) {
		List<Marca> mar = mar_rep.findAll();
		List<TipoPeca> tip = tipo_pec_rep.findAll();
		List<Tag> tag = tag_rep.findAll();
		Produto prod = rep.findById(id).get();
		mav.addAttribute("prod", prod);
		mav.addAttribute("mar", mar);
		mav.addAttribute("tip", tip);
		mav.addAttribute("tag", tag);
		return "produtos/editar";
	}

	@PutMapping("/{id}")
	public String updateProduto(String is_pc_pronto, Produto produto, PcPronto pc_pronto, 
			 Peca peca, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			attr.addFlashAttribute("error", "Algum campo deve ser obrigatório!");
			return "redirect:/produto/editar/" + produto.getId();
		}

		Produto prodBASE = rep.findById(produto.getId()).orElse(null);
		
		// e as tags ?
		
		if (is_pc_pronto.equals("pc_pronto")) {
			
			if (prodBASE.getPc_pronto() == null) {

				pc_pronto.setCreated_at(LocalDateTime.now());
				pc_pronto.setUpdated_at(LocalDateTime.now());
				
				prodBASE.setPc_pronto(pc_rep.save(pc_pronto));
				prodBASE.setPeca(null);
				
			} else {

				PcPronto pcProntoBase = pc_rep.findById(prodBASE.getPc_pronto().getId()).get();
				
				pcProntoBase.setUpdated_at(LocalDateTime.now());
				pcProntoBase.setName(pc_pronto.getName());
				pcProntoBase.setDescription(pc_pronto.getDescription());
			}

			prodBASE.setIsPcPronto(true);
			
		} else {

			if (prodBASE.getPeca() == null) {

				peca.setCreated_at(LocalDateTime.now());
				peca.setUpdated_at(LocalDateTime.now());
				
				prodBASE.setPeca(pec_rep.save(peca));
				prodBASE.setPc_pronto(null);
				
			} else {
				
				Peca pecaBase = pec_rep.findById(prodBASE.getPeca().getId()).get();
			
				pecaBase.setUpdated_at(LocalDateTime.now());
				pecaBase.setCapacity(peca.getCapacity());
				pecaBase.setMeasure_unity(peca.getMeasure_unity());
				pecaBase.setQuantity(peca.getQuantity());
				pecaBase.setTipo_peca(peca.getTipo_peca());
				pecaBase.setName(peca.getName());
				pecaBase.setDescription(peca.getDescription());
			
			}
			
			prodBASE.setIsPcPronto(false);
		}
		
		prodBASE.setUpdated_at(LocalDateTime.now());
		prodBASE.setCost_price(produto.getCost_price());
		prodBASE.setMarca(produto.getMarca());
		
		rep.save(prodBASE);
		
		attr.addFlashAttribute("success", "Atualizado com sucesso!");
		
		return "redirect:/produto";
	}
}
