package br.com.gx.patrimonio.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.gx.patrimonio.modelo.User;

public class CadastroForm {

	@NotBlank
	private String username;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public User toUser(CadastroForm form) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		
		User user = new User();
		user.setEmail(this.email);
		user.setUsername(this.username);
		user.setPassword(encoder.encode(this.password));
		
		return user;
		
	}

}
