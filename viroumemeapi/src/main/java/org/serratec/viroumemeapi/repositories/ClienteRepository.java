package org.serratec.viroumemeapi.repositories;

import java.util.Optional;

import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

	Optional<ClienteEntity> findByUsername(String username);

}
