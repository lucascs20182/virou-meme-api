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
import org.serratec.viroumemeapi.repositories.DetalhesPedidoRepository;
import org.serratec.viroumemeapi.repositories.PedidoRepository;
import org.serratec.viroumemeapi.util.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	DetalhesPedidoRepository detalhesPedidoRepository;

	@Autowired
	PedidoMapper pedidoMapper;
	
	@Autowired
	ProdutoService produtoService;

//	@Autowired
//	DetalhesPedidoService detalhesPedidoService;

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

//	@Transactional
	public PedidoEntity create(PedidoDTORequest dto) throws ItemNotFoundException {
		PedidoEntity entity = pedidoMapper.toEntity(dto);

		entity.setNumeroPedido(new NumberGenerator().generate());

		entity.setStatus(StatusPedido.NAO_FINALIZADO);

		entity.setDataQuePedidoFoiFeito(LocalDate.now());

		entity.setDataEntrega(LocalDate.now().plusDays(15));
		
		pedidoRepository.save(entity);

		List<DetalhesPedidoEntity> produtosDoPedido = new ArrayList<DetalhesPedidoEntity>();

		// cria os detalhes de pedido
		for (DetalhesPedidoDTORequest detalhesPedido : dto.getProdutosDoPedido()) {
			DetalhesPedidoEntity detalhesPedidoEntity = detalhesPedidoMapper.toEntity(detalhesPedido);

			detalhesPedidoEntity.setPedido(entity);

			DetalhesPedidoEntity produtoDoPedido = detalhesPedidoRepository.save(detalhesPedidoEntity);

			produtosDoPedido.add(produtoDoPedido);
		}
		
		System.out.println("valor: " + produtosDoPedido.get(0));

		entity.setProdutosDoPedido(produtosDoPedido);
		pedidoRepository.save(entity);

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
}
