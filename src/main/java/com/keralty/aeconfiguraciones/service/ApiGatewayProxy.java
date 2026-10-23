package com.keralty.aeconfiguraciones.service;


import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/")
@RegisterRestClient(configKey="ae-apigateway")
@ClientHeaderParam(name = "Authorization", value = "Bearer {com.keralty.aeconfiguraciones.utils.TokenUtilities.getToken4aeapigateway}")
public interface ApiGatewayProxy {

    @POST
    @Path("/api/LogLlamadasBHApi/create_instance")
    CompletionStage<ServiceResponse> createinstanceAsync(@RequestBody com.keralty.aeconfiguraciones.viewmodel.logllamadasbh.siu.CreateinstanceRequest request);

}
