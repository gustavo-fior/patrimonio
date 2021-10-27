package br.com.gx.patrimonio.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gx.patrimonio.controller.form.ImovelForm;
import br.com.gx.patrimonio.modelo.Imovel;
import br.com.gx.patrimonio.modelo.User;
import br.com.gx.patrimonio.repository.ImovelRepository;
import br.com.gx.patrimonio.repository.UserRepository;

@Controller
@RequestMapping("imoveis")
public class ImovelController {

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("apagar/{id}")
	@Transactional
	public ModelAndView apagarImovel(@PathVariable Long id) {

		imovelRepository.deleteById(id);

		return new ModelAndView("redirect:/users/home");

	}

	@GetMapping("/novo")
	public ModelAndView novo(ImovelForm form) {

		return new ModelAndView("imoveis/novo");

	}

	@PostMapping("/novo")
	@Transactional
	public ModelAndView novoImovel(@Valid ImovelForm form, BindingResult result) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userRepository.findByUsername(username);

		if (result.hasErrors() || !user.isPresent()) {

			return new ModelAndView("/imoveis/novo");
		}

		Imovel imovel = form.toImovel();

		imovel.setUser(user.get());

		imovelRepository.save(imovel);

		return new ModelAndView("redirect:/users/home");
	}
	
	@GetMapping("/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable Long id){
		
		Optional<Imovel> imovel = imovelRepository.findById(id);
		
		if (!imovel.isPresent()) {
			return new ModelAndView("users/home");
		}
		
		ImovelForm imovelForm = new ImovelForm().toImovelForm(imovel.get());
		
		ModelAndView mv = new ModelAndView("imoveis/atualizar");
		mv.addObject("imovelForm", imovelForm);
		mv.addObject("id", id);

		return mv;
		
	}
	
	@PostMapping("atualizar/{id}")
	public ModelAndView atualizarImovel(@PathVariable Long id, @Valid ImovelForm form, BindingResult result) {
		
		Optional<Imovel> imovel = imovelRepository.findById(id);
		
		if (result.hasErrors() || !imovel.isPresent()) {
			
			return new ModelAndView("/imoveis/atualizar");
		}
		
		Imovel imovelNovo = imovel.get().atualizar(form);
		
		imovelRepository.save(imovelNovo);
		
		return new ModelAndView("redirect:/users/home");
		
		
	}

}
