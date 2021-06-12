package org.serratec.viroumemeapi.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class PedidoDTORequest {
	private Long idCliente;

	@Schema(description = "Na edição do pedido esse campo não é necessário. "
			+ "Os detalhes do pedido podem ser editados no endpoint /pedido/detalhes")
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
