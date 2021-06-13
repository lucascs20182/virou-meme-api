package org.serratec.viroumemeapi.repositories;

import java.util.List;

import org.serratec.viroumemeapi.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

	List<ProdutoEntity> findByNome(String nome);
}
