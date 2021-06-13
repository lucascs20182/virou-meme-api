package org.serratec.viroumemeapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.serratec.viroumemeapi.dtos.ClienteDTORequest;
import org.serratec.viroumemeapi.dtos.ClienteDTOResponse;
import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.serratec.viroumemeapi.exceptions.AddressNotAssociatedWithClientException;
import org.serratec.viroumemeapi.exceptions.CpfNotEditableException;
import org.serratec.viroumemeapi.exceptions.ItemAlreadyExistsException;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.ClienteMapper;
import org.serratec.viroumemeapi.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class ClienteController {

	@Autowired
	ClienteService service;

	@Autowired
	ClienteMapper mapper;

	@GetMapping("/cliente")
	public ResponseEntity<List<ClienteDTOResponse>> getAll() {
		List<ClienteDTOResponse> listaClientesResponse = new ArrayList<ClienteDTOResponse>();

		for (ClienteEntity cliente : service.getAll()) {
			listaClientesResponse.add(mapper.toDto(cliente));
		}

		return new ResponseEntity<List<ClienteDTOResponse>>(listaClientesResponse, HttpStatus.OK);
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<ClienteDTOResponse> getById(@PathVariable Long id) throws ItemNotFoundException {
		ClienteDTOResponse clienteResponse = mapper.toDto(service.getById(id));

		return new ResponseEntity<ClienteDTOResponse>(clienteResponse, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody ClienteDTORequest cliente)
			throws ItemNotFoundException, AddressNotAssociatedWithClientException, ItemAlreadyExistsException {

		service.create(cliente);

		return new ResponseEntity<String>("Cliente cadastrado com sucesso", HttpStatus.CREATED);
	}

	@SecurityRequirement(name = "bearerAuth")
	@PutMapping("/cliente/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ClienteDTORequest cliente)
			throws ItemNotFoundException, CpfNotEditableException, ItemAlreadyExistsException {
		service.update(id, cliente);

		return new ResponseEntity<String>("Cliente editado com sucesso", HttpStatus.OK);
	}

	@SecurityRequirement(name = "bearerAuth")
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws ItemNotFoundException {
		service.delete(id);

		return new ResponseEntity<String>("Cliente deletado com sucesso", HttpStatus.OK);
	}
}
