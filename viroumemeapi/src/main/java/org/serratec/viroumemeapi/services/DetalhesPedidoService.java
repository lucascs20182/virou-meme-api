//package org.serratec.viroumemeapi.services;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.serratec.viroumemeapi.dtos.CategoriaDTORequest;
//import org.serratec.viroumemeapi.dtos.DetalhesPedidoDTORequest;
//import org.serratec.viroumemeapi.entities.CategoriaEntity;
//import org.serratec.viroumemeapi.entities.DetalhesPedidoEntity;
//import org.serratec.viroumemeapi.exceptions.ItemNotFoundException;
//import org.serratec.viroumemeapi.mappers.DetalhesPedidoMapper;
//import org.serratec.viroumemeapi.repositories.DetalhesPedidoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DetalhesPedidoService {
//
//	@Autowired
//	DetalhesPedidoRepository detalhesPedidoRepository;
//
//	@Autowired
//	DetalhesPedidoMapper detalhesPedidoMapper;
//
//	public List<DetalhesPedidoEntity> getAll() {
//		return detalhesPedidoRepository.findAll();
//	}
//
//	public DetalhesPedidoEntity getById(Long id) throws ItemNotFoundException {
//		Optional<DetalhesPedidoEntity> pedido = detalhesPedidoRepository.findById(id);
//
//		if (pedido.isEmpty()) {
//			throw new ItemNotFoundException("Não existem Detalhes do Pedido com esse Id.");
//		}
//
//		return pedido.get();
//	}
//
//	public DetalhesPedidoEntity create(DetalhesPedidoDTORequest dto) throws ItemNotFoundException {
//		DetalhesPedidoEntity entity = detalhesPedidoMapper.toEntity(dto);
//
//		return detalhesPedidoRepository.save(entity);
//	}
//}
