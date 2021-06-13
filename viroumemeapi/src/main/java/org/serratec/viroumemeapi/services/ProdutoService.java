package org.serratec.viroumemeapi.services;

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

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ProdutoMapper produtoMapper;

	@Autowired
	CategoriaService categoriaService;

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

	public ProdutoEntity create(ProdutoDTORequest dto) throws ItemNotFoundException {
		ProdutoEntity entity = produtoMapper.toEntity(dto);

		entity.setDataCadastro(LocalDate.now());

		return produtoRepository.save(entity);
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
