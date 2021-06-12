package org.serratec.viroumemeapi.mappers;

import java.util.ArrayList;
import java.util.List;

import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTOResponse;
import org.serratec.viroumemeapi.dtos.PedidoDTORequest;
import org.serratec.viroumemeapi.dtos.PedidoDTOResponse;
import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.serratec.viroumemeapi.entities.DetalhesPedidoEntity;
import org.serratec.viroumemeapi.entities.PedidoEntity;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.services.ClienteService;
import org.serratec.viroumemeapi.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

	@Autowired
	DetalhesPedidoMapper mapperDetalhesPedido;

	@Autowired
	ClienteService clienteService;

	@Autowired
	ProdutoService produtoService;

	public PedidoEntity toEntity(PedidoDTORequest dto) throws ItemNotFoundException {
		PedidoEntity entity = new PedidoEntity();

		ClienteEntity entityCliente = clienteService.getById(dto.getIdCliente());

		entity.setCliente(entityCliente);

		List<PedidoEntity> pedidosDoCliente = entityCliente.getPedidosDoCliente();
		pedidosDoCliente.add(entity);

		entityCliente.setPedidosDoCliente(pedidosDoCliente);

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

		dto.setProdutosDoPedido(listaDetalhesDosPedidos);

		return dto;
	}
}
