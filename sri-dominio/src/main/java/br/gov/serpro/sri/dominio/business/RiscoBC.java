package br.gov.serpro.sri.dominio.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.serpro.sri.dominio.entity.Risco;
import br.gov.serpro.sri.dominio.persistence.RiscoDAO;

@BusinessController
public class RiscoBC  extends DelegateCrud<Risco, Long, RiscoDAO>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5173913986499582065L;		

}
