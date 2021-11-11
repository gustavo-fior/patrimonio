package br.com.gx.patrimonio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gx.patrimonio.api.dto.DashboardDTO;
import br.com.gx.patrimonio.repository.ImovelRepository;
import br.com.gx.patrimonio.repository.UserRepository;

@RestController
@RequestMapping("/dashboard")
public class DashboardRest {

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public DashboardDTO dashboard() {

		DashboardDTO dashboardDTO = new DashboardDTO();

		dashboardDTO.setDados(imovelRepository, userRepository);

		return dashboardDTO;

	}

}
