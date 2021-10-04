package br.com.gx.patrimonio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gx.patrimonio.modelo.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	User findByUsername(String username);
	
}
