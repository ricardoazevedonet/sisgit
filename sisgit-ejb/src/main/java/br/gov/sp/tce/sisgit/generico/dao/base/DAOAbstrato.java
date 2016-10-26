package br.gov.sp.tce.sisgit.generico.dao.base;

import br.gov.sp.tce.sisgit.generico.modelo.base.EntidadeGenerica;

/**
 * Interface que define métodos em comum para todos os DAOs de entidades do
 * Portal
 * 
 * @author p-lgaona
 * @version 1.0
 * @since 1.0
 * 
 * @param <T>
 */
public interface DAOAbstrato<T extends EntidadeGenerica> extends DAOGenerico<T, Long> {

	
}