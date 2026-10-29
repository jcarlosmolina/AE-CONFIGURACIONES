package com.keralty.integraciones.organization.getorganization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.integraciones.organization.getorganization.response.GetOrganizationResponse;

public class ConsultarOrganizationResponse {
    private GetOrganizationResponse getOrganizationResponse;
    private boolean exito = true;;
    private String textoError = null;

   
	@JsonProperty("getOrganizationResponse")
    public GetOrganizationResponse getGetOrganizationResponse() { return getOrganizationResponse; }
	@JsonProperty("getOrganizationResponse")
	public void setGetOrganizationResponse(GetOrganizationResponse getOrganizationResponse) { this.getOrganizationResponse = getOrganizationResponse; }

	@JsonProperty("exito")
    public boolean isExito() { return exito; }
    @JsonProperty("exito")
	public void setExito(boolean exito) { this.exito = exito; }
	
    @JsonProperty("textoError")
    public String getTextoError() { return textoError; }
    @JsonProperty("textoError")
	public void setTextoError(String textoError) { this.textoError = textoError; }

    
    /**
     * Comprueba si se ha producido algún error en la respuesta de BH y ajusta los valores de las variables 
     */
    public void checkError() {
    	
	}
    
    private void anyadirTextoError(String texto) {
    	if (texto == null || texto.isEmpty()) {
    		return;
    	}
    	if (getTextoError() == null || getTextoError().isEmpty()) {
    		setTextoError(texto);
    	} else {
    		setTextoError(getTextoError() + " " + texto);
    	}
    }
}
