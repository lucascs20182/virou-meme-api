package org.serratec.viroumemeapi.mappers;

import org.serratec.viroumemeapi.dtos.EnderecoDTORequest;
import org.serratec.viroumemeapi.dtos.EnderecoDTOResponse;
import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.serratec.viroumemeapi.entities.EnderecoEntity;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

	public EnderecoEntity toEntity(EnderecoDTORequest dto) {
		EnderecoEntity entity = new EnderecoEntity();

		ClienteEntity entityCliente = new ClienteEntity();
		entityCliente.setId(dto.getClienteId());

		entity.setCep(dto.getCep());
		entity.setNumero(dto.getNumero());
		entity.setComplemento(dto.getComplemento());
		entity.setCliente(entityCliente);

		return entity;
	}

	public EnderecoDTOResponse toDto(EnderecoEntity entity) {
		EnderecoDTOResponse dto = new EnderecoDTOResponse();

		dto.setId(entity.getId());
		dto.setCep(entity.getCep());
		dto.setRua(entity.getRua());
		dto.setBairro(entity.getBairro());
		dto.setCidade(entity.getCidade());
		dto.setNumero(entity.getNumero());
		dto.setComplemento(entity.getComplemento());
		dto.setEstado(entity.getEstado());

		dto.setIdClienteQueMoraNoEndereco(entity.getCliente().getId());

		return dto;
	}
}
