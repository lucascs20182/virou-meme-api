package org.serratec.viroumemeapi.mappers;

import java.util.ArrayList;
import java.util.List;

import org.serratec.viroumemeapi.dtos.ClienteDTORequest;
import org.serratec.viroumemeapi.dtos.ClienteDTOResponse;
import org.serratec.viroumemeapi.dtos.EnderecoDTORequest;
import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.serratec.viroumemeapi.entities.EnderecoEntity;
import org.serratec.viroumemeapi.entities.PedidoEntity;
import org.serratec.viroumemeapi.exceptions.ItemAlreadyExistsException;
import org.serratec.viroumemeapi.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

	@Autowired
	ClienteService service;

	public ClienteEntity toEntity(ClienteDTORequest dto) throws ItemAlreadyExistsException {
		ClienteEntity entity = new ClienteEntity();

		List<EnderecoEntity> listaEnderecosDoCliente = new ArrayList<EnderecoEntity>();

		for (ClienteEntity cliente : service.getAll()) {
			if (cliente.getCpf().equals(dto.getCpf())) {
				throw new ItemAlreadyExistsException("Uma conta já foi cadastrada utilizando este CPF.");
			}

			if (cliente.getEmail().equals(dto.getEmail())) {
				throw new ItemAlreadyExistsException("Uma conta já foi cadastrada utilizando este e-mail.");
			}

			if (cliente.getUsername().equals(dto.getUsername())) {
				throw new ItemAlreadyExistsException("Uma conta já foi cadastrada utilizando este username.");
			}
		}

		entity.setEmail(dto.getEmail());
		entity.setUsername(dto.getUsername());
		entity.setSenha(dto.getSenha());
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());
		entity.setTelefone(dto.getTelefone());
		entity.setDataNascimento(dto.getDataNascimento());

		if (dto.getEnderecosDoCliente() != null) {
			for (EnderecoDTORequest enderecoIncompleto : dto.getEnderecosDoCliente()) {
				EnderecoEntity endereco = new EnderecoEntity();

				endereco.setCep(enderecoIncompleto.getCep());
				endereco.setNumero(enderecoIncompleto.getNumero());
				endereco.setComplemento(enderecoIncompleto.getComplemento());

				endereco.setCliente(entity);

				listaEnderecosDoCliente.add(endereco);
			}
		}

		entity.setEnderecosDoCliente(listaEnderecosDoCliente);

		return entity;
	}

	public ClienteDTOResponse toDto(ClienteEntity entity) {
		ClienteDTOResponse dto = new ClienteDTOResponse();

		List<Long> idsDosEnderecosDoCliente = new ArrayList<Long>();

		List<Long> idsDosPedidosDoCliente = new ArrayList<Long>();

		dto.setId(entity.getId());
		dto.setEmail(entity.getEmail());
		dto.setUsername(entity.getUsername());
		dto.setNome(entity.getNome());
		dto.setCpf(entity.getCpf());
		dto.setTelefone(entity.getTelefone());
		dto.setDataNascimento(entity.getDataNascimento());

		for (EnderecoEntity endereco : entity.getEnderecosDoCliente()) {
			idsDosEnderecosDoCliente.add(endereco.getId());
		}

		for (PedidoEntity pedido : entity.getPedidosDoCliente()) {
			idsDosPedidosDoCliente.add(pedido.getId());
		}

		dto.setIdsDosEnderecosDoCliente(idsDosEnderecosDoCliente);
		dto.setIdsDosPedidosDoCliente(idsDosPedidosDoCliente);

		return dto;
	}
}
