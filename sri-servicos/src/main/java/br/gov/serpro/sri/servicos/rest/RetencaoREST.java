package br.gov.serpro.sri.servicos.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.gov.frameworkdemoiselle.NotFoundException;
import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.serpro.sri.dominio.business.RetencaoBC;
import br.gov.serpro.sri.dominio.entity.Retencao;

@Api(value = "retencao")
@Path("retencao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RetencaoREST {

	@Inject
	private RetencaoBC bc;

	
	@GET
	@LoggedIn
	//@RequiredPermission(resource = "retencao", operation = "consultar")
	@ApiOperation(value = "Busca lista de retenções", response = Retencao.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Parâmetro em desacordo com a especificação.") })
	public List<Retencao> find() throws Exception {
		
		List<Retencao> result;		
		result = bc.findAll();
		
		return result;
	}

	@GET
	@Path("{id}")
	@LoggedIn
	@ApiOperation(value = "Busca retencao pelo id", notes = "É obrigatório informar o parâmetro", response = Retencao.class)	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "É obrigatório informar o parâmetro") })
	public Retencao load(@ApiParam(value = "id da retencao") @PathParam("id") Long id) throws Exception {
		
		Retencao result = bc.load(id);

		if (result == null) {
			throw new NotFoundException();
		}
		
		return result;
	}	
	
}
