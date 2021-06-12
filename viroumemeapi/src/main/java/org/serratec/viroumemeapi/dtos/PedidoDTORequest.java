package org.serratec.viroumemeapi.dtos;

import java.util.List;

public class PedidoDTORequest {
	private Long idCliente;

	private List<DetalhesPedidoDTORequest> produtosDoPedido;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<DetalhesPedidoDTORequest> getProdutosDoPedido() {
		return produtosDoPedido;
	}

	public void setProdutosDoPedido(List<DetalhesPedidoDTORequest> produtosDoPedido) {
		this.produtosDoPedido = produtosDoPedido;
	}
}
