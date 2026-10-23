package com.keralty.aeconfiguraciones.service;

import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCRONIZARPARAMETRICASRequest;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/api/GlobalServicesApi")
@RegisterRestClient(configKey="ae-configuraciones")
@ClientHeaderParam(name = "Authorization", value = "Bearer {com.keralty.aeconfiguraciones.utils.TokenUtilities.getToken4aeconfiguraciones}")
public interface GlobalServicesItSelf {

	
	// Definimos el mismo interfaz pero asíncrono

    @POST
    @Path(value = "/OGSINCRONIZARPARAMETRICAS_Async")
    public CompletionStage<ServiceResponse> oGSINCRONIZARPARAMETRICAS_Async(OGSINCRONIZARPARAMETRICASRequest request);
    
}
