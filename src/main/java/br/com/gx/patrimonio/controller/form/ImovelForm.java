package br.com.gx.patrimonio.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.gx.patrimonio.modelo.Endereco;
import br.com.gx.patrimonio.modelo.Imovel;
import br.com.gx.patrimonio.modelo.StatusImovel;

public class ImovelForm {

	@NotBlank(message = "Campo obrigatório")
	@Size(max = 8, message = "Apenas números")
	private String cep;

	@NotBlank(message = "Campo obrigatório")
	private String rua;

	@NotBlank(message = "Campo obrigatório")
	@Pattern(regexp = "^\\d+$", message = "Apenas números")
	private String numero;

	@NotBlank(message = "Campo obrigatório")
	private String inquilino;

	@NotBlank(message = "Campo obrigatório")
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$", message = "Utilize o padrão 99999.99")
	private String aluguel;

	@Size(max = 255, message = "Não pode ser maior que 255 caractéres")
	private String linkImagem;

	// Validacao
	private Integer status;

	public ImovelForm() {
	}

	public ImovelForm(@NotBlank(message = "Campo obrigatório") @Size(max = 8, message = "Apenas números") String cep,
			@NotBlank(message = "Campo obrigatório") String rua,
			@NotBlank(message = "Campo obrigatório") @Pattern(regexp = "^\\d+$", message = "Apenas números") String numero,
			@NotBlank(message = "Campo obrigatório") String inquilino,
			@NotBlank(message = "Campo obrigatório") @Pattern(regexp = "^\\d+(\\.\\d+{2})?$", message = "Utilize o padrão 99999.99") String aluguel,
			@Size(max = 255, message = "Não pode ser maior que 255 caractéres") String linkImagem, Integer status) {
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.inquilino = inquilino;
		this.aluguel = aluguel;
		this.linkImagem = linkImagem;
		this.status = status;
	}

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

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Imovel toImovel() {

		Imovel imovel = new Imovel();

		Endereco endereco = new Endereco(this.rua, this.numero, this.cep);

		imovel.setAluguel(new BigDecimal(this.aluguel));
		imovel.setInquilino(this.inquilino);
		imovel.setStatus(StatusImovel.valueOf(Integer.valueOf(this.status)));
		imovel.setEndereco(endereco);
		imovel.setLinkImagem(this.linkImagem);

		return imovel;

	}

	public ImovelForm toImovelForm(Imovel imovel) {
		
		ImovelForm form = new ImovelForm(imovel.getEndereco().getCep(),
				imovel.getEndereco().getRua(),
				imovel.getEndereco().getNumero().toString(),
				imovel.getInquilino(),
				imovel.getAluguel().toString(),
				imovel.getLinkImagem(),
				imovel.getStatus().getValor()
				);
		
		return form;
		
	}

}
