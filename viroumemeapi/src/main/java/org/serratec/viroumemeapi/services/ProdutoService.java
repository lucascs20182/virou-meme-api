package org.serratec.viroumemeapi.services;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.serratec.viroumemeapi.dtos.ProdutoDTORequest;
import org.serratec.viroumemeapi.entities.ProdutoEntity;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.ProdutoMapper;
import org.serratec.viroumemeapi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ProdutoMapper produtoMapper;

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	ImagemService imagemService;

	public List<ProdutoEntity> getAll() {
		return produtoRepository.findAll();
	}

	public ProdutoEntity getById(Long id) throws ItemNotFoundException {
		Optional<ProdutoEntity> produto = produtoRepository.findById(id);

		if (produto.isEmpty()) {
			throw new ItemNotFoundException("Não existe produto com esse Id.");
		}

		return produto.get();
	}

	public ProdutoEntity getByName(String nome) throws ItemNotFoundException {
		List<ProdutoEntity> produto = produtoRepository.findByNome(nome);

		if (produto.isEmpty()) {
			throw new ItemNotFoundException("Não existe produto com esse nome.");
		}

		return produto.get(0);
	}

	public ProdutoEntity getImagem(ProdutoEntity produtoEntity) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produto/{produtoId}/image")
				.buildAndExpand(produtoEntity.getId()).toUri();
		produtoEntity.setUrlImagem(uri.toString());
		return produtoEntity;
	}

	public ProdutoEntity create(ProdutoDTORequest dto, MultipartFile multipartFile)
			throws ItemNotFoundException, IOException {
		ProdutoEntity entity = produtoMapper.toEntity(dto);

		entity.setDataCadastro(LocalDate.now());

		ProdutoEntity produtoEntity = produtoRepository.save(entity);

		imagemService.create(produtoEntity, multipartFile);

		return produtoRepository.save(getImagem(produtoEntity));
	}

	public ProdutoEntity updateQuantidadeEmEstoque(Long id, Integer novaQuantidade) throws ItemNotFoundException {
		ProdutoEntity entity = this.getById(id);

		entity.setQuantidadeEmEstoque(novaQuantidade);

		return produtoRepository.save(entity);
	}

	public ProdutoEntity update(Long id, ProdutoDTORequest dto) throws ItemNotFoundException {
		ProdutoEntity entity = this.getById(id);

		if (dto.getNome() != null) {
			entity.setNome(dto.getNome());
		}

		if (dto.getDescricao() != null) {
			entity.setDescricao(dto.getDescricao());
		}

		if (dto.getPreco() != null) {
			entity.setPreco(dto.getPreco());
		}

		if (dto.getQuantidadeEmEstoque() != null) {
			entity.setQuantidadeEmEstoque(dto.getQuantidadeEmEstoque());
		}

		if (dto.getCategoriaId() != null) {
			entity.setCategoria(categoriaService.getById(dto.getCategoriaId()));
		}

		return produtoRepository.save(entity);
	}

	public void delete(Long id) throws ItemNotFoundException {
		this.getById(id);

		produtoRepository.deleteById(id);
	}
}
