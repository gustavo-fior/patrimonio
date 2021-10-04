package br.com.gx.patrimonio.modelo;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imoveis")
public class Imovel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@Embedded
	private Endereco endereco;
	private String inquilino;
	private BigDecimal aluguel;

	@Enumerated(EnumType.STRING)
	private StatusImovel status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getInquilino() {
		return inquilino;
	}

	public void setInquilino(String inquilino) {
		this.inquilino = inquilino;
	}

	public BigDecimal getAluguel() {
		return aluguel;
	}

	public void setAluguel(BigDecimal aluguel) {
		this.aluguel = aluguel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public StatusImovel getStatus() {
		return status;
	}

	public void setStatus(StatusImovel status) {
		this.status = status;
	}

}
