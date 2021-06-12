package org.serratec.viroumemeapi.dtos;

import java.time.LocalDate;
import java.util.List;

public class ClienteDTOResponse {

	private Long id;

	private String email;

	private String username;

	private String nome;

	private String cpf;

	private String telefone;

	private LocalDate dataNascimento;

	private List<Long> idsDosEnderecosDoCliente;

	private List<Long> idsDosPedidosDoCliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Long> getIdsDosEnderecosDoCliente() {
		return idsDosEnderecosDoCliente;
	}

	public void setIdsDosEnderecosDoCliente(List<Long> idsDosEnderecosDoCliente) {
		this.idsDosEnderecosDoCliente = idsDosEnderecosDoCliente;
	}

	public List<Long> getIdsDosPedidosDoCliente() {
		return idsDosPedidosDoCliente;
	}

	public void setIdsDosPedidosDoCliente(List<Long> idsDosPedidosDoCliente) {
		this.idsDosPedidosDoCliente = idsDosPedidosDoCliente;
	}
}
