package org.serratec.viroumemeapi.mappers;

import org.serratec.viroumemeapi.dtos.ProdutoDTORequest;
import org.serratec.viroumemeapi.dtos.ProdutoDTOResponse;
import org.serratec.viroumemeapi.entities.CategoriaEntity;
import org.serratec.viroumemeapi.entities.ProdutoEntity;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

	@Autowired
	CategoriaService categoriaService;

	public ProdutoEntity toEntity(ProdutoDTORequest dto) throws ItemNotFoundException {
		ProdutoEntity entity = new ProdutoEntity();

		if (dto.getCategoriaId() != null) {
			CategoriaEntity entityCategoria = categoriaService.getById(dto.getCategoriaId());

			entity.setCategoria(entityCategoria);
		}

		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setPreco(dto.getPreco());
		entity.setQuantidadeEmEstoque(dto.getQuantidadeEmEstoque());

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

		if (entity.getCategoria() != null) {
			dto.setIdCategoria(entity.getCategoria().getId());
		}

		return dto;
	}
}
