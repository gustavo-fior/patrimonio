package br.com.gx.patrimonio.controller.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.gx.patrimonio.modelo.Endereco;
import br.com.gx.patrimonio.modelo.Imovel;
import br.com.gx.patrimonio.modelo.StatusImovel;

public class ImovelForm {

	@NotNull
	@Size(max = 8)
	private String cep;

	@NotNull
	private String rua;

	@NotNull
	@Pattern(regexp = "^\\d+$")
	private String numero;

	@NotNull
	private String inquilino;

	@NotNull
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
	private String aluguel;

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getInquilino() {
		return inquilino;
	}

	public void setInquilino(String inquilino) {
		this.inquilino = inquilino;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getAluguel() {
		return aluguel;
	}

	public void setAluguel(String aluguel) {
		this.aluguel = aluguel;
	}

	public Imovel toImovel() {

		Imovel imovel = new Imovel();

		Endereco endereco = new Endereco(this.rua, this.numero, this.cep);

		imovel.setAluguel(new BigDecimal(aluguel));
		imovel.setInquilino(this.inquilino);
		imovel.setStatus(StatusImovel.ALUGADO);
		imovel.setEndereco(endereco);

		return imovel;

	}

}
