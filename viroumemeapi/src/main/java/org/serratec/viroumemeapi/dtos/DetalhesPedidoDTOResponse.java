package org.serratec.viroumemeapi.dtos;

public class DetalhesPedidoDTOResponse {

	private Long id;
	
	private Long idPedido;

	private Long idProduto;

	private Integer quantidadeProdutos;

	private Double precoDoProduto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getQuantidadeProdutos() {
		return quantidadeProdutos;
	}

	public void setQuantidadeProdutos(Integer quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}

	public Double getPrecoDoProduto() {
		return precoDoProduto;
	}

	public void setPrecoDoProduto(Double precoDoProduto) {
		this.precoDoProduto = precoDoProduto;
	}
}
