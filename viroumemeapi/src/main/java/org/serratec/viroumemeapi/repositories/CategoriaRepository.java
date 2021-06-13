package org.serratec.viroumemeapi.repositories;

import java.util.List;

import org.serratec.viroumemeapi.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

	List<CategoriaEntity> findByNome(String nome);
}
