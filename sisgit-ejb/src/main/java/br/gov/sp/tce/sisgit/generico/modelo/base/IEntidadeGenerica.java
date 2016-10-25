package br.gov.sp.tce.sisgit.generico.modelo.base;

import java.io.Serializable;

/**
 * Estutura básica para uma Entidade gerenciada no contexto do JPA<br/>
 * A chave primária (PK) é genérica, permitindo a utilização de chaves compostas 
 * 
 * @author dvivencio
 *
 * @param <PK>
 */
public interface IEntidadeGenerica <PK extends Serializable> extends Serializable {
	
	public PK getId();
	
	public void setId(PK pk);
}
