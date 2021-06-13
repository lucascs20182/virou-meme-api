package org.serratec.viroumemeapi.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.serratec.viroumemeapi.dtos.ProdutoDTORequest;
import org.serratec.viroumemeapi.dtos.ProdutoDTOResponse;
import org.serratec.viroumemeapi.entities.ImagemEntity;
import org.serratec.viroumemeapi.entities.ProdutoEntity;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.ProdutoMapper;
import org.serratec.viroumemeapi.services.ImagemService;
import org.serratec.viroumemeapi.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService service;

	@Autowired
	ProdutoMapper mapper;
	
	@Autowired
	ImagemService imagemService;

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
	
	@GetMapping("/{produtoId}/image")
	public ResponseEntity<byte[]> getImagem(@PathVariable Long produtoId){
		ImagemEntity imagem = imagemService.getImagem(produtoId);
		HttpHeaders header = new HttpHeaders();
		header.add("content-length", String.valueOf(imagem.getData().length));
		header.add("content-type", imagem.getMimeType());
		return new ResponseEntity<>(imagem.getData(), header, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestParam MultipartFile file, @RequestPart ProdutoDTORequest produto)
			throws ItemNotFoundException, IOException {
		service.create(produto, file);

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
