package br.gov.serpro.sri.servicos.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import br.gov.frameworkdemoiselle.BadRequestException;
import br.gov.frameworkdemoiselle.NotFoundException;
import br.gov.frameworkdemoiselle.UnauthorizedException;
import br.gov.frameworkdemoiselle.security.LoggedIn;
import br.gov.frameworkdemoiselle.security.RequiredPermission;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ValidatePayload;
import br.gov.serpro.guia.security.AutentikusToken;
import br.gov.serpro.sri.dominio.business.DARFBC;
import br.gov.serpro.sri.dominio.business.RetencaoBC;
import br.gov.serpro.sri.dominio.business.RiscoBC;
import br.gov.serpro.sri.dominio.entity.DARF;
import br.gov.serpro.sri.dominio.entity.Municipio;
import br.gov.serpro.sri.dominio.entity.Retencao;
import br.gov.serpro.sri.dominio.entity.Risco;
import br.gov.serpro.sri.dominio.entity.Tipo;
import br.gov.serpro.sri.dominio.entity.UF;
import br.gov.serpro.sri.servicos.body.CadDARFBody;
import br.gov.serpro.sri.servicos.util.Util;

@Api(value = "darfs")
@Path("darfs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DARFREST {

	@Inject
	private DARFBC bc;
	
	@Inject
	private RetencaoBC retencaoBC;
	
	@Inject
	private RiscoBC riscoBC;
	
	
	@GET
	@LoggedIn
    @ApiOperation(value = "Lista com paginação no servidor", response = Response.class, notes="Se não informar os parâmetros, todos os registros são retornados.")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Operacação realizada com sucesso", response = Response.class),
						   @ApiResponse(code = 400, message = "Parâmetros inválidos", response = Response.class)})
    public Response list(@ApiParam(name="sort", value="Representa o campo/ordem(asc/desc). O prefixo \"-\" indica ordem inversa", example="http://localhost:8080/sri-servicos/darfs?sort=-name&offset=30&limit=10") @QueryParam("sort") String sort, 
    					 @ApiParam(name="offset", value="Representa a posição do primeiro registro") @QueryParam("offset") int offset, 
    					 @ApiParam(name="limit", value="Representa a quantidade de registros (tamanho da página)") @QueryParam("limit") int limit) throws NotFoundException 
	{
		
        if ((offset > -1) && (limit > 0) && !sort.isEmpty() && (sort.startsWith("-") ? Util.fieldInClass(sort.substring(1), DARF.class) : Util.fieldInClass(sort, DARF.class))) {
        	
        	String order = sort.startsWith("-") ? "desc" : "asc";
        	
            return Response.ok().entity(bc.list(sort.startsWith("-") ? sort.substring(1) : sort, order, offset, limit)).build();
        }
        else if ((sort == null || sort.isEmpty()) && offset == 0 && limit == 0){ // se não informar os parâmetros, retorna todos os registros
        	return Response.ok().entity(bc.findAll()).build();
        }
        else{
        	return Response.status(Status.BAD_REQUEST).entity(null).build();
        }

        	
        	
    }
	
	@Path("/rnf/c1-c2")
	@GET
    @ApiOperation(value = "Cenários 1 e 2 para testes de RNF. Lista com paginação no servidor", response = Response.class, notes="Se não informar os parâmetros, todos os registros são retornados.")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Operacação realizada com sucesso", response = Response.class),
						   @ApiResponse(code = 400, message = "Parâmetros inválidos", response = Response.class)})
    public Response list2(@ApiParam(name="sort", value="Representa o campo/ordem(asc/desc). O prefixo \"-\" indica ordem inversa", example="http://localhost:8080/sri-servicos/darfs?sort=-name&offset=30&limit=10") @QueryParam("sort") String sort, 
    					 @ApiParam(name="offset", value="Representa a posição do primeiro registro") @QueryParam("offset") int offset, 
    					 @ApiParam(name="limit", value="Representa a quantidade de registros (tamanho da página)") @QueryParam("limit") int limit) throws NotFoundException 
	{
		
        if ((offset > -1) && (limit > 0) && !sort.isEmpty() && (sort.startsWith("-") ? Util.fieldInClass(sort.substring(1), DARF.class) : Util.fieldInClass(sort, DARF.class))) {
        	
        	String order = sort.startsWith("-") ? "desc" : "asc";
        	
            return Response.ok().entity(bc.list(sort.startsWith("-") ? sort.substring(1) : sort, order, offset, limit)).build();
        }
        else if ((sort == null || sort.isEmpty()) && offset == 0 && limit == 0){ // se não informar os parâmetros, retorna todos os registros
        	return Response.ok().entity(bc.findAll()).build();
        }
        else{
        	return Response.status(Status.BAD_REQUEST).entity(null).build();
        }

        	
        	
    }
	
	@Path("/rnf/c3")
	@GET
    @LoggedIn
    @ApiOperation(value = "Cenários 3 para testes de RNF. Lista com paginação no servidor", response = Response.class, notes="Se não informar os parâmetros, todos os registros são retornados.")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Operacação realizada com sucesso", response = Response.class),
						   @ApiResponse(code = 400, message = "Parâmetros inválidos", response = Response.class)})
    public Response list3(@ApiParam(name="sort", value="Representa o campo/ordem(asc/desc). O prefixo \"-\" indica ordem inversa", example="http://localhost:8080/sri-servicos/darfs?sort=-name&offset=30&limit=10") @QueryParam("sort") String sort, 
    					 @ApiParam(name="offset", value="Representa a posição do primeiro registro") @QueryParam("offset") int offset, 
    					 @ApiParam(name="limit", value="Representa a quantidade de registros (tamanho da página)") @QueryParam("limit") int limit) throws NotFoundException 
	{
		
        if ((offset > -1) && (limit > 0) && !sort.isEmpty() && (sort.startsWith("-") ? Util.fieldInClass(sort.substring(1), DARF.class) : Util.fieldInClass(sort, DARF.class))) {
        	
        	String order = sort.startsWith("-") ? "desc" : "asc";
        	
            return Response.ok().entity(bc.list(sort.startsWith("-") ? sort.substring(1) : sort, order, offset, limit)).build();
        }
        else if ((sort == null || sort.isEmpty()) && offset == 0 && limit == 0){ // se não informar os parâmetros, retorna todos os registros
        	return Response.ok().entity(bc.findAll()).build();
        }
        else{
        	return Response.status(Status.BAD_REQUEST).entity(null).build();
        }

        	
        	
    }
	
	@Path("/rnf/c4-c5-c6-c7")
	@GET
    @LoggedIn
    @RequiredPermission(resource="darf", operation="consultar")
    @ApiOperation(value = "Cenários 4, 5, 6 e 7 para testes de RNF. Lista com paginação no servidor", response = Response.class, notes="Se não informar os parâmetros, todos os registros são retornados.")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Operacação realizada com sucesso", response = Response.class),
						   @ApiResponse(code = 400, message = "Parâmetros inválidos", response = Response.class)})
    public Response list4(@ApiParam(name="sort", value="Representa o campo/ordem(asc/desc). O prefixo \"-\" indica ordem inversa", example="http://localhost:8080/sri-servicos/darfs?sort=-name&offset=30&limit=10") @QueryParam("sort") String sort, 
    					 @ApiParam(name="offset", value="Representa a posição do primeiro registro") @QueryParam("offset") int offset, 
    					 @ApiParam(name="limit", value="Representa a quantidade de registros (tamanho da página)") @QueryParam("limit") int limit) throws NotFoundException 
	{
		
        if ((offset > -1) && (limit > 0) && !sort.isEmpty() && (sort.startsWith("-") ? Util.fieldInClass(sort.substring(1), DARF.class) : Util.fieldInClass(sort, DARF.class))) {
        	
        	String order = sort.startsWith("-") ? "desc" : "asc";
        	
            return Response.ok().entity(bc.list(sort.startsWith("-") ? sort.substring(1) : sort, order, offset, limit)).build();
        }
        else if ((sort == null || sort.isEmpty()) && offset == 0 && limit == 0){ // se não informar os parâmetros, retorna todos os registros
        	return Response.ok().entity(bc.findAll()).build();
        }
        else{
        	return Response.status(Status.BAD_REQUEST).entity(null).build();
        }

        	
        	
    }
	
	@GET
    @Path("count")
    @LoggedIn
    @ApiOperation(value = "Quantidade de registro",
                  notes = "Usado para trabalhar as tabelas com paginação no servidor",
                  response = Response.class
    )
    public Response count() throws NotFoundException {
        return Response.ok().entity(bc.count()).build();
    }
	
	@GET
	@Path("/{id}")
	@LoggedIn
	@ApiOperation(value = "Busca darf pelo id", notes = "É obrigatório informar o parâmetro", response = Response.class)	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "É obrigatório informar o parâmetro") })
	public Response load(@ApiParam(value = "id do darf") @PathParam("id") Long id) throws Exception {
		DARF result = bc.load(id);

		if (result == null) {
			throw new NotFoundException();
		}
		
		return Response.ok().entity(result).build();
		
	}
	
	@GET
	@Path("/rnf/c7-composicao")
	@Transactional
	@LoggedIn
	@RequiredPermission(resource="darf", operation="consultar")
	@ApiOperation(value = "Teste de composição de serviços. Retorna um objeto contedo 5 listas para montagem de combos na tela de cadastro", response = Response.class)	
	public Response listComposto(@Context UriInfo uriInfo) throws Exception {

		CadDARFBody cadDARFBody = new CadDARFBody();
		
		Properties properties = new Properties();
		properties.load(DARFREST.class.getClassLoader().getResourceAsStream("sri-servicos.properties"));
		
		boolean backendDominioUnico = properties.getProperty("backend_dominio_unico").equalsIgnoreCase("S");
		
		// URLs dos serviços
	    URL urlRetencao = new URL(properties.getProperty("urlRetencao"));
	    URL urlUF = new URL(properties.getProperty("urlUF"));
	    URL urlMunicipio = new URL(properties.getProperty("urlMunicipio"));
	    URL urlTipo = new URL(properties.getProperty("urlTipo"));
	    URL urlRisco = new URL(properties.getProperty("urlRisco"));
		
		AutentikusToken token = Beans.getReference(AutentikusToken.class);
	    ObjectMapper mapper = new ObjectMapper();
	    
		// consulta os serviços para obter as listas		
	    
	    // RETENCAO
		HttpURLConnection connectionRetencao = (HttpURLConnection) urlRetencao.openConnection();
		connectionRetencao.setRequestMethod("GET");
		connectionRetencao.setRequestProperty("Content-Type", "application/json");
		
		if (token.getCookie() != null) {
			connectionRetencao.setRequestProperty("Cookie", token.getCookie());
			
			// necessário para teste fora do domínio único
			if (!backendDominioUnico){
				connectionRetencao.setRequestProperty("Authorization", "Token Teste");
				connectionRetencao.setRequestProperty("OAM_SERPRO_CPF", token.getCpf());
				connectionRetencao.setRequestProperty("OAM_IDENTITY_ASSERTION", token.getSaml());
			}
			
		}
		
		InputStream isRetencao = connectionRetencao.getInputStream();
		
		switch (connectionRetencao.getResponseCode()) {
		case HttpURLConnection.HTTP_UNAUTHORIZED:			
			throw new UnauthorizedException();
		
		case HttpURLConnection.HTTP_BAD_REQUEST:			
			throw new BadRequestException();
			
		default:			
			break;
		}	
		
		BufferedReader br = new BufferedReader(new InputStreamReader((isRetencao)));

		String responseBody = "";
		String output;
		while ((output = br.readLine()) != null) {
			responseBody += output;
		}

		connectionRetencao.disconnect();		
		cadDARFBody.setRetencaoList((List<Retencao>)mapper.readValue(responseBody, new TypeReference<List<Retencao>>() {}));
		
	    // UF
		HttpURLConnection connectionUF = (HttpURLConnection) urlUF.openConnection();
		connectionUF.setRequestMethod("GET");
		connectionUF.setRequestProperty("Content-Type", "application/json");
		
		if (token.getCookie() != null) {
			connectionUF.setRequestProperty("Cookie", token.getCookie());
			
			// necessário para teste fora do domínio único
			if (!backendDominioUnico){
				connectionUF.setRequestProperty("Authorization", "Token Teste");
				connectionUF.setRequestProperty("OAM_SERPRO_CPF", token.getCpf());
				connectionUF.setRequestProperty("OAM_IDENTITY_ASSERTION", token.getSaml());
			}
			
		}
		
		InputStream isUF = connectionUF.getInputStream();
		
		switch (connectionUF.getResponseCode()) {
		case HttpURLConnection.HTTP_UNAUTHORIZED:			
			throw new UnauthorizedException();
		
		case HttpURLConnection.HTTP_BAD_REQUEST:			
			throw new BadRequestException();
			
		default:			
			break;
		}		
		
		BufferedReader brUF = new BufferedReader(new InputStreamReader((isUF)));

		String responseBodyUF = "";
		String outputUF;
		while ((outputUF = brUF.readLine()) != null) {
			responseBodyUF += outputUF;
		}

		connectionUF.disconnect();		
		cadDARFBody.setUfList((List<UF>)mapper.readValue(responseBodyUF, new TypeReference<List<UF>>() {}));
		
		 // ###  MUNICIPIO   ####
		HttpURLConnection connectionMunicipio = (HttpURLConnection) urlMunicipio.openConnection();
		connectionMunicipio.setRequestMethod("GET");
		connectionMunicipio.setRequestProperty("Content-Type", "application/json");
		
		if (token.getCookie() != null) {
			connectionMunicipio.setRequestProperty("Cookie", token.getCookie());
			
			// necessário para teste fora do domínio único
			if (!backendDominioUnico){
				connectionMunicipio.setRequestProperty("Authorization", "Token Teste");
				connectionMunicipio.setRequestProperty("OAM_SERPRO_CPF", token.getCpf());
				connectionMunicipio.setRequestProperty("OAM_IDENTITY_ASSERTION", token.getSaml());
			}
			
		}
		
		InputStream isMunicipio = connectionMunicipio.getInputStream();
		
		switch (connectionMunicipio.getResponseCode()) {
		case HttpURLConnection.HTTP_UNAUTHORIZED:			
			throw new UnauthorizedException();
		
		case HttpURLConnection.HTTP_BAD_REQUEST:			
			throw new BadRequestException();
			
		default:			
			break;
		}		
		
		BufferedReader brMunicipio = new BufferedReader(new InputStreamReader((isMunicipio)));

		String responseBodyMunicipio = "";
		String outputMunicipio;
		while ((outputMunicipio = brMunicipio.readLine()) != null) {
			responseBodyMunicipio += outputMunicipio;
		}

		connectionMunicipio.disconnect();		
		cadDARFBody.setMunicipioList((List<Municipio>)mapper.readValue(responseBodyMunicipio, new TypeReference<List<Municipio>>() {}));
		
		 // ### TIPO ###
		HttpURLConnection connectionTipo = (HttpURLConnection) urlTipo.openConnection();
		connectionTipo.setDoOutput(true);
		connectionTipo.setRequestMethod("GET");
		connectionTipo.setRequestProperty("Content-Type", "application/json");
		
		if (token.getCookie() != null) {
			connectionTipo.setRequestProperty("Cookie", token.getCookie());
			
			// necessário para teste fora do domínio único
			if (!backendDominioUnico){
				connectionTipo.setRequestProperty("Authorization", "Token Teste");
				connectionTipo.setRequestProperty("OAM_SERPRO_CPF", token.getCpf());
				connectionTipo.setRequestProperty("OAM_IDENTITY_ASSERTION", token.getSaml());
			}
			
		}
		
		InputStream isTipo = connectionTipo.getInputStream();
		
		switch (connectionTipo.getResponseCode()) {
		case HttpURLConnection.HTTP_UNAUTHORIZED:			
			throw new UnauthorizedException();
		
		case HttpURLConnection.HTTP_BAD_REQUEST:			
			throw new BadRequestException();
			
		default:			
			break;
		}		
		
		BufferedReader brTipo = new BufferedReader(new InputStreamReader((isTipo)));

		String responseBodyTipo = "";
		String outputTipo;
		while ((outputTipo = brTipo.readLine()) != null) {
			responseBodyTipo += outputTipo;
		}

		connectionTipo.disconnect();		
		cadDARFBody.setTipoList((List<Tipo>)mapper.readValue(responseBodyTipo, new TypeReference<List<Tipo>>() {}));
		
		// RISCO
		HttpURLConnection connectionRisco = (HttpURLConnection) urlRisco.openConnection();
		connectionRisco.setDoOutput(true);
		connectionRisco.setRequestMethod("GET");
		connectionRisco.setRequestProperty("Content-Type", "application/json");
		
		if (token.getCookie() != null) {
			connectionRisco.setRequestProperty("Cookie", token.getCookie());
			
			// necessário para teste fora do domínio único
			if (!backendDominioUnico){
				connectionRisco.setRequestProperty("Authorization", "Token Teste");
				connectionRisco.setRequestProperty("OAM_SERPRO_CPF", token.getCpf());
				connectionRisco.setRequestProperty("OAM_IDENTITY_ASSERTION", token.getSaml());
			}			
		}
		
		InputStream isRisco = connectionRisco.getInputStream();
		
		switch (connectionRisco.getResponseCode()) {
		case HttpURLConnection.HTTP_UNAUTHORIZED:			
			throw new UnauthorizedException();
		
		case HttpURLConnection.HTTP_BAD_REQUEST:			
			throw new BadRequestException();
			
		default:			
			break;
		}		
		
		BufferedReader brRisco = new BufferedReader(new InputStreamReader((isRisco)));

		String responseBodyRisco = "";
		String outputRisco;
		while ((outputRisco = brRisco.readLine()) != null) {
			responseBodyRisco += outputRisco;
		}

		connectionRisco.disconnect();		
		cadDARFBody.setRiscoList((List<Risco>)mapper.readValue(responseBodyRisco, new TypeReference<List<Risco>>() {}));
		
		return Response.ok().entity(cadDARFBody).build();
	
	}
	
	@POST
	@LoggedIn
	@Transactional
	@ValidatePayload
	@RequiredPermission(resource = "darf", operation = "inserir")
	@ApiOperation(value = "Insere darf", response = Response.class)	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Erro nos parâmetros informados") })
	public Response insert(DARF darf, @Context UriInfo uriInfo) throws Throwable {

		checkId(darf);
		
		Retencao retencao = retencaoBC.load(darf.getRetencao().getId());
		
		darf.setRetencao(retencao);
		DARF darfNovo = bc.insert(darf);
		URI location = uriInfo.getRequestUriBuilder().path(darfNovo.getId().toString()).build();
	
		return Response.created(location).entity(darfNovo).build();
	}
	
	@POST
	@Path("/composicao")
	@Transactional
	@LoggedIn
	@ValidatePayload
	@ApiOperation(value = "Teste de composição de serviços. Atualiza um DARF já existente e cadastra um novo risco", notes = "É obrigatório informar o parâmetro", response = Response.class)	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "É obrigatório informar o parâmetro") })
	public Response insertComposto(DARF darf, @Context UriInfo uriInfo) throws Exception {
		
		checkId(darf);
			
		// cadastra novo Risco a partir do servico REST desta entidade de negócio
		Risco risco = new Risco();
		risco.setDescricao("risco novo "+Math.random());
		
		URL url = new URL("http://localhost:8080/sri-servicos/api/risco");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		
		AutentikusToken token = Beans.getReference(AutentikusToken.class);
		if (token.getCookie() != null) {
			connection.setRequestProperty("Cookie", token.getCookie());
			
			// necessário para teste fora do domínio único
			connection.setRequestProperty("Authorization", "Token Teste");
			connection.setRequestProperty("OAM_SERPRO_CPF", token.getCpf());
			connection.setRequestProperty("OAM_IDENTITY_ASSERTION", token.getSaml());
		}
		
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		writer.write((new ObjectMapper()).writeValueAsString(risco));
		writer.close();
		
		OutputStream os = connection.getOutputStream();
		os.flush();
		
		switch (connection.getResponseCode()) {
		case HttpURLConnection.HTTP_UNAUTHORIZED:			
			throw new UnauthorizedException();
		
		case HttpURLConnection.HTTP_BAD_REQUEST:
			
			throw new BadRequestException();
			
		default:
			
			break;
		}		
		
		BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

		String responseBody = "";
		String output;
		while ((output = br.readLine()) != null) {
			responseBody += output;
		}

		connection.disconnect();

		ObjectMapper mapper = new ObjectMapper();
		Risco riscoNovo = mapper.readValue(responseBody, new TypeReference<Risco>() {});
		
		darf.setRisco(riscoNovo);
		DARF darfNovo = bc.insert(darf);
		
		URI location = uriInfo.getRequestUriBuilder().path(darfNovo.getId().toString()).build();
		
		return Response.created(location).entity(darfNovo).build();
	
	}
	
	@PUT
	@Path("/{id}")
	@Transactional
	@LoggedIn
	@ValidatePayload
	@ApiOperation(value = "Atualiza um DARF já existente", notes = "É obrigatório informar o parâmetro", response = Response.class)	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "É obrigatório informar o parâmetro") })
	public Response update(@ApiParam(value = "id do darf", required=true) @PathParam("id") Long id, DARF darf, @Context UriInfo uriInfo) throws Exception {
		
		darf.setId(id);
		DARF darfAtualizado = bc.update(darf);
		
		if (darfAtualizado == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			
			URI location = uriInfo.getRequestUriBuilder().build();
			
			return Response.noContent().location(location).entity(darfAtualizado).build();
		}
	}
	
	

	@DELETE
	@LoggedIn
	@Path("/{id}")
	@Transactional
	@ApiOperation(value = "Exclui DARF pelo id", notes = "É obrigatório informar o parâmetro")	
	@ApiResponses(value = { @ApiResponse(code = 400, message = "É obrigatório informar o parâmetro") })
	public Response delete(@ApiParam(value = "id do DARF", required=true) @PathParam("id") Long id) throws Exception {
		
		bc.delete(id);
		
		return Response.noContent().build();
	}

	private void checkId(DARF entity) throws Exception {
		if (entity.getId() != null) {
			throw new BadRequestException();
		}
	}
	
}
