package br.com.gx.patrimonio.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gx.patrimonio.controller.dto.ImovelForm;
import br.com.gx.patrimonio.modelo.Imovel;
import br.com.gx.patrimonio.modelo.User;
import br.com.gx.patrimonio.repository.ImovelRepository;
import br.com.gx.patrimonio.repository.UserRepository;

@Controller
@RequestMapping("users")
public class UsersController {

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/novo")
	@Transactional
	public ModelAndView novoImovel(@Valid ImovelForm form, BindingResult result) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userRepository.findByUsername(username);

		if (result.hasErrors() || !user.isPresent()) {

			return new ModelAndView("/users/novo");
		}

		Imovel imovel = form.toImovel();

		imovel.setUser(user.get());

		imovelRepository.save(imovel);

		return new ModelAndView("redirect:/users/home");
	}

	@GetMapping("/novo")
	public ModelAndView novo(ImovelForm form) {

		return new ModelAndView("users/novo");

	}

	@GetMapping("/home")
	@Cacheable(value = "home")
	public ModelAndView home() {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		// PageRequest pageable = PageRequest.of(0, 10);

		Optional<User> user = userRepository.findByUsername(username);
		List<Imovel> imoveis = imovelRepository.findByUserOrderByAluguelDesc(user.get());

		ModelAndView mv = new ModelAndView("/users/home");
		mv.addObject("imoveis", imoveis);

		return mv;

	}
}
