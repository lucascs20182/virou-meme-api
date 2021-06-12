package org.serratec.viroumemeapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTORequest;
import org.serratec.viroumemeapi.dtos.PedidoDTORequest;
import org.serratec.viroumemeapi.entities.DetalhesPedidoEntity;
import org.serratec.viroumemeapi.entities.PedidoEntity;
import org.serratec.viroumemeapi.entities.ProdutoEntity;
import org.serratec.viroumemeapi.enums.StatusPedido;
import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
import org.serratec.viroumemeapi.mappers.DetalhesPedidoMapper;
import org.serratec.viroumemeapi.mappers.PedidoMapper;
import org.serratec.viroumemeapi.repositories.PedidoRepository;
import org.serratec.viroumemeapi.util.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	PedidoMapper pedidoMapper;

	@Autowired
	ProdutoService produtoService;

	@Autowired
	DetalhesPedidoService detalhesPedidoService;

	@Autowired
	DetalhesPedidoMapper detalhesPedidoMapper;

	public List<PedidoEntity> getAll() {
		return pedidoRepository.findAll();
	}

	public PedidoEntity getById(Long id) throws ItemNotFoundException {
		Optional<PedidoEntity> pedido = pedidoRepository.findById(id);

		if (pedido.isEmpty()) {
			throw new ItemNotFoundException("Não existe pedido com esse Id.");
		}

		return pedido.get();
	}

	public PedidoEntity create(PedidoDTORequest dto) throws ItemNotFoundException {
		PedidoEntity entity = pedidoMapper.toEntity(dto);

		entity.setNumeroPedido(new NumberGenerator().generate());

		entity.setStatus(StatusPedido.NAO_FINALIZADO);

		entity.setDataQuePedidoFoiFeito(LocalDate.now());

		entity.setDataEntrega(LocalDate.now().plusDays(15));

		// salva a entity incompleta para referenciar na criação dos detalhes do pedido
		pedidoRepository.save(entity);

		List<DetalhesPedidoEntity> produtosDoPedido = new ArrayList<DetalhesPedidoEntity>();

		// cria os detalhes de pedido
		for (DetalhesPedidoDTORequest detalhesPedido : dto.getProdutosDoPedido()) {

			detalhesPedido.setIdPedido(entity.getId());

			DetalhesPedidoEntity produtoDoPedido = detalhesPedidoService.create(detalhesPedido);

			produtosDoPedido.add(produtoDoPedido);
		}

		entity.setProdutosDoPedido(produtosDoPedido);

		// preenche pedidosDoProduto no ProdutoEntity
		for (DetalhesPedidoDTORequest detalhesPedido : dto.getProdutosDoPedido()) {
			DetalhesPedidoEntity detalhesPedidoEntity = detalhesPedidoMapper.toEntity(detalhesPedido);

			ProdutoEntity produto = produtoService.getById(detalhesPedido.getIdProduto());

			List<DetalhesPedidoEntity> pedidosComEsseProduto = produto.getPedidosDoProduto();

			pedidosComEsseProduto.add(detalhesPedidoEntity);

			produto.setPedidosDoProduto(pedidosComEsseProduto);
		}

		Double valorTotal = 0.0;

		// calcula o valorTotal
		for (DetalhesPedidoEntity detalhesPedido : entity.getProdutosDoPedido()) {
			valorTotal += detalhesPedido.getPreco() * detalhesPedido.getQuantidade();
		}

		entity.setValorTotal(valorTotal);

		return pedidoRepository.save(entity);
	}

	public PedidoEntity update(Long id, DetalhesPedidoDTORequest dto) throws ItemNotFoundException {
//		PedidoEntity entity = this.getById(id);
//		
//		if (entity.getStatus() != StatusPedido.NAO_FINALIZADO) {
//			throw new ItemNotFoundException("Pedido finalizado não pode ser alterado.");
//		}
//
//		List<DetalhesPedidoEntity> produtosDoPedido = new ArrayList<DetalhesPedidoEntity>();
//
//		// cria os detalhes de pedido
//		for (DetalhesPedidoEntity detalhesPedido : entity.getProdutosDoPedido()) {
//
//			detalhesPedido.set
//			detalhesPedido.setIdPedido(entity.getId());
//
//			DetalhesPedidoEntity produtoDoPedido = detalhesPedidoService.create(detalhesPedido);
//
//			produtosDoPedido.add(produtoDoPedido);
//		}
//
//		entity.setProdutosDoPedido(produtosDoPedido);
//		
//		// preenche pedidosDoProduto no ProdutoEntity
//		for (DetalhesPedidoDTORequest detalhesPedido : dto.getProdutosDoPedido()) {
//			DetalhesPedidoEntity detalhesPedidoEntity = detalhesPedidoMapper.toEntity(detalhesPedido);
//
//			ProdutoEntity produto = produtoService.getById(detalhesPedido.getIdProduto());
//
//			List<DetalhesPedidoEntity> pedidosComEsseProduto = produto.getPedidosDoProduto();
//
//			pedidosComEsseProduto.add(detalhesPedidoEntity);
//
//			produto.setPedidosDoProduto(pedidosComEsseProduto);
//		}
//
//		Double valorTotal = 0.0;
//
//		// calcula o valorTotal
//		for (DetalhesPedidoEntity detalhesPedido : entity.getProdutosDoPedido()) {
//			valorTotal += detalhesPedido.getPreco() * detalhesPedido.getQuantidade();
//		}
//
//		entity.setValorTotal(valorTotal);
//		
//		LocalDate dataQuePedidoFoiFinalizado = LocalDate.now();
//		entity.setDataEntrega(dataQuePedidoFoiFinalizado.plusDays(15));
//
//		return pedidoRepository.save(entity);
		
		return null;
	}

	public PedidoEntity updateStatus(Long id) throws ItemNotFoundException {
		PedidoEntity entity = this.getById(id);

		if (entity.getStatus() != StatusPedido.NAO_FINALIZADO) {
			throw new ItemNotFoundException("Pedido finalizado não pode ser alterado.");
		}

		LocalDate dataQuePedidoFoiFinalizado = LocalDate.now();
		entity.setDataEntrega(dataQuePedidoFoiFinalizado.plusDays(15));

		entity.setStatus(StatusPedido.FINALIZADO);

		return pedidoRepository.save(entity);
	}

	public void delete(Long id) throws ItemNotFoundException {

		PedidoEntity entity = this.getById(id);

		if (entity.getStatus() != StatusPedido.NAO_FINALIZADO) {
			throw new ItemNotFoundException("Pedido finalizado não pode ser excluído.");
		}

		// todos os detalhes do pedido devem ser deletados ao deletar o pedido
		if (entity.getProdutosDoPedido() != null) {
			for (DetalhesPedidoEntity detalhesPedido : entity.getProdutosDoPedido()) {
				detalhesPedidoService.delete(detalhesPedido.getId());
			}
		}

		pedidoRepository.deleteById(id);
	}
}
