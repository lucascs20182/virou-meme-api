package org.serratec.viroumemeapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.serratec.viroumemeapi.dtos.ProdutoDTORequest;
import org.serratec.viroumemeapi.dtos.ProdutoDTOResponse;
import org.serratec.viroumemeapi.entities.ProdutoEntity;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.ProdutoMapper;
import org.serratec.viroumemeapi.services.ProdutoService;
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
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService service;

	@Autowired
	ProdutoMapper mapper;

	@GetMapping
	public ResponseEntity<List<ProdutoDTOResponse>> getAll() {
		List<ProdutoDTOResponse> listaProdutosResponse = new ArrayList<ProdutoDTOResponse>();

		for (ProdutoEntity produto : service.getAll()) {
			listaProdutosResponse.add(mapper.toDto(produto));
		}

		return new ResponseEntity<List<ProdutoDTOResponse>>(listaProdutosResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTOResponse> getById(@PathVariable Long id) throws ItemNotFoundException {
		ProdutoDTOResponse produtoResponse = mapper.toDto(service.getById(id));

		return new ResponseEntity<ProdutoDTOResponse>(produtoResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody ProdutoDTORequest produto) throws ItemNotFoundException {
		service.create(produto);

		return new ResponseEntity<String>("Produto cadastrado com sucesso", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ProdutoDTORequest produto)
			throws ItemNotFoundException {
		service.update(id, produto);

		return new ResponseEntity<String>("Produto editado com sucesso", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws ItemNotFoundException {
		service.delete(id);

		return new ResponseEntity<String>("Produto deletado com sucesso", HttpStatus.OK);
	}
}
