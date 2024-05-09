package abstracts;

import java.util.List;


import jakarta.persistence.EntityManager;

import jakarta.persistence.criteria.CriteriaQuery;


public abstract class AbstractFacade<T> {
	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	protected abstract EntityManager getEntityManager();
	public void create(T entity) {
		getEntityManager().persist(entity);
	}
	public void update(T entity) {
		getEntityManager().merge(entity);
	}
	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return (List<T>) getEntityManager().createQuery(cq).getResultList();
	}
	public T findOne(Object id) {
	    return getEntityManager().find(entityClass, id);
	}
}