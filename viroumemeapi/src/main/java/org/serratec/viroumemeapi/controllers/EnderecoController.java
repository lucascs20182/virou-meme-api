package org.serratec.viroumemeapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.serratec.viroumemeapi.dtos.EnderecoDTORequest;
import org.serratec.viroumemeapi.dtos.EnderecoDTOResponse;
import org.serratec.viroumemeapi.entities.EnderecoEntity;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.EnderecoMapper;
import org.serratec.viroumemeapi.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService service;

	@Autowired
	EnderecoMapper mapper;

	@GetMapping
	public ResponseEntity<List<EnderecoDTOResponse>> getAll() {
		List<EnderecoDTOResponse> listaEnderecosResponse = new ArrayList<EnderecoDTOResponse>();

		for (EnderecoEntity endereco : service.getAll()) {
			listaEnderecosResponse.add(mapper.toDto(endereco));
		}

		return new ResponseEntity<List<EnderecoDTOResponse>>(listaEnderecosResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDTOResponse> getById(@PathVariable Long id) throws ItemNotFoundException {
		EnderecoDTOResponse enderecoResponse = mapper.toDto(service.getById(id));

		return new ResponseEntity<EnderecoDTOResponse>(enderecoResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody EnderecoDTORequest endereco) throws ItemNotFoundException {
		service.create(endereco);

		return new ResponseEntity<String>("Endereço cadastrado com sucesso", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody EnderecoDTORequest endereco)
			throws ItemNotFoundException {
		service.update(id, endereco);

		return new ResponseEntity<String>("Endereço editado com sucesso", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws ItemNotFoundException {
		service.delete(id);

		return new ResponseEntity<String>("Endereço deletado com sucesso", HttpStatus.OK);
	}
}
