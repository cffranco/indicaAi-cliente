package br.net.mecsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.mecsoft.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("Select count(c.id) from Cliente c where c.id= :idCli")
	Integer verificaCli(Long idCli);

}
