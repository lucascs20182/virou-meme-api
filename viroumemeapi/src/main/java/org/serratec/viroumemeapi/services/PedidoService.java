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

		// salva a entity incompleta para referenciá-la na criação dos detalhes do
		// pedido
		entity = pedidoRepository.save(entity);

		List<DetalhesPedidoEntity> produtosDoPedido = new ArrayList<DetalhesPedidoEntity>();

		// cria os detalhes de pedido
		for (DetalhesPedidoDTORequest detalhesPedido : dto.getProdutosDoPedido()) {

			detalhesPedido.setIdPedido(entity.getId());

			try {
				DetalhesPedidoEntity produtoDoPedido = detalhesPedidoService.create(detalhesPedido);

				produtosDoPedido.add(produtoDoPedido);
			} catch (ItemNotFoundException exception) {
				throw new ItemNotFoundException(
						"Não existe produto com esse Id. " + "O pedido foi criado sem produtos.");
			}
		}

		entity.setProdutosDoPedido(produtosDoPedido);

		entity = pedidoRepository.save(entity);

		// atualiza o pedido calculando valorTotal e dataEntrega
		return this.update(entity.getId());
	}

	public PedidoEntity update(Long id) throws ItemNotFoundException {
		PedidoEntity entity = this.getById(id);

		if (entity.getStatus() != StatusPedido.NAO_FINALIZADO) {
			throw new ItemNotFoundException("Pedido finalizado não pode ser alterado.");
		}

		// identifica se o pedido está sendo criado com detalhes do pedido embutido
		// nesse caso já está sendo atualizado na criação, que também chama o método
		// update
		if (entity.getProdutosDoPedido() == null) {
			return pedidoRepository.save(entity);
		}

		// preenche pedidosDoProduto no ProdutoEntity
		for (DetalhesPedidoEntity detalhesPedido : entity.getProdutosDoPedido()) {

			ProdutoEntity produto = detalhesPedido.getProduto();

			List<DetalhesPedidoEntity> pedidosComEsseProduto = produto.getPedidosDoProduto();

			pedidosComEsseProduto.add(detalhesPedido);

			produto.setPedidosDoProduto(pedidosComEsseProduto);
		}

		Double valorTotal = 0.0;

		// calcula o valorTotal
		for (DetalhesPedidoEntity detalhesPedido : entity.getProdutosDoPedido()) {
			valorTotal += detalhesPedido.getPreco() * detalhesPedido.getQuantidade();
		}

		entity.setValorTotal(valorTotal);

		LocalDate dataQuePedidoFoiFinalizado = LocalDate.now();
		entity.setDataEntrega(dataQuePedidoFoiFinalizado.plusDays(15));

		return pedidoRepository.save(entity);
	}

	public PedidoEntity updateStatus(Long id) throws ItemNotFoundException {
		PedidoEntity entity = this.getById(id);

		if (entity.getStatus() != StatusPedido.NAO_FINALIZADO) {
			throw new ItemNotFoundException("O status do pedido já é finalizado.");
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
		// não testado
		if (entity.getProdutosDoPedido() != null) {
			for (DetalhesPedidoEntity detalhesPedido : entity.getProdutosDoPedido()) {
				detalhesPedidoService.delete(detalhesPedido.getId());
			}
		}

		pedidoRepository.deleteById(id);
	}
}
