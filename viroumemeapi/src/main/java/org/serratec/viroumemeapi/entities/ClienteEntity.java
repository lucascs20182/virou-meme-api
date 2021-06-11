package org.serratec.viroumemeapi.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String username;

	private String senha;

	private String nome;

	@Column(unique = true)
	private String cpf;

	private String telefone;

	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "cliente")
	private List<EnderecoEntity> enderecosDoCliente;

	@OneToMany(mappedBy = "cliente")
	private List<PedidoEntity> pedidosDoCliente;

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

	public List<EnderecoEntity> getEnderecosDoCliente() {
		return enderecosDoCliente;
	}

	public void setEnderecosDoCliente(List<EnderecoEntity> enderecosDoCliente) {
		this.enderecosDoCliente = enderecosDoCliente;
	}

	public List<PedidoEntity> getPedidosDoCliente() {
		return pedidosDoCliente;
	}

	public void setPedidosDoCliente(List<PedidoEntity> pedidosDoCliente) {
		this.pedidosDoCliente = pedidosDoCliente;
	}
}
