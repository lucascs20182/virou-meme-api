package org.serratec.viroumemeapi.repositories;

import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

	ClienteEntity findByUsername(String username);

}
