package org.serratec.viroumemeapi.mappers;

import org.serratec.viroumemeapi.dtos.CategoriaDTORequest;
import org.serratec.viroumemeapi.dtos.CategoriaDTOResponse;
import org.serratec.viroumemeapi.entities.CategoriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
	
	public CategoriaEntity toEntity(CategoriaDTORequest dto) {
		CategoriaEntity entity = new CategoriaEntity();
		
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		
		return entity;
	}
	
	public CategoriaDTOResponse toDto(CategoriaEntity entity) {
		CategoriaDTOResponse dto = new CategoriaDTOResponse();
		
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setDescricao(entity.getDescricao());
		
		return dto;
	}
}
