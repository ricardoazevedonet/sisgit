package br.gov.sp.tce.sisgit.generico.modelo.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Superclasse abstrata que define toda entidade persistível pela JPA
 * 
 * @author p-mvicente
 * @version 1.0
 * 
 */
@MappedSuperclass
@SuppressWarnings("serial") //serialVersionUID deve ser definido em cada classe
public abstract class EntidadeGenerica extends DadosComplementares implements IEntidadeGenerica<Long> {

	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/*
	 * Esta implementação de equals invoca o getter da propriedade id ao invés de acessá-la diretamente, pois a instância utilizada no contexto do Sipnet é um proxy dinâmico
	 * gerado pelo Hibernate, e para recuperar o id com lazy loading é necessário invocar o getter()
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof EntidadeGenerica)) {
			return false;
		}
		EntidadeGenerica other = (EntidadeGenerica) o;
		if (this.getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!this.getId().equals(other.getId())) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		return result;
	}

	/*@Override
	public String toString() {
		return "EntidadeAbstrata [id=" + id + "]";
	}	*/
	
}