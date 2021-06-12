package org.serratec.viroumemeapi.dtos;

public class EnderecoDTOResponse {
	private Long id;

	private String cep;

	private String rua;

	private String bairro;

	private String cidade;

	private Integer numero;

	private String complemento;

	private String estado;

	private Long idClienteQueMoraNoEndereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getIdClienteQueMoraNoEndereco() {
		return idClienteQueMoraNoEndereco;
	}

	public void setIdClienteQueMoraNoEndereco(Long idClienteQueMoraNoEndereco) {
		this.idClienteQueMoraNoEndereco = idClienteQueMoraNoEndereco;
	}
}
