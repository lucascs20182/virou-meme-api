package org.serratec.viroumemeapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTORequest;
import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTOResponse;
import org.serratec.viroumemeapi.entities.DetalhesPedidoEntity;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.exceptions.ProductStockLessThanRequestedException;
import org.serratec.viroumemeapi.exceptions.QuantityCannotBeZeroException;
import org.serratec.viroumemeapi.mappers.DetalhesPedidoMapper;
import org.serratec.viroumemeapi.services.DetalhesPedidoService;
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
@RequestMapping("/pedido/detalhes")
public class DetalhesPedidoController {
	@Autowired
	DetalhesPedidoService service;

	@Autowired
	DetalhesPedidoMapper mapper;

	@GetMapping
	public ResponseEntity<List<DetalhesPedidoDTOResponse>> getAll() {
		List<DetalhesPedidoDTOResponse> listaDetalhesDosPedidosResponse = new ArrayList<DetalhesPedidoDTOResponse>();

		for (DetalhesPedidoEntity detalhesPedido : service.getAll()) {
			listaDetalhesDosPedidosResponse.add(mapper.toDto(detalhesPedido));
		}

		return new ResponseEntity<List<DetalhesPedidoDTOResponse>>(listaDetalhesDosPedidosResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesPedidoDTOResponse> getById(@PathVariable Long id) throws ItemNotFoundException {
		DetalhesPedidoDTOResponse detalhesPedidoResponse = mapper.toDto(service.getById(id));

		return new ResponseEntity<DetalhesPedidoDTOResponse>(detalhesPedidoResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody DetalhesPedidoDTORequest dto)
			throws ItemNotFoundException, ProductStockLessThanRequestedException, QuantityCannotBeZeroException {
		service.create(dto);

		return new ResponseEntity<String>("Detalhe de pedido cadastrado com sucesso", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody DetalhesPedidoDTORequest dto)
			throws ItemNotFoundException {
		service.update(id, dto);

		return new ResponseEntity<String>("Detalhe de pedido editado com sucesso", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws ItemNotFoundException {
		service.delete(id);

		return new ResponseEntity<String>("Endereço deletado com sucesso", HttpStatus.OK);
	}
}
