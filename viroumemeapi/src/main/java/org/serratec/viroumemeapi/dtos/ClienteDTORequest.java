package org.serratec.viroumemeapi.dtos;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClienteDTORequest {

	private String email;

	private String username;

	private String senha;

	private String nome;

	private String cpf;

	private String telefone;

	private LocalDate dataNascimento;

	@Schema(description = "Na edição do cliente esse campo não é necessário. "
			+ "O endereço do cliente pode ser editado no endpoint /endereco")
	private List<EnderecoDTORequest> enderecosDoCliente;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public List<EnderecoDTORequest> getEnderecosDoCliente() {
		return enderecosDoCliente;
	}

	public void setEnderecosDoCliente(List<EnderecoDTORequest> enderecosDoCliente) {
		this.enderecosDoCliente = enderecosDoCliente;
	}
}
