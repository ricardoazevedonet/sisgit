package br.gov.sp.tce.sisgit.generico.dao.base;


import br.gov.sp.tce.sisgit.generico.modelo.base.EntidadeGenerica;

/**
 * Supertype para todos os DAOs do sistema, com métodos de utilidade já implementados
 * 
 * @author p-lgaona
 * @version 1.0
 * @since 1.0
 * 
 */
public abstract class DAOAbstratoImpl<T extends EntidadeGenerica>
	extends DAOGenericoImpl<T, Long> implements DAOAbstrato<T> {
	
	
}