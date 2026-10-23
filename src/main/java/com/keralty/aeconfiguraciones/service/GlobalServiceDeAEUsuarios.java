package com.keralty.aeconfiguraciones.service;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/api/GlobalServicesApi")
@RegisterRestClient(configKey="ae-usuarios")
@ClientHeaderParam(name = "Authorization", value = "Bearer {com.keralty.aeconfiguraciones.utils.TokenUtilities.getToken4aeusuarios}")
public interface GlobalServiceDeAEUsuarios {

    @POST
    @Path(value = "/OGSINCASESORES")
    public Object oGSINCSUCURSALES(Object request);
}
