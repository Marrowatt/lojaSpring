package com.example.Loja.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private double cost_price;

	@NotNull
	private boolean isPcPronto;
	
	@ManyToOne
    @JoinColumn(name="marca_id", nullable=false)
    private Marca marca;

	@ManyToOne
    @JoinColumn(name="peca_id", nullable=true)
    private Peca peca;

	@ManyToOne
    @JoinColumn(name="pc_pronto_id", nullable=true)
    private PcPronto pc_pronto;
	
	@ManyToOne
    @JoinColumn(name="usuario_id", nullable=false)
    private Usuario usuario;

	@ManyToMany
	@JoinTable(
	  name = "produto_tag", 
	  joinColumns = @JoinColumn(name = "produto_id"), 
	  inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD HH:mm:ss")
	private LocalDateTime created_at;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD HH:mm:ss")
	private LocalDateTime updated_at;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCost_price() {
		return cost_price;
	}

	public void setCost_price(double cost_price) {
		this.cost_price = cost_price;
	}

	public boolean getIsPcPronto() {
		return isPcPronto;
	}

	public void setIsPcPronto(boolean isPcPronto) {
		this.isPcPronto = isPcPronto;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public PcPronto getPc_pronto() {
		return pc_pronto;
	}

	public void setPc_pronto(PcPronto pc_pronto) {
		this.pc_pronto = pc_pronto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

}
