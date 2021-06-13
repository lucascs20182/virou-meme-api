package org.serratec.viroumemeapi.services;

import java.util.List;
import java.util.Optional;

import org.serratec.viroumemeapi.dtos.EnderecoDTORequest;
import org.serratec.viroumemeapi.dtos.ViaCepDTO;
import org.serratec.viroumemeapi.entities.EnderecoEntity;
import org.serratec.viroumemeapi.exceptions.AddressNotAssociatedWithClientException;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.EnderecoMapper;
import org.serratec.viroumemeapi.repositories.ClienteRepository;
import org.serratec.viroumemeapi.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoMapper enderecoMapper;

	@Autowired
	ClienteService clienteService;

	@Autowired
	RestTemplate restTemplate;

	@Value("${endereco.baseUrl}")
	String baseUrl;

	public List<EnderecoEntity> getAll() {
		return enderecoRepository.findAll();
	}

	public EnderecoEntity getById(Long id) throws ItemNotFoundException {
		Optional<EnderecoEntity> endereco = enderecoRepository.findById(id);

		if (endereco.isEmpty()) {
			throw new ItemNotFoundException("Não existe endereço com esse Id.");
		}

		return endereco.get();
	}

	public EnderecoEntity create(EnderecoDTORequest endereco)
			throws ItemNotFoundException, AddressNotAssociatedWithClientException {
		EnderecoEntity entity = enderecoMapper.toEntity(endereco);

		if (endereco.getCep() != null) {
			ViaCepDTO viaCep = restTemplate.getForObject(baseUrl + endereco.getCep() + "/json", ViaCepDTO.class);

			entity.setRua(viaCep.getLogradouro());
			entity.setBairro(viaCep.getBairro());
			entity.setCidade(viaCep.getLocalidade());
			entity.setEstado(viaCep.getUf());
		}

		return enderecoRepository.save(entity);
	}

	public EnderecoEntity update(Long id, EnderecoDTORequest dto) throws ItemNotFoundException {
		EnderecoEntity endereco = this.getById(id);

		if (dto.getCep() != null) {
			ViaCepDTO viaCep = restTemplate.getForObject(baseUrl + dto.getCep() + "/json", ViaCepDTO.class);

			endereco.setCep(dto.getCep());

			endereco.setRua(viaCep.getLogradouro());
			endereco.setBairro(viaCep.getBairro());
			endereco.setCidade(viaCep.getLocalidade());
			endereco.setEstado(viaCep.getUf());
		}

		if (dto.getNumero() != null) {
			endereco.setNumero(dto.getNumero());
		}

		if (dto.getComplemento() != null) {
			endereco.setComplemento(dto.getComplemento());
		}

		return enderecoRepository.save(endereco);
	}

	public void delete(Long id) throws ItemNotFoundException {
		this.getById(id);

		enderecoRepository.deleteById(id);
	}
}
