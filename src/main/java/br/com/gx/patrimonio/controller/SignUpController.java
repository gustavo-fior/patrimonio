package br.com.gx.patrimonio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gx.patrimonio.controller.dto.CadastroForm;
import br.com.gx.patrimonio.controller.validacao.UsuarioValidacao;
import br.com.gx.patrimonio.modelo.Perfil;
import br.com.gx.patrimonio.modelo.User;
import br.com.gx.patrimonio.repository.PerfilRepository;
import br.com.gx.patrimonio.repository.UserRepository;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private UsuarioValidacao usuarioValidacao;

	@GetMapping
	public ModelAndView signUp(CadastroForm form) {
		return new ModelAndView("signup");
	}

	@PostMapping
	public ModelAndView signUpCadastro(@Valid CadastroForm form, BindingResult result) {

		Perfil userPerfil = perfilRepository.findByNome("ROLE_USER");
		
		User user = form.toUser(form);
		user.adicionarPerfil(userPerfil);

		if (!result.hasErrors() && usuarioValidacao.isEmailUnico(user) && usuarioValidacao.isUsernameUnico(user)) {

			userRepository.save(user);
			
			usuarioValidacao.autenticar(form.getUsername(), form.getPassword());
			
			return new ModelAndView("redirect:/users/home");

		}

		return new ModelAndView("/signup");

	}

}
