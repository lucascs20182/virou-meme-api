package org.serratec.viroumemeapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.serratec.viroumemeapi.dtos.CategoriaDTORequest;
import org.serratec.viroumemeapi.dtos.CategoriaDTOResponse;
import org.serratec.viroumemeapi.entities.CategoriaEntity;
import org.serratec.viroumemeapi.exceptions.CategoryReferencedByProductException;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.CategoriaMapper;
import org.serratec.viroumemeapi.services.CategoriaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService service;

	@Autowired
	CategoriaMapper mapper;

	@GetMapping
	public ResponseEntity<List<CategoriaDTOResponse>> getAll() {
		List<CategoriaDTOResponse> listaCategoriasResponse = new ArrayList<CategoriaDTOResponse>();

		for (CategoriaEntity categoria : service.getAll()) {
			listaCategoriasResponse.add(mapper.toDto(categoria));
		}

		return new ResponseEntity<List<CategoriaDTOResponse>>(listaCategoriasResponse, HttpStatus.OK);
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<CategoriaDTOResponse> getById(@PathVariable Long id) throws ItemNotFoundException {
//		CategoriaDTOResponse categoriaResponse = mapper.toDto(service.getById(id));
//
//		return new ResponseEntity<CategoriaDTOResponse>(categoriaResponse, HttpStatus.OK);
//	}

	@GetMapping("busca")
	public ResponseEntity<CategoriaDTOResponse> getByName(@RequestParam String nome) throws ItemNotFoundException {
		CategoriaDTOResponse categoriaResponse = mapper.toDto(service.getByName(nome));

		return new ResponseEntity<CategoriaDTOResponse>(categoriaResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody CategoriaDTORequest categoria) throws ItemNotFoundException {
		service.create(categoria);

		return new ResponseEntity<String>("Categoria cadastrada com sucesso", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody CategoriaDTORequest categoria)
			throws ItemNotFoundException {
		service.update(id, categoria);

		return new ResponseEntity<String>("Categoria editada com sucesso", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id)
			throws ItemNotFoundException, CategoryReferencedByProductException {
		service.delete(id);

		return new ResponseEntity<String>("Categoria deletada com sucesso", HttpStatus.OK);
	}
}
