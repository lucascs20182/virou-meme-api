package org.serratec.viroumemeapi.dtos;

public class DetalhesPedidoDTORequest {

	private Long idProduto;

	private Integer quantidade;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
