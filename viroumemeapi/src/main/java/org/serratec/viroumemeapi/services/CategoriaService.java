package org.serratec.viroumemeapi.services;

import java.util.List;
import java.util.Optional;

import org.serratec.viroumemeapi.dtos.CategoriaDTORequest;
import org.serratec.viroumemeapi.entities.CategoriaEntity;
import org.serratec.viroumemeapi.exceptions.CategoryReferencedByProductException;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.CategoriaMapper;
import org.serratec.viroumemeapi.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	CategoriaMapper categoriaMapper;

	public List<CategoriaEntity> getAll() {
		return categoriaRepository.findAll();
	}

	public CategoriaEntity getById(Long id) throws ItemNotFoundException {
		Optional<CategoriaEntity> categoria = categoriaRepository.findById(id);

		if (categoria.isEmpty()) {
			throw new ItemNotFoundException("Não existe categoria com esse Id.");
		}

		return categoria.get();
	}

	public CategoriaEntity create(CategoriaDTORequest dto) throws ItemNotFoundException {
		CategoriaEntity entity = categoriaMapper.toEntity(dto);

		return categoriaRepository.save(entity);
	}

	public CategoriaEntity update(Long id, CategoriaDTORequest dto) throws ItemNotFoundException {
		CategoriaEntity entity = this.getById(id);

		if (dto.getNome() != null) {
			entity.setNome(dto.getNome());
		}

		if (dto.getDescricao() != null) {
			entity.setDescricao(dto.getDescricao());
		}
		return categoriaRepository.save(entity);
	}

	public void delete(Long id) throws ItemNotFoundException, CategoryReferencedByProductException {
		CategoriaEntity categoria = this.getById(id);

		if (!categoria.getProdutosDaCategoria().isEmpty()) {
			throw new CategoryReferencedByProductException(
					"Categorias referenciadas por algum produto não podem ser deletadas.");
		}

		categoriaRepository.deleteById(id);
	}
}
