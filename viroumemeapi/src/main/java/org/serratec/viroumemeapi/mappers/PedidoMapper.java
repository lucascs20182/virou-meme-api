package org.serratec.viroumemeapi.mappers;

import java.util.ArrayList;
import java.util.List;

import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTORequest;
import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTOResponse;
import org.serratec.viroumemeapi.dtos.PedidoDTORequest;
import org.serratec.viroumemeapi.dtos.PedidoDTOResponse;
import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.serratec.viroumemeapi.entities.DetalhesPedidoEntity;
import org.serratec.viroumemeapi.entities.PedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

	@Autowired
	DetalhesPedidoMapper mapperDetalhesPedido;

	public PedidoEntity toEntity(PedidoDTORequest dto) {
		PedidoEntity entity = new PedidoEntity();

		ClienteEntity entityCliente = new ClienteEntity();
		entityCliente.setId(dto.getIdCliente());

		List<DetalhesPedidoEntity> produtosDoPedido = new ArrayList<DetalhesPedidoEntity>();

		for (DetalhesPedidoDTORequest detalhesPedido : dto.getProdutosDoPedido()) {
			DetalhesPedidoEntity entityDetalhesPedido = mapperDetalhesPedido.toEntity(detalhesPedido);

			produtosDoPedido.add(entityDetalhesPedido);
		}

		entity.setCliente(entityCliente);
		entity.setProdutosDoPedido(produtosDoPedido);

		return entity;
	}

	public PedidoDTOResponse toDto(PedidoEntity entity) {
		PedidoDTOResponse dto = new PedidoDTOResponse();

		List<DetalhesPedidoDTOResponse> listaDetalhesDosPedidos = new ArrayList<DetalhesPedidoDTOResponse>();

		dto.setId(entity.getId());
		dto.setNumeroPedido(entity.getNumeroPedido());
		dto.setValorTotal(entity.getValorTotal());
		dto.setDataQuePedidoFoiFeito(entity.getDataQuePedidoFoiFeito());
		dto.setDataEntrega(entity.getDataEntrega());
		dto.setStatus(entity.getStatus());

		for (DetalhesPedidoEntity detalhesPedido : entity.getProdutosDoPedido()) {
			DetalhesPedidoDTOResponse dtoDetalhesPedido = mapperDetalhesPedido.toDto(detalhesPedido);

			listaDetalhesDosPedidos.add(dtoDetalhesPedido);
		}

		dto.setIdDoClienteQueFezPedido(entity.getCliente().getId());

		return dto;
	}
}
