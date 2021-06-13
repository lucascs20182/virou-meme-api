package org.serratec.viroumemeapi.dtos;

import java.time.LocalDate;
import java.util.List;

public class PedidoDTOResponse {
	private Long id;

	private String numeroPedido;

	private Double valorTotal;

	private LocalDate dataQuePedidoFoiFeito;

	private LocalDate dataEntrega;

	private String status;

	private List<DetalhesPedidoDTOResponse> produtosDoPedido;

	private Long idDoClienteQueFezPedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataQuePedidoFoiFeito() {
		return dataQuePedidoFoiFeito;
	}

	public void setDataQuePedidoFoiFeito(LocalDate dataQuePedidoFoiFeito) {
		this.dataQuePedidoFoiFeito = dataQuePedidoFoiFeito;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DetalhesPedidoDTOResponse> getProdutosDoPedido() {
		return produtosDoPedido;
	}

	public void setProdutosDoPedido(List<DetalhesPedidoDTOResponse> produtosDoPedido) {
		this.produtosDoPedido = produtosDoPedido;
	}

	public Long getIdDoClienteQueFezPedido() {
		return idDoClienteQueFezPedido;
	}

	public void setIdDoClienteQueFezPedido(Long idDoClienteQueFezPedido) {
		this.idDoClienteQueFezPedido = idDoClienteQueFezPedido;
	}
}
