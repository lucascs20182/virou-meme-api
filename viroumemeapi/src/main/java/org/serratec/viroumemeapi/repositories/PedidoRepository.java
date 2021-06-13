package org.serratec.viroumemeapi.repositories;

import java.util.List;

import org.serratec.viroumemeapi.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

	List<PedidoEntity> findByNumeroPedido(String numeroPedido);
}
