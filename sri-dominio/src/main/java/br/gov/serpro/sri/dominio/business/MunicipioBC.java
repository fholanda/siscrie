package br.gov.serpro.sri.dominio.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.serpro.sri.dominio.entity.Municipio;
import br.gov.serpro.sri.dominio.persistence.MunicipioDAO;

@BusinessController
public class MunicipioBC  extends DelegateCrud<Municipio, Long, MunicipioDAO>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5173913986499582065L;		

}
