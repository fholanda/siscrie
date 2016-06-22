package br.gov.serpro.sri.servicos.body;

import java.util.List;

import br.gov.serpro.sri.dominio.entity.Municipio;
import br.gov.serpro.sri.dominio.entity.Retencao;
import br.gov.serpro.sri.dominio.entity.Risco;
import br.gov.serpro.sri.dominio.entity.Tipo;
import br.gov.serpro.sri.dominio.entity.UF;

public class CadDARFBody {

	private List<Retencao> retencaoList;
	private List<UF> ufList;
	private List<Municipio> municipioList;
	private List<Tipo> tipoList;
	private List<Risco> riscoList;
	
	
	public List<Retencao> getRetencaoList() {
		return retencaoList;
	}
	public void setRetencaoList(List<Retencao> retencaoList) {
		this.retencaoList = retencaoList;
	}
	public List<UF> getUfList() {
		return ufList;
	}
	public void setUfList(List<UF> ufList) {
		this.ufList = ufList;
	}
	public List<Municipio> getMunicipioList() {
		return municipioList;
	}
	public void setMunicipioList(List<Municipio> municipioList) {
		this.municipioList = municipioList;
	}
	public List<Tipo> getTipoList() {
		return tipoList;
	}
	public void setTipoList(List<Tipo> tipoList) {
		this.tipoList = tipoList;
	}
	public List<Risco> getRiscoList() {
		return riscoList;
	}
	public void setRiscoList(List<Risco> riscoList) {
		this.riscoList = riscoList;
	}
	
	
	
}
