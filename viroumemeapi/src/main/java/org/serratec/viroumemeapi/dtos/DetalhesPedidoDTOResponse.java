package org.serratec.viroumemeapi.dtos;

public class DetalhesPedidoDTOResponse {
	private Long idProduto;

	private Integer quantidadeProdutos;

	private Double precoDoProduto;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
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
