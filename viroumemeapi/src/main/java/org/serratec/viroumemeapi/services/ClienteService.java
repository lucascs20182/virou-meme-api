package org.serratec.viroumemeapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.viroumemeapi.dtos.ClienteDTORequest;
import org.serratec.viroumemeapi.dtos.EnderecoDTORequest;
import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.serratec.viroumemeapi.entities.EnderecoEntity;
import org.serratec.viroumemeapi.exceptions.AddressNotAssociatedWithClientException;
import org.serratec.viroumemeapi.exceptions.CpfNotEditableException;
import org.serratec.viroumemeapi.exceptions.ItemAlreadyExistsException;
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

	public ClienteEntity getById(Long id) throws ItemNotFoundException {
		Optional<ClienteEntity> cliente = clienteRepository.findById(id);

		if (cliente.isEmpty()) {
			throw new ItemNotFoundException("Não existe cliente com esse Id.");
		}

		return cliente.get();
	}

	public ClienteEntity create(ClienteDTORequest dto)
			throws ItemNotFoundException, AddressNotAssociatedWithClientException, ItemAlreadyExistsException {
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

	public ClienteEntity update(Long id, ClienteDTORequest dto)
			throws ItemNotFoundException, CpfNotEditableException, ItemAlreadyExistsException {
		ClienteEntity entity = this.getById(id);

		for (ClienteEntity cliente : this.getAll()) {
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

		if (dto.getEmail() != null) {
			entity.setEmail(dto.getEmail());
		}

		if (dto.getUsername() != null) {
			entity.setUsername(dto.getUsername());
		}

		if (dto.getSenha() != null) {
			entity.setSenha(dto.getSenha());
		}

		if (dto.getNome() != null) {
			entity.setNome(dto.getNome());
		}

		if (dto.getCpf() != null) {
			throw new CpfNotEditableException("Não é possível alterar o CPF.");
		}

		if (dto.getTelefone() != null) {
			entity.setTelefone(dto.getTelefone());
		}

		if (dto.getDataNascimento() != null) {
			entity.setDataNascimento(dto.getDataNascimento());
		}

		return clienteRepository.save(entity);
	}

	public void delete(Long id) throws ItemNotFoundException {

		ClienteEntity entity = this.getById(id);

		if (entity.getEnderecosDoCliente() != null) {
			for (EnderecoEntity endereco : entity.getEnderecosDoCliente()) {
				enderecoService.delete(endereco.getId());
			}
		}

		clienteRepository.deleteById(id);
	}
}
