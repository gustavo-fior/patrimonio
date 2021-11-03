package br.com.gx.patrimonio.api.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.gx.patrimonio.modelo.Imovel;
import br.com.gx.patrimonio.modelo.StatusImovel;
import br.com.gx.patrimonio.modelo.User;
import br.com.gx.patrimonio.repository.ImovelRepository;
import br.com.gx.patrimonio.repository.UserRepository;

public class DashboardDTO {

	private BigDecimal totalAluguel;
	private Integer totalImoveisAlugados;
	private Integer totalImoveisVagos;
	private Integer totalImoveisConstruindo;
	private List<Imovel> imoveis;

	public DashboardDTO() {
	}

	// Colocando os dados neste DTO
	public void setDados(ImovelRepository imovelRepository, UserRepository userRespotiory) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userRespotiory.findByUsername(username);

		this.totalImoveisAlugados = imovelRepository.findByStatus(StatusImovel.ALUGADO).size();
		this.totalImoveisVagos = imovelRepository.findByStatus(StatusImovel.VAGO).size();
		this.totalImoveisConstruindo = imovelRepository.findByStatus(StatusImovel.CONSTRUINDO).size();
		this.imoveis = imovelRepository.findByUserOrderByAluguelDesc(user.get());
		this.totalAluguel = new BigDecimal(0);

		imoveis.forEach(i -> {

			this.totalAluguel = this.totalAluguel.add(i.getAluguel());

		});

	}

	public BigDecimal getTotalAluguel() {
		return totalAluguel;
	}

	public void setTotalAluguel(BigDecimal totalAluguel) {
		this.totalAluguel = totalAluguel;
	}

	public Integer getTotalImoveisAlugados() {
		return totalImoveisAlugados;
	}

	public void setTotalImoveisAlugados(Integer totalImoveisAlugados) {
		this.totalImoveisAlugados = totalImoveisAlugados;
	}

	public Integer getTotalImoveisVagos() {
		return totalImoveisVagos;
	}

	public void setTotalImoveisVagos(Integer totalImoveisVagos) {
		this.totalImoveisVagos = totalImoveisVagos;
	}

	public Integer getTotalImoveisConstruindo() {
		return totalImoveisConstruindo;
	}

	public void setTotalImoveisConstruindo(Integer totalImoveisConstruindo) {
		this.totalImoveisConstruindo = totalImoveisConstruindo;
	}

	public List<Imovel> getImoveis() {
		return imoveis;
	}

	public void setImoveis(List<Imovel> imoveis) {
		this.imoveis = imoveis;
	}

}
