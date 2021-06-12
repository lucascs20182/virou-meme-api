package org.serratec.viroumemeapi.mappers;

import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTORequest;
import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTOResponse;
import org.serratec.viroumemeapi.entities.DetalhesPedidoEntity;
import org.serratec.viroumemeapi.entities.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class DetalhesPedidoMapper {

	public DetalhesPedidoEntity toEntity(DetalhesPedidoDTORequest dto) {
		DetalhesPedidoEntity entity = new DetalhesPedidoEntity();

		ProdutoEntity entityProduto = new ProdutoEntity();
		entityProduto.setId(dto.getIdProduto());

		entity.setProduto(entityProduto);
		entity.setQuantidade(dto.getQuantidade());

		return entity;
	}

	public DetalhesPedidoDTOResponse toDto(DetalhesPedidoEntity entity) {
		DetalhesPedidoDTOResponse dto = new DetalhesPedidoDTOResponse();

		dto.setIdProduto(entity.getProduto().getId());
		dto.setPrecoDoProduto(entity.getPreco());
		dto.setQuantidadeProdutos(entity.getQuantidade());

		return dto;
	}
}
