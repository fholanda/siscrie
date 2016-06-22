package br.gov.serpro.sri.dominio.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.serpro.sri.dominio.entity.UF;
import br.gov.serpro.sri.dominio.persistence.UFDAO;

@BusinessController
public class UFBC  extends DelegateCrud<UF, Long, UFDAO>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5173913986499582065L;		

}
