package br.com.gx.patrimonio.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ModelAndView novoImovel(@Valid ImovelForm form, BindingResult result) {

		Imovel imovel = form.toImovel();

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userRepository.findByUsername(username);

		if (result.hasErrors() || !user.isPresent()) {

			return new ModelAndView("/users/novo");
		}

		imovel.setUser(user.get());

		imovelRepository.save(imovel);
		
		return new ModelAndView("/users/home");
	}

	@GetMapping("/novo")
	public ModelAndView novo(ImovelForm form) {

		return new ModelAndView("users/novo");

	}
}
