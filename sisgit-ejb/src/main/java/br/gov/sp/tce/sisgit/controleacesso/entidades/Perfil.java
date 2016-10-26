package br.gov.sp.tce.sisgit.controleacesso.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.gov.sp.tce.sisgit.generico.modelo.base.EntidadeGenerica;


@Entity
@Table(name="perfil")
@NamedQueries({	
	@NamedQuery(name = "perfil.obterPefilPorNome", query = "SELECT u FROM Perfil u WHERE lower(u.nome) = lower(:nome)"),
})
public class Perfil extends EntidadeGenerica implements Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3410103889805629622L;
	
	@Column(nullable = false, length = 100)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj ==null)
			return false;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
