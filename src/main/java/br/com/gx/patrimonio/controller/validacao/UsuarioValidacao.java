package br.com.gx.patrimonio.controller.validacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.gx.patrimonio.modelo.User;
import br.com.gx.patrimonio.repository.UserRepository;

@Service
public class UsuarioValidacao {
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserRepository userRepository;

	public boolean isEmailUnico(User user) {

		Optional<User> userEmail = userRepository.findByEmail(user.getEmail());

		if (userEmail.isPresent()) {
			return false;
		}

		return true;

	}

	public boolean isUsernameUnico(User user) {

		Optional<User> userUsername = userRepository.findByUsername(user.getUsername());

		if (userUsername.isPresent()) {
			return false;
		}

		return true;

	}

	public void autenticar(String username, String password) {

		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);
		Authentication auth = authManager.authenticate(authReq);
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);
		
	}

}
