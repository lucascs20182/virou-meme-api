package org.serratec.viroumemeapi.services;

import java.util.List;
import java.util.Optional;

import org.serratec.viroumemeapi.dtos.EnderecoDTORequest;
import org.serratec.viroumemeapi.entities.EnderecoEntity;
import org.serratec.viroumemeapi.mappers.EnderecoMapper;
import org.serratec.viroumemeapi.repositories.ClienteRepository;
import org.serratec.viroumemeapi.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoMapper enderecoMapper;

	public List<EnderecoEntity> getAll() {
		return enderecoRepository.findAll();
	}

	public EnderecoEntity getById(Long id) {
		Optional<EnderecoEntity> endereco = enderecoRepository.findById(id);

//		if (endereco.isEmpty()) {
//			throw new ItemNotFoundException("Não existe endereço com esse Id.");
//		}

		return endereco.get();
	}

	public EnderecoEntity create(EnderecoDTORequest endereco) {
		EnderecoEntity entity = enderecoMapper.toEntity(endereco);

		clienteRepository.save(entity.getCliente());

		return enderecoRepository.save(entity);
	}

	public EnderecoEntity update(Long id, EnderecoDTORequest dto) {
		EnderecoEntity endereco = this.getById(id);

		if (dto.getNumero() != null) {
			endereco.setNumero(dto.getNumero());
		}

		if (dto.getComplemento() != null) {
			endereco.setComplemento(dto.getComplemento());
		}

//		if (dto.getClienteId() != null) {
//			endereco.setCliente(clienteService.getById(dto.getClienteId()));
//		}

		return enderecoRepository.save(endereco);
	}

	public void delete(Long id) {
		this.getById(id); // dispara ItemNotFoundException caso o id seja inválido

		enderecoRepository.deleteById(id);
	}
}
