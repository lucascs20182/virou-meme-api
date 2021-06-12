package org.serratec.viroumemeapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.viroumemeapi.dtos.ClienteDTORequest;
import org.serratec.viroumemeapi.dtos.EnderecoDTORequest;
import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.serratec.viroumemeapi.entities.EnderecoEntity;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.ClienteMapper;
import org.serratec.viroumemeapi.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteMapper clienteMapper;
	
	@Autowired
	EnderecoService enderecoService;

	public List<ClienteEntity> getAll() {
		return clienteRepository.findAll();
	}
	
	public ClienteEntity create(ClienteDTORequest dto) throws ItemNotFoundException {
		ClienteEntity entity = clienteMapper.toEntity(dto);

		clienteRepository.save(entity);

		List<EnderecoEntity> listaEnderecosDoCliente = new ArrayList<EnderecoEntity>();

		if (dto.getEnderecosDoCliente() != null) {
			for (EnderecoDTORequest enderecoDto : dto.getEnderecosDoCliente()) {
				enderecoDto.setClienteId(entity.getId());
				
				EnderecoEntity enderecoCompleto = enderecoService.create(enderecoDto);

				listaEnderecosDoCliente.add(enderecoCompleto);
			}
		}

		entity.setEnderecosDoCliente(listaEnderecosDoCliente);

		return entity;
	}
	
	public ClienteEntity getById(Long id) throws ItemNotFoundException {
		Optional<ClienteEntity> cliente = clienteRepository.findById(id);

		if (cliente.isEmpty()) {
			throw new ItemNotFoundException("Não existe cliente com esse Id.");
		}

		return cliente.get();
	}
}
