package org.serratec.viroumemeapi.repositories;

import org.serratec.viroumemeapi.entities.ImagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<ImagemEntity, Long> {

	ImagemEntity findByProdutoId(Long id);

}
