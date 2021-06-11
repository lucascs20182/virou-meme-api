package org.serratec.viroumemeapi.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.serratec.viroumemeapi.enums.StatusPedido;

@Entity
@Table(name = "Pedido")
public class PedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String numeroPedido;

	private Double valorTotal;

	private LocalDate dataQuePedidoFoiFeito;

	private LocalDate dataEntrega;

	private StatusPedido status;

	@OneToMany(mappedBy = "pedido")
	private List<DetalhesPedidoEntity> produtosDoPedido;

	@ManyToOne
	private ClienteEntity cliente;

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

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public List<DetalhesPedidoEntity> getProdutosDoPedido() {
		return produtosDoPedido;
	}

	public void setProdutosDoPedido(List<DetalhesPedidoEntity> produtosDoPedido) {
		this.produtosDoPedido = produtosDoPedido;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
}
