package br.gov.serpro.sri.dominio.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.serpro.sri.dominio.entity.Retencao;
import br.gov.serpro.sri.dominio.persistence.RetencaoDAO;

@BusinessController
public class RetencaoBC  extends DelegateCrud<Retencao, Long, RetencaoDAO>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5173913986499582065L;		

}
