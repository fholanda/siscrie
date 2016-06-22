package br.gov.serpro.sri.dominio.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.serpro.sri.dominio.entity.DARF;

public class DARFDAO extends JPACrud<DARF, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4027119409298291170L;
	
	@Inject
    private EntityManager em;
	
	public List<DARF> find(String filter) {
	    StringBuffer jpql = new StringBuffer();
	    jpql.append(" select d ");
	    jpql.append("   from DARF d ");
	    jpql.append("  where lower(d.nrDARF) like :filter ");
	    jpql.append("  order by ");
	    jpql.append("        d.nrDARF asc ");
	 
	    TypedQuery<DARF> query = em.createQuery(jpql.toString(), DARF.class);
	    query.setParameter("filter", "%" + (filter == null ? "" : filter.toLowerCase()) + "%");
	 
	    return query.getResultList();
	}
	
	
	@SuppressWarnings("rawtypes")
	public List list(String field, String order, int init, int qtde) {
        return getEntityManager().createQuery("select u from " + this.getBeanClass().getSimpleName() + " u ORDER BY " + field + " " + order).setFirstResult(init).setMaxResults(qtde).getResultList();
    }
	
	public Long count() {
        return (Long) getEntityManager().createQuery("select COUNT(u) from " + this.getBeanClass().getSimpleName() + " u").getSingleResult();
    }

}
