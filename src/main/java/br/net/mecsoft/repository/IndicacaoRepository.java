package br.net.mecsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.mecsoft.model.Indicacao;

public interface IndicacaoRepository extends JpaRepository<Indicacao, Long> {
	
	@Query("Select i from Indicacao i where i.idCliente= :idCli and i.idProfissional= :idProf")
	Indicacao verifica(Long idCli, Long idProf);


}
