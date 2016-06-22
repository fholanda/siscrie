package br.gov.serpro.sri.servicos.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.gov.frameworkdemoiselle.BadRequestException;
import br.gov.frameworkdemoiselle.NotFoundException;
import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ValidatePayload;
import br.gov.serpro.sri.dominio.business.RiscoBC;
import br.gov.serpro.sri.dominio.entity.Risco;

@Api(value = "risco")
@Path("risco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RiscoREST {

	@Inject
	private RiscoBC bc;

	
	@GET
	@LoggedIn
	@ApiOperation(value = "Busca lista de riscos", response = Risco.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Parâmetro em desacordo com a especificação.") })
	public List<Risco> find() throws Exception {
		
		List<Risco> result;		
		result = bc.findAll();
		
		return result;
	}

	@GET
	@Path("{id}")
	@LoggedIn
	@ApiOperation(value = "Busca risco pelo id", notes = "É obrigatório informar o parâmetro", response = Risco.class)	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "É obrigatório informar o parâmetro") })
	public Risco load(@ApiParam(value = "id do tipo") @PathParam("id") Long id) throws Exception {
		
		Risco result = bc.load(id);

		if (result == null) {
			throw new NotFoundException();
		}
		
		return result;
	}
	
	@POST
	@LoggedIn
	@Transactional
	@ValidatePayload
	@ApiOperation(value = "Insere risco", response = Response.class)	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Erro nos parâmetros informados") })
	public Response insert(Risco risco, @Context UriInfo uriInfo) throws Throwable {

		checkId(risco);
		
		Risco riscoNovo = bc.insert(risco);
		URI location = uriInfo.getRequestUriBuilder().path(riscoNovo.getId().toString()).build();
	
		return Response.created(location).entity(riscoNovo).build();
	}
	
	private void checkId(Risco entity) throws Exception {
		if (entity.getId() != null) {
			throw new BadRequestException();
		}
	}
	
}
