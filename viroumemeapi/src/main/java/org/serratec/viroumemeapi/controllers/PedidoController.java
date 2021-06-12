package org.serratec.viroumemeapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.serratec.viroumemeapi.dtos.PedidoDTORequest;
import org.serratec.viroumemeapi.dtos.PedidoDTOResponse;
import org.serratec.viroumemeapi.entities.PedidoEntity;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.PedidoMapper;
import org.serratec.viroumemeapi.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService service;

	@Autowired
	PedidoMapper mapper;

	@GetMapping
	public ResponseEntity<List<PedidoDTOResponse>> getAll() {
		List<PedidoDTOResponse> listaPedidosResponse = new ArrayList<PedidoDTOResponse>();

		for (PedidoEntity pedido : service.getAll()) {
			listaPedidosResponse.add(mapper.toDto(pedido));
		}

		return new ResponseEntity<List<PedidoDTOResponse>>(listaPedidosResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDTOResponse> getById(@PathVariable Long id) throws ItemNotFoundException {
		PedidoDTOResponse pedidoResponse = mapper.toDto(service.getById(id));

		return new ResponseEntity<PedidoDTOResponse>(pedidoResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody PedidoDTORequest dto) throws ItemNotFoundException {
		service.create(dto);

		return new ResponseEntity<String>("Pedido cadastrado com sucesso", HttpStatus.CREATED);
	}
}
