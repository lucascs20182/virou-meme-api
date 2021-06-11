package org.serratec.viroumemeapi.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Categoria")
public class CategoriaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String descricao;

	@OneToMany(mappedBy = "categoria")
	private List<ProdutoEntity> produtosDaCategoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ProdutoEntity> getProdutosDaCategoria() {
		return produtosDaCategoria;
	}

	public void setProdutosDaCategoria(List<ProdutoEntity> produtosDaCategoria) {
		this.produtosDaCategoria = produtosDaCategoria;
	}
}
