package org.serratec.viroumemeapi.services;

import java.util.List;
import java.util.Optional;

import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTORequest;
import org.serratec.viroumemeapi.entities.DetalhesPedidoEntity;
import org.serratec.viroumemeapi.entities.PedidoEntity;
import org.serratec.viroumemeapi.enums.StatusPedido;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.DetalhesPedidoMapper;
import org.serratec.viroumemeapi.repositories.DetalhesPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalhesPedidoService {

	@Autowired
	DetalhesPedidoRepository detalhesPedidoRepository;

	@Autowired
	DetalhesPedidoMapper detalhesPedidoMapper;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	ProdutoService produtoService;

	public List<DetalhesPedidoEntity> getAll() {
		return detalhesPedidoRepository.findAll();
	}

	public DetalhesPedidoEntity getById(Long id) throws ItemNotFoundException {
		Optional<DetalhesPedidoEntity> pedido = detalhesPedidoRepository.findById(id);

		if (pedido.isEmpty()) {
			throw new ItemNotFoundException("Não existem Detalhes do Pedido com esse Id.");
		}

		return pedido.get();
	}

	public DetalhesPedidoEntity create(DetalhesPedidoDTORequest dto) throws ItemNotFoundException {
		DetalhesPedidoEntity entity = detalhesPedidoMapper.toEntity(dto);

		// PODE SER AQUI O PROBLEMA
		PedidoEntity pedido = pedidoService.getById(dto.getIdPedido());

		if (pedido.getStatus() != StatusPedido.NAO_FINALIZADO) {
			throw new ItemNotFoundException("Componentes de Pedido finalizado não podem ser alterados.");
		}

		detalhesPedidoRepository.save(entity);

		// recalcula valorTotal e dataEntrega
		pedidoService.update(entity.getPedido().getId());

		return entity;
	}

	public DetalhesPedidoEntity update(Long id, DetalhesPedidoDTORequest dto) throws ItemNotFoundException {

		DetalhesPedidoEntity entity = this.getById(id);

		if (entity.getPedido().getStatus() != StatusPedido.NAO_FINALIZADO) {
			throw new ItemNotFoundException("Componentes de Pedido finalizado não podem ser alterados.");
		}

		if (dto.getIdProduto() != null) {
			entity.setProduto(produtoService.getById(dto.getIdProduto()));
		}

		if (dto.getQuantidade() != null) {
			entity.setQuantidade(dto.getQuantidade());
		}

		detalhesPedidoRepository.save(entity);

		// PODE SER AQUI O PROBLEMA ok
		pedidoService.update(entity.getPedido().getId());

		return entity;
	}

	public void delete(Long id) throws ItemNotFoundException {
		DetalhesPedidoEntity entity = this.getById(id);

		if (entity.getPedido().getStatus() != StatusPedido.NAO_FINALIZADO) {
			throw new ItemNotFoundException("Componentes de Pedido finalizado não podem ser excluídos.");
		}

		detalhesPedidoRepository.deleteById(id);
	}
}
