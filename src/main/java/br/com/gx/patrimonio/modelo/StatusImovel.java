package br.com.gx.patrimonio.modelo;

import java.util.HashMap;
import java.util.Map;

public enum StatusImovel {

	ALUGADO(1), VAGO(2), CONSTRUINDO(3);

	private int valor;

	// Map de integer e enum
	private final static Map<Integer, StatusImovel> map = new HashMap<>();

	StatusImovel(int i) {
		this.valor = i;
	}

	// Map de integer e enum
	static {
		for (StatusImovel status : StatusImovel.values()) {
			map.put(status.valor, status);
		}
	}

	public int getValor() {
		return valor;
	}

	// Converte de int para enum
	public static StatusImovel valueOf(int status) {
		return map.get(status);
	}

}
