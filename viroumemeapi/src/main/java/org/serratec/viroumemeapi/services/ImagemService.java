package org.serratec.viroumemeapi.services;

import java.io.IOException;

import javax.transaction.Transactional;

import org.serratec.viroumemeapi.entities.ImagemEntity;
import org.serratec.viroumemeapi.entities.ProdutoEntity;
import org.serratec.viroumemeapi.repositories.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagemService {

	@Autowired
	ImagemRepository repository;

	@Transactional
	public ImagemEntity create(ProdutoEntity produtoEntity, MultipartFile multipartFile) throws IOException {
		ImagemEntity imageProduto = new ImagemEntity();
		imageProduto.setProduto(produtoEntity);
		imageProduto.setData(multipartFile.getBytes());
		imageProduto.setMimeType(multipartFile.getContentType());
		imageProduto.setNome(produtoEntity.getNome().replace(" ", "-") + "-img");
		return repository.save(imageProduto);
	}

	@Transactional
	public ImagemEntity getImagem(Long id) {
		ImagemEntity image = repository.findByProdutoId(id);
		return image;
	}
}
