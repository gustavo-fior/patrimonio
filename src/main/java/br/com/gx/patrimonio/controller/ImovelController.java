package br.com.gx.patrimonio.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gx.patrimonio.repository.ImovelRepository;

@Controller
@RequestMapping("imoveis")
public class ImovelController {

	@Autowired
	private ImovelRepository imovelRepository;
	
	@GetMapping("apagar/{id}")
	@Transactional
	@CacheEvict(allEntries = true, value = "home")
	public ModelAndView apagarImovel(@PathVariable Long id) {
		
		imovelRepository.deleteById(id);
		
		return new ModelAndView("redirect:/users/home");
		
	}
	
}
