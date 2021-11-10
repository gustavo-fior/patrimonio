package br.com.gx.patrimonio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.gx.patrimonio.interceptor.InterceptadorDeAcessos;
import br.com.gx.patrimonio.repository.AcessoRepository;
import br.com.gx.patrimonio.repository.UserRepository;

@Configuration
@EnableWebMvc
public class WebMcvConfig implements WebMvcConfigurer {

	@Autowired
	private AcessoRepository acessoRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new InterceptadorDeAcessos(acessoRepository, userRepository)).addPathPatterns("/**");
	}
	
	// Para acessar recursos estaticos
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.
		addResourceHandler("resources/**")
		.addResourceLocations("/public", "classpath:/static/")
		.setCachePeriod(31556926);
		
	}

}
