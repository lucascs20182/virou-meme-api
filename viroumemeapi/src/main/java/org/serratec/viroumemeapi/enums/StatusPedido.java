package org.serratec.viroumemeapi.enums;

public enum StatusPedido {
	FINALIZADO("Finalizado"), NAO_FINALIZADO("Não finalizado");

	private String valorDoStatus;

	private StatusPedido(String valorDoStatus) {
		this.valorDoStatus = valorDoStatus;
	}

	public String getValorDoStatus() {
		return valorDoStatus;
	}

}
