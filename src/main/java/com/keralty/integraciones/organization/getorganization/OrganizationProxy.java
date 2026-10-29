package com.keralty.integraciones.organization.getorganization;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.keralty.integraciones.organization.getorganization.request.GetOrganizationRequest;
import com.keralty.integraciones.organization.getorganization.response.GetOrganizationResponse;

import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("")
@RegisterRestClient(configKey="integraciones_organization")
public interface OrganizationProxy {
	
	@POST
	@Path("/getOrganization")
	@ClientHeaderParam(name="Content-Type", value="application/json")
	@ClientHeaderParam(name="Accept", value="application/json")
	@ClientHeaderParam(name="codApplication", value="${com.keralty.integraciones.codaplicacion}")
	@ClientHeaderParam(name="businessFunction", value="${com.keralty.integraciones.funcionnegocio}")
	
	public GetOrganizationResponse getOrganization(
			@HeaderParam("Authorization") String token, 
			@HeaderParam("terminalId") String idTerminal, 
			@HeaderParam("user") String usuario, 
			@HeaderParam("requestDate") String fechaPeticion, 
			@HeaderParam("queryType") String tipoConsulta,
			@RequestBody GetOrganizationRequest request);
}
