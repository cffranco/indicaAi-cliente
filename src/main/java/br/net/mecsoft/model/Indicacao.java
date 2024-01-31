package br.net.mecsoft.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Indicacao {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long idCliente;
	
	private Long idProfissional;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdProfissional() {
		return idProfissional;
	}

	public void setIdProfissional(Long idProfissional) {
		this.idProfissional = idProfissional;
	}

	public Indicacao() {}
	
	public Indicacao(Long id, Long idCliente, Long idProfissional) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.idProfissional = idProfissional;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idCliente, idProfissional);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Indicacao other = (Indicacao) obj;
		return Objects.equals(id, other.id) && Objects.equals(idCliente, other.idCliente)
				&& Objects.equals(idProfissional, other.idProfissional);
	}
	
	
	
	
}
