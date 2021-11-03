package br.com.gx.patrimonio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gx.patrimonio.modelo.Imovel;
import br.com.gx.patrimonio.modelo.StatusImovel;
import br.com.gx.patrimonio.modelo.User;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long>{

	@Query("SELECT i FROM Imovel i WHERE i.user.username = :username")
	List<Imovel> findByUsername(String username);
	
	List<Imovel> findByUserOrderByAluguelDesc(User user);
	
	List<Imovel> findByStatus(StatusImovel status);
	
}
