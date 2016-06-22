package br.gov.serpro.sri.dominio.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.serpro.sri.dominio.entity.Tipo;
import br.gov.serpro.sri.dominio.persistence.TipoDAO;

@BusinessController
public class TipoBC  extends DelegateCrud<Tipo, Long, TipoDAO>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5173913986499582065L;		

}
