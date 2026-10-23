package com.keralty.integraciones.organization.getorganization;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.integraciones.CallBHUtils;
import com.keralty.integraciones.IntegracionesUtils;
import com.keralty.integraciones.organization.getorganization.request.Entry;
import com.keralty.integraciones.organization.getorganization.request.GetOrganizationRequest;
import com.keralty.integraciones.organization.getorganization.request.Request;
import com.keralty.integraciones.token.TokenUtils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrganizationUtils {

	CallBHUtils callBHUtils;

	TokenUtils tokenUtils;


	@RestClient
	OrganizationProxy organizationProxy;

	@ConfigProperty(name="com.keralty.integraciones.maxintentosllamada", defaultValue = "1" )
    private int maxIntentosLlamada;

    private int maxReintentos401 = 2;
	
	@Inject
	public OrganizationUtils(CallBHUtils callBHUtils, TokenUtils tokenUtils) {
		this.callBHUtils = callBHUtils;
		this.tokenUtils = tokenUtils;
	}
	
	public ConsultarOrganizationResponse consultarEPS() {
		GetOrganizationRequest getOrgRequest = new GetOrganizationRequest();
		getOrgRequest.setResourceType("Bundle");
		getOrgRequest.setID("bundle-request-organization");
		getOrgRequest.setType("batch");
		getOrgRequest.setEntry(new ArrayList<Entry>());
		Entry entry = new Entry();
		entry.setRequest(new Request());
		entry.getRequest().setMethod("GET");
		entry.getRequest().setURL("&type=BH/entityType|EPS-");
		getOrgRequest.getEntry().add(entry);
		
		return  callGetOrganization("3", getOrgRequest);
	}

	/**
	 * Devuelve las sucursales de ColSanitas y MediSanitas. Puede contener elementos repetidos para aquellas sucursales que operen con ambas.
	 * @return
	 */
	public ConsultarOrganizationResponse consultarSucursales() {
		
		// NIT COLSANITAS	NI-860078828
		ConsultarOrganizationResponse sucursales = consultarSucursales("NI-860078828");
		
		// NIT MEDISANITAS	NI-800153424
		ConsultarOrganizationResponse sucursalesMedisanitas = consultarSucursales("NI-800153424");

		if (sucursales != null && sucursales.getGetOrganizationResponse() != null && sucursales.isExito() &&
				sucursalesMedisanitas != null && sucursalesMedisanitas.getGetOrganizationResponse() != null && sucursalesMedisanitas.isExito()) {
			
    		for (com.keralty.integraciones.organization.getorganization.response.Entry entry : sucursalesMedisanitas.getGetOrganizationResponse().getEntry()) {
    			if (entry.getResource() != null && entry.getResource().getSchema() != null) {
    				sucursales.getGetOrganizationResponse().getEntry().add(entry);
    			}
    		}
		} else {
			return null;
		}
		
		return  sucursales;
	}
	
	public ConsultarOrganizationResponse consultarSucursales(String nit) {
		GetOrganizationRequest getOrgRequest = new GetOrganizationRequest();
		getOrgRequest.setResourceType("Bundle");
		getOrgRequest.setID("bundle-request-organization");
		getOrgRequest.setType("batch");
		getOrgRequest.setEntry(new ArrayList<Entry>());
		Entry entry = new Entry();
		entry.setRequest(new Request());
		entry.getRequest().setMethod("GET");
		entry.getRequest().setURL("organization?identifier=BH|" + nit + "&type=BH/organizationType|1-");
		getOrgRequest.getEntry().add(entry);
		
		return  callGetOrganization("4", getOrgRequest);
	}
	
	public ConsultarOrganizationResponse consultarCompanyas() {
		GetOrganizationRequest getOrgRequest = new GetOrganizationRequest();
		getOrgRequest.setResourceType("Bundle");
		getOrgRequest.setID("bundle-request-organization");
		getOrgRequest.setType("batch");
		getOrgRequest.setEntry(new ArrayList<Entry>());
		Entry entry = new Entry();
		entry.setRequest(new Request());
		entry.getRequest().setMethod("GET");
		entry.getRequest().setURL("&type=BH/entityType|MP-");
		getOrgRequest.getEntry().add(entry);
		
		return  callGetOrganization("3", getOrgRequest);
	}

	private String getRequestDate() {
		java.util.Date currentDate = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(currentDate); 
	}
	public ConsultarOrganizationResponse callGetOrganization(String tipoRequest, GetOrganizationRequest getOrgRequest) {
		
		long startTime = System.currentTimeMillis();
		Timestamp fechaHora = STDFunctions.systemDateTimeFun();	
		String mensajeError = "";
		long endTime = startTime;

		ConsultarOrganizationResponse response = new ConsultarOrganizationResponse();
		
		// Datos de headers
		String idTerminal = "1";
		
		int numIntentos = 1;
		int numReintentos401 = 0;

		String token = "";
		try {
			token = tokenUtils.getTokenValue();
		} catch (Exception e) {
			org.slf4j.LoggerFactory.getLogger(this.getClass().getName()).error("ORG-0010. Error obteniendo token. " + e.getMessage());
			response.setExito(false);
			response.setTextoError(IntegracionesUtils.getTextoErrorUsuarioFinal("ORG-0010"));
			return response;
		}
		while (numIntentos <= maxIntentosLlamada && numReintentos401 < maxReintentos401) {

			startTime = System.currentTimeMillis();
			fechaHora = STDFunctions.systemDateTimeFun();	

        	try {
        		response.setGetOrganizationResponse(organizationProxy.getOrganization(token, 
        				idTerminal, 
        				"user",
        				getRequestDate(), 
        				tipoRequest,
        				getOrgRequest));
        		endTime = System.currentTimeMillis();
        		mensajeError = "";
        		response.checkError();
        		break;
        	} catch (Exception e) {
        		endTime = System.currentTimeMillis();
        		mensajeError = e.getMessage();
    			if (mensajeError != null && mensajeError.indexOf("401") >= 0) {
					org.slf4j.LoggerFactory.getLogger(this.getClass().getName()).info("Organization getOrganization. Reintento por 401");

					// Guardamos la llamada al WS
					callBHUtils.guardarLlamadaBH(fechaHora, "user", 
							"getOrganization",
							"Organization", 
							null, 
							getOrgRequest,
							response,
							(double) ((endTime - startTime)/1000.0f), 
							mensajeError);

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						org.slf4j.LoggerFactory.getLogger(this.getClass().getName()).error(e1.getMessage());
						Thread.currentThread().interrupt();
					}
					try {
						token = tokenUtils.getTokenValue();					
					} catch (Exception e2) {
						org.slf4j.LoggerFactory.getLogger(this.getClass().getName()).error("ORG-0020. Error obteniendo token. " + e.getMessage());
						response.setExito(false);
						response.setTextoError(IntegracionesUtils.getTextoErrorUsuarioFinal("ORG-0020"));
						return response;
					}
    				numReintentos401++;			
    			} else {
    				numIntentos++;
        		}
        	}
        }
		if (mensajeError != null && !mensajeError.isEmpty()) {
			org.slf4j.LoggerFactory.getLogger(this.getClass().getName()).error("ORG-0030. " + mensajeError);
			response.setExito(false);
			response.setTextoError(IntegracionesUtils.getTextoErrorUsuarioFinal("ORG-0030"));
		}

		callBHUtils.guardarLlamadaBH(fechaHora, "user", 
				"getOrganization",
				"Organization", 
				null, 
				getOrgRequest,
				response.getGetOrganizationResponse(),
				(double) ((endTime - startTime)/1000.0f), 
				mensajeError);

		return response;
	}
}
