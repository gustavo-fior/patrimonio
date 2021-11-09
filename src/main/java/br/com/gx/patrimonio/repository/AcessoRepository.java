package br.com.gx.patrimonio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gx.patrimonio.modelo.Acesso;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

}
