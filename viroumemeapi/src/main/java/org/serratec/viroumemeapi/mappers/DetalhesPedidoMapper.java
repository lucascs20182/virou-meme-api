package org.serratec.viroumemeapi.mappers;

import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTORequest;
import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTOResponse;
import org.serratec.viroumemeapi.entities.DetalhesPedidoEntity;
import org.serratec.viroumemeapi.entities.ProdutoEntity;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetalhesPedidoMapper {

	@Autowired
	ProdutoService produtoService;

	public DetalhesPedidoEntity toEntity(DetalhesPedidoDTORequest dto) throws ItemNotFoundException {
		DetalhesPedidoEntity entity = new DetalhesPedidoEntity();

		ProdutoEntity entityProduto = produtoService.getById(dto.getIdProduto());

		entity.setProduto(entityProduto);
		entity.setQuantidade(dto.getQuantidade());
		entity.setPreco(entityProduto.getPreco());

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
