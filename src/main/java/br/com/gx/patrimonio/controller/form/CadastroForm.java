package br.com.gx.patrimonio.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.gx.patrimonio.modelo.User;

public class CadastroForm {

	@NotBlank(message = "Campo obrigatório")
	@Size(min = 4, max = 20, message = "O nome de usuário deve ter no mínimo 4 e no máximo 20 caractéres")
	private String username;
	
	
	@NotBlank(message = "Campo obrigatório")
	@Email(message = "Siga o padrão joao@email.com")
	private String email;
	
	@NotNull
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caractéres")
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
		user.setEnabled(true);
		
		return user;
		
	}

}
