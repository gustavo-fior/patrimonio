package br.com.gx.patrimonio.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import br.com.gx.patrimonio.modelo.Acesso;
import br.com.gx.patrimonio.modelo.User;
import br.com.gx.patrimonio.repository.AcessoRepository;
import br.com.gx.patrimonio.repository.UserRepository;

public class InterceptadorDeAcessos implements AsyncHandlerInterceptor {

	private AcessoRepository acessoRepository;

	private UserRepository userRepository;

	public InterceptadorDeAcessos(AcessoRepository acessoRepository, UserRepository userRepository) {
		this.acessoRepository = acessoRepository;
		this.userRepository = userRepository;
	}

	// Antes de comecar o processamento
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<User> user = userRepository.findByUsername(name);

		Acesso acesso = new Acesso();

		acesso.setPath(request.getRequestURI());
		acesso.setData(LocalDateTime.now().minus(3l, ChronoUnit.HOURS));

		if (user.isPresent()) {
			acesso.setUser(user.get());
		}

		request.setAttribute("acesso", acesso);

		return true;
	}

	// Apos a resposta ser completa
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		Acesso acesso = (Acesso) request.getAttribute("acesso");

		acesso.setDuracao(Duration.between(acesso.getData(), LocalDateTime.now()));

		acessoRepository.save(acesso);

	}

}
