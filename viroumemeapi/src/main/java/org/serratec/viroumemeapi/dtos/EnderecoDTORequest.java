package org.serratec.viroumemeapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnderecoDTORequest {

	@Schema(description = "A partir do CEP será gerado os demais atributos do endereço")
	private String cep;

	@Schema(description = "Número do endereço")
	private Integer numero;

	@Schema(description = "Complemento do endereço (Apto, bloco etc.)")
	private String complemento;

	@Schema(description = "Na criação do usuário ou na edição de endereço esse campo não é necessário")
	private Long clienteId;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
}
