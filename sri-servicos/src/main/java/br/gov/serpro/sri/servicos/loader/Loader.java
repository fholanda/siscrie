package br.gov.serpro.sri.servicos.loader;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.sri.dominio.entity.DARF;
import br.gov.serpro.sri.dominio.entity.Municipio;
import br.gov.serpro.sri.dominio.entity.Retencao;
import br.gov.serpro.sri.dominio.entity.Risco;
import br.gov.serpro.sri.dominio.entity.Tipo;
import br.gov.serpro.sri.dominio.entity.UF;
import br.gov.serpro.sri.dominio.persistence.DARFDAO;
import br.gov.serpro.sri.dominio.persistence.MunicipioDAO;
import br.gov.serpro.sri.dominio.persistence.RetencaoDAO;
import br.gov.serpro.sri.dominio.persistence.RiscoDAO;
import br.gov.serpro.sri.dominio.persistence.TipoDAO;
import br.gov.serpro.sri.dominio.persistence.UFDAO;
 
@Transactional
public class Loader {
 
    @Inject
    private RetencaoDAO retencaoDAO;
    
    @Inject
    private DARFDAO darfdao;
    
    @Inject
    private UFDAO ufdao;
    
    @Inject
    private MunicipioDAO municipioDAO;
    
    @Inject
    private TipoDAO tipoDAO;
    
    @Inject
    private RiscoDAO riscoDAO;
 
    //@Startup
    public void load() {
        
        Retencao retencao = new Retencao();
        retencao.setCodigo("6147");
        retencao.setDescricao("Tributos Retidos Órgãos Públicos - Produtos");
        retencaoDAO.insert(retencao);
        
        retencao = new Retencao();
        retencao.setCodigo("0176");
        retencao.setDescricao("ISS - PALMAS");
        retencaoDAO.insert(retencao);
        
        for (int i = 0; i < 20; i++) {
        	DARF darf = new DARF();
            darf.setNrDARF("2016DF0000"+i);
            darf.setCnpj(33683111000107L);
            darf.setNome("Serviço Federal de Processamento de Dados - SERPRO " +i);
            darf.setRetencao(retencao);
            darf.setValor(new BigDecimal("99.99"));
            darf.setDtVencimento(Calendar.getInstance().getTime());
    		darf.setDtApuracao(Calendar.getInstance().getTime());
            
            darfdao.insert(darf);
		}
        
        
        for (int i = 1; i < 400; i++) {
        	UF uf = new UF();
        	uf.setDescricao("UF "+i);
        	ufdao.insert(uf);
        }
        
        for (int i = 1; i < 400; i++) {
        	Municipio municipio = new Municipio();
        	municipio.setDescricao("Municipio "+i);
        	municipioDAO.insert(municipio);
        }
        
        for (int i = 1; i < 400; i++) {
        	Tipo tipo = new Tipo();
        	tipo.setDescricao("Tipo "+i);
        	tipoDAO.insert(tipo);
        }
        
        for (int i = 1; i < 400; i++) {
        	Risco risco = new Risco();
        	risco.setDescricao("Risco "+i);
        	riscoDAO.insert(risco);
        }
        
    }
}
