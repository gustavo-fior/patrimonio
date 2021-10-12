package br.com.gx.patrimonio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gx.patrimonio.modelo.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{

	Perfil findByNome(String nome);
	
}
