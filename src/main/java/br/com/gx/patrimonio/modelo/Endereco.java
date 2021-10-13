package br.com.gx.patrimonio.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	private String rua;
	private Integer numero;
	private String cep;

	public Endereco() {
	}
	
	public Endereco(String rua, String numero, String cep) {
		this.rua = rua;
		this.numero = Integer.valueOf(numero) ;
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
