package org.serratec.viroumemeapi.mappers;

import org.serratec.viroumemeapi.dtos.ProdutoDTORequest;
import org.serratec.viroumemeapi.dtos.ProdutoDTOResponse;
import org.serratec.viroumemeapi.entities.CategoriaEntity;
import org.serratec.viroumemeapi.entities.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

	public ProdutoEntity toEntity(ProdutoDTORequest dto) {
		ProdutoEntity entity = new ProdutoEntity();

		CategoriaEntity entityCategoria = new CategoriaEntity();
		entityCategoria.setId(dto.getCategoriaId());

		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setPreco(dto.getPreco());
		entity.setQuantidadeEmEstoque(dto.getQuantidadeEmEstoque());

		entity.setCategoria(entityCategoria);

		return entity;
	}

	public ProdutoDTOResponse toDto(ProdutoEntity entity) {
		ProdutoDTOResponse dto = new ProdutoDTOResponse();

		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setDescricao(entity.getDescricao());
		dto.setPreco(entity.getPreco());
		dto.setQuantidadeEmEstoque(entity.getQuantidadeEmEstoque());
		dto.setDataCadastro(entity.getDataCadastro());
		dto.setIdCategoria(entity.getCategoria().getId());

		return dto;
	}
}
