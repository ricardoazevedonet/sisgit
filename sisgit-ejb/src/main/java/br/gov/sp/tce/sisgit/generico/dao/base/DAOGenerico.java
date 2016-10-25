package br.gov.sp.tce.sisgit.generico.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.gov.sp.tce.sisgit.generico.modelo.base.IEntidadeGenerica;

public interface DAOGenerico<T extends IEntidadeGenerica<PK>, PK extends Serializable>{
	
	/**
	 * Realiza lookup de uma entidade pela propriedade <code>id</code>
	 * 
	 * @param id
	 * @return {@link EntidadeAbstrata}, ou <code>null</code> se não for
	 *         encontrado
	 */
	T lookup(PK id);

	/**
	 * Persiste uma entidade na base de dados
	 * 
	 * @param instancia
	 */
	void save(T instancia);
	
	/**
	 * Persiste uma entidade na base de dados em cascata
	 * 
	 * @param instancia
	 */
	T saveCascaded(T instancia);

	/**
	 * Atualiza uma entidade na base de dados
	 * 
	 * @param instancia
	 */
	T update(T instancia);

	/**
	 * Atualiza uma Lista de entidade na base de dados
	 * 
	 * @param Lista instancia
	 */
	void updateBatch(List<T> instancia);

	/**
	 * Remove uma entidade da base de dados
	 * 
	 * @param instancia
	 */
	void delete(T instancia);

	/**
	 * Remove uma entidade da base de dados pela propriedade <code>id</code>.
	 * Uma chamada ao método {@link DAOAbstratoImpl#lookup(Long)} é realizada
	 * antes de se invocar {@link EntityManager#remove(Object)}
	 * 
	 * @param id
	 */
	void deleteById(PK id);

	/**
	 * Recupera todas as instancias de uma determinada entidade
	 * 
	 * @return {@link List}
	 */
	List<T> getAll();

	/**
	 * Recupera a(s) instância(s) de uma determinada entidade de acordo com a
	 * {@link NamedQuery} e seus respectivos parâmetros.
	 * 
	 * @param name
	 *            nome da {@link NamedQuery}
	 * @param params
	 *            Parâmetros da query.
	 * 
	 * @return Lista com o resultado da query.
	 */
	List<T> findByNamedQuery(final String name,
			Param... params);
	
	/**
	 * Recupera a(s) instância(s) de uma determinada entidade de acordo com a
	 * {@link NamedQuery} e seus respectivos parâmetros e com um número máximo de resultados.
	 * 
	 * @param name
	 *            nome da {@link NamedQuery}
	 * @param params
	 *            Parâmetros da query.
	 * @param maxResult
	 *            Parâmetro para o máximo de resultados.
	 * 
	 * @return Lista com o resultado da query.
	 */
	List<T> findByNamedQuery(final String name, int maxResult, Param... params);
	
	/**
	 * Recupera a(s) instância(s) de uma determinada entidade de acordo com a
	 * {@link Query (HQL)} e seus respectivos parâmetros.
	 * 
	 * @param name
	 *            nome da {@link Quey}
	 * @param params
	 *            Parâmetros da query.
	 * 
	 * @return Lista com o resultado da query.
	 */
	List<T> findByQueryTyped(final String name, final Param... params);
	
	/**
	 * Recupera uma Lista de ? de acorco com a 
	 * {@link Query Nativa} e seus respectivos parâmetros.
	 * 
	 * @param name
	 *            nome da {@link Quey}
	 * @param params
	 *            Parâmetros da query.
	 * 
	 * @return Lista com o resultado da query.
	 */
	List<?> findByNativeQuery(final String nativeQuery, final Param... params);
	
	/**
	 * Recupera uma Lista de ? de acorco com a 
	 * {@link NamedQuery} e seus respectivos parâmetros.
	 * 
	 * @param name
	 *            nome da {@link Quey}
	 * @param params
	 *            Parâmetros da query.
	 * 
	 * @return Lista com o resultado da query.
	 */
	List<?> findByNamedQueryNotTyped(final String name, final Param... params);

	/**
	 * Recupera a(s) instância(s) de uma determinada entidade de acordo com a
	 * criteria
	 * 
	 * @return {@link List}
	 */
	List<T> findByCriteria(CriteriaQuery<T> criteriaQuery);

	/**
	 * Recupera a instância de uma determinada entidade de acordo com a
	 * namedQuery
	 * 
	 * @return {@link List}
	 */
	T findByOneNamedQuery(final String name, final Param... params);
	
	List<T> findNamedQueryByRange(final String name, int startPosition, int maxResult);
	
	int executeUpdateByNamedQuery(String string, List<Entry<String, Object>> params);
	
	List<?> findByNamedQueryNotTyped(String name, List<Entry<String, Object>> params);

	int deleteAll();

	List<T> findByQueryTyped(String name, List<Entry<String, Object>> params, int maxResult);
	

	List<T> findByQueryTyped(String name, List<Entry<String, Object>> params);

	List<T> findByQueryTyped(String name, List<Entry<String, Object>> params, int pageSize, int first);

	Number countRegister(String name, List<Entry<String, Object>> params);

	List<T> findByQueryTypedScrollableResults(String name, List<Entry<String, Object>> params, int quantidadeMaximaResultados, int indicePor);

	int deleteByQuery(String name, Param[] params);

	int deleteByNativeQuery(String name, Param[] params);

	Object getDelegate();

		

}