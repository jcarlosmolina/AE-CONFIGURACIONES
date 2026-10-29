package com.keralty.aeconfiguraciones.api.contratocombo;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.ContratoCombo;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoComboOid;
import com.keralty.aeconfiguraciones.service.ContratoComboService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.contratocombo.piu.PIUContratoComboRequest;
import com.keralty.aeconfiguraciones.viewmodel.contratocombo.piu.PIUContratoComboResponse;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/ContratoComboApi")
@RequestScoped
public class ContratoComboApi {

    ContratoComboService service;

    public ContratoComboApi(ContratoComboService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{contratoComboId}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public ContratoCombo getInstance(Long contratoComboId) {
    	return service.getById(contratoComboId);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public List<ContratoCombo> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public ContratoCombo getByOid(ContratoComboOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public ContratoCombo getByOIDWithHV(ContratoComboOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetContratoComboDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getContratoComboDynamic(QueryRequest request) {
        ContratoComboOid oid = new ContratoComboOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getContratoComboDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getContratoComboPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }



    @POST
    @Path(value = "/PIU_ContratoCombo")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public PIUContratoComboResponse piuPIUContratoCombo(PIUContratoComboRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUContratoComboResponse response;
        try
        {
            response = new PIUContratoComboResponse(service.piuPIUContratoCombo(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUContratoCombo(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUContratoComboResponse();
            response.setException(e);
        }
        return response;
    }


    @GET
    @Path("buildRelatedInstance/{relatedInstanceJsonOid}/{relatedInstancePath}")
    @Operation(hidden = true)
    public AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePath) {
        return service.buildRelatedInstance(relatedInstanceJsonOid, relatedInstancePath);
    }
}
