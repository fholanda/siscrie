package br.gov.serpro.sri.dominio.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.serpro.sri.dominio.entity.DARF;
import br.gov.serpro.sri.dominio.persistence.DARFDAO;

@BusinessController
public class DARFBC  extends DelegateCrud<DARF, Long, DARFDAO>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5173913986499582065L;

	@Inject
	DARFDAO dao;
	
	@Override
	public DARF insert(DARF bean) {		
		
		DARF darf = super.insert(bean);
		return darf;
	}
	
	public List<DARF> find(String filtro){
		
		return dao.find(filtro);
		
	}
	
	@SuppressWarnings("rawtypes")
	public List list(String field, String order, int init, int qtde) {
        return getDelegate().list(field, order, init, qtde);
    }
	
	public Long count() {
        return getDelegate().count();
    }

}
