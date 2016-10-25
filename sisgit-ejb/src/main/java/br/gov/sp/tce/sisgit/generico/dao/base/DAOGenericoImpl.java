package br.gov.sp.tce.sisgit.generico.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import br.gov.sp.tce.sisgit.generico.exception.EntityNotFoundException;
import br.gov.sp.tce.sisgit.generico.modelo.base.IEntidadeGenerica;





public class DAOGenericoImpl<T extends IEntidadeGenerica<PK>, PK extends Serializable> implements DAOGenerico<T, PK> {
	
	private final Class<T> type;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public DAOGenericoImpl() {
		final Type t = this.getClass().getGenericSuperclass();
		final ParameterizedType parameterizedType = (ParameterizedType) t;
		this.type = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public T lookup(PK id) {
		return entityManager.find(type, id);
	}

	@Override	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(T instancia) {
		entityManager.persist(instancia);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public T saveCascaded(T instancia) {		
		return entityManager.merge(instancia);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public T update(T instancia) {
		return entityManager.merge(instancia);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateBatch(List<T> instancia) {
		for (T object : instancia) {
			this.update(object);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(T instancia) {
		entityManager.remove(this.lookup(instancia.getId()));
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteById(PK id) {
		entityManager.remove(this.lookup(id));
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int deleteAll() {
		List<T> lista = entityManager.createQuery(getAllQuery(), type).getResultList();
		for (T t : lista) {
			entityManager.remove(t);
		}
		return lista.size();
	}

	@Override
	//@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> getAll() {
		return entityManager.createQuery(getAllQuery(), type).getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private String getAllQuery() {
		final StringBuilder sb = new StringBuilder();
		sb.append("FROM ");
		sb.append(type.getSimpleName());
		sb.append(" ORDER BY id");
		return sb.toString();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByNamedQuery(final String name, final Param... params) {
		final TypedQuery<T> query = entityManager.createNamedQuery(name, type);
		setParams(query, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByQueryTyped(final String name, final Param... params) {
		final Query query = entityManager.createQuery(name, type);
		setParams(query, params);
		return query.getResultList();
	}
	

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int deleteByQuery(final String name, final Param... params) {
		final Query query = entityManager.createQuery(name);
		setParams(query, params);
		return query.executeUpdate();
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int deleteByNativeQuery(final String name, final Param... params) {
		final Query query = entityManager.createNativeQuery(name);
		setParams(query, params);
		return query.executeUpdate();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<?> findByNativeQuery(final String nativeQuery, final Param... params) {
		final Query query = entityManager.createNativeQuery(nativeQuery);
		setParams(query, params);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<?> findByNamedQueryNotTyped(final String name, final Param... params) {
		final Query query = entityManager.createNamedQuery(name);
		setParams(query, params);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByNamedQuery(final String name, int maxResult, Param... params) {
		final TypedQuery<T> query = entityManager.createNamedQuery(name, type);
		setParams(query, params);
		query.setMaxResults(maxResult);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public T findByOneNamedQuery(final String name, final Param... params) throws NonUniqueResultException {
		try {
			final TypedQuery<T> query = entityManager.createNamedQuery(name, type);
			setParams(query, params);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			throw new EntityNotFoundException("Registro do tipo " + type.getSimpleName() + " não encontrado.", ex);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findNamedQueryByRange(final String name, int startPosition, int maxResult) {
		return entityManager.createNamedQuery(name, type).setFirstResult(startPosition).setMaxResults(maxResult).getResultList();
	}

	private void setParams(final Query query, final Param... params) {
		for (final Param param : params) {
			query.setParameter(param.chave, param.valor);
		}
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByCriteria(CriteriaQuery<T> criteriaQuery) {
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}

	protected CriteriaQuery<T> createCriteriaQuery() {
		return getCriteriaBuilder().createQuery(type);
	}

	protected EntityType<T> getMetaModelEntityType() {
		Metamodel metaModel = entityManager.getMetamodel();
		return metaModel.entity(type);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<?> findByNamedQueryNotTyped(final String name, List<Map.Entry<String, Object>> params) {
		Query query = entityManager.createNamedQuery(name);
		for (Map.Entry<String, Object> param : params) {
			query.setParameter(param.getKey(), param.getValue());
		}
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int executeUpdateByNamedQuery(String name, List<Entry<String, Object>> params) {
		Query query = entityManager.createNamedQuery(name);

		if (params != null) {
			for (Map.Entry<String, Object> param : params) {
				query.setParameter(param.getKey(), param.getValue());
			}
		}

		return query.executeUpdate();
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByQueryTyped(final String name, List<Map.Entry<String, Object>> params, int maxResult) {
		TypedQuery<T> query = entityManager.createQuery(name, type);
		query.setMaxResults(maxResult);
		for (Map.Entry<String, Object> param : params) {
			query.setParameter(param.getKey(), param.getValue());
		}
		return query.getResultList();
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByQueryTyped(final String name, List<Map.Entry<String, Object>> params) {
		TypedQuery<T> query = entityManager.createQuery(name, type);
		
		for (Map.Entry<String, Object> param : params) {
			query.setParameter(param.getKey(), param.getValue());
		}
		return query.getResultList();
	}
		
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Number countRegister(final String name, List<Map.Entry<String, Object>> params) {
		
		Query query = entityManager.createQuery(name);
		
		for (Map.Entry<String, Object> param : params) {
			query.setParameter(param.getKey(), param.getValue());
		}

		Number result = (Number) query.getSingleResult();
		
		return result;
	}	
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByQueryTyped(final String name, List<Map.Entry<String, Object>> params, int quantidadeMaximaResultados, int indicePor) {
		TypedQuery<T> query = entityManager.createQuery(name, type);
		if (quantidadeMaximaResultados >= 0){
			query.setMaxResults(quantidadeMaximaResultados);
        }
        if (indicePor >= 0){
        	query.setFirstResult(indicePor);
        }
		for (Map.Entry<String, Object> param : params) {
			query.setParameter(param.getKey(), param.getValue());
		}
		return query.getResultList();
	}
	
	
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByQueryTypedScrollableResults(final String name, List<Map.Entry<String, Object>> params, int quantidadeMaximaResultados, int indicePor) {
		
		Session session = (Session) entityManager.getDelegate();
		
		org.hibernate.query.Query query = session.createQuery(name);

		query.setFirstResult(indicePor);
		query.setMaxResults(quantidadeMaximaResultados);
		
		for (Map.Entry<String, Object> param : params) {
			query.setParameter(param.getKey(), param.getValue());
		}

		ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
		
		List<T> listaRet = new ArrayList<T>();
		
		try {
			int contadorDeResultados = 1;
			
			// OBSERVAÇÃO: A paginação de 40.000 estava alocando muito memoria,
			// depois que baixei para 3.000 o memory leak deixou de acontecer.
			// Verificar se foi por causa da paginação ou se for por cauxa do
			// clear de x em x.
			
			while (results.next()) {
				
				if ((contadorDeResultados % quantidadeMaximaResultados) == 0) {
					// OBSERVAÇÃO: A utilização do código abaixo faz com que os
					// x obtjetos atachados sejam desatachados e prontos
					// para serem coletados imediatamente.
					session.clear();
				}
				
				listaRet.add((T) results.get(0));
				contadorDeResultados++;
			}
			
		} finally {
			if (!results.next()){
				results.close();
			}
			
			session.clear();
		}
		
		results = null;		
		
		return listaRet;
	}
	
	@Override
	public Object getDelegate(){	
		return entityManager.getDelegate();		
	}

}