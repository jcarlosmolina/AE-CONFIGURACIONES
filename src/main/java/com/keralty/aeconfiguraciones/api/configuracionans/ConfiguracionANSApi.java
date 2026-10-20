package com.keralty.aeconfiguraciones.api.configuracionans;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionANSOid;
import com.keralty.aeconfiguraciones.service.ConfiguracionANSService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.configuracionans.ds.DSConfiguracionANSDetalle;
import com.keralty.aeconfiguraciones.viewmodel.configuracionans.piu.PIUConfiguracionANSResponse;
import com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TCREARRequest;
import com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TELIMINARRequest;
import com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu.TMODIFICARRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/ConfiguracionANSApi")
@RequestScoped
public class ConfiguracionANSApi {

    ConfiguracionANSService service;

    public ConfiguracionANSApi(ConfiguracionANSService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{configuracionANSId}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Gestor"})
    public ConfiguracionANS getInstance(Long configuracionANSId) {
    	return service.getById(configuracionANSId);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Gestor"})
    public List<ConfiguracionANS> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Gestor"})
    public ConfiguracionANS getByOid(ConfiguracionANSOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Gestor"})
    public ConfiguracionANS getByOIDWithHV(ConfiguracionANSOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetConfiguracionANSDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Gestor"})
    public InstanceResponseViewModel getConfiguracionANSDynamic(QueryRequest request) {
        ConfiguracionANSOid oid = new ConfiguracionANSOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getConfiguracionANSDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Gestor"})
    public PopulationResponseViewModel getConfiguracionANSPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/TCREAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse configuracionANSTCREAR(TCREARRequest request) {
        return service.tCREAR(request);
    }
    

    @POST
    @Path(value = "/TCREARCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse configuracionANSTCREARCheckPreconditions(TCREARRequest request) {

        return service.tCREARCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TMODIFICAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse configuracionANSTMODIFICAR(TMODIFICARRequest request) {
        return service.tMODIFICAR(request);
    }
    

    @POST
    @Path(value = "/TELIMINAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse configuracionANSTELIMINAR(TELIMINARRequest request) {
        return service.tELIMINAR(request);
    }
    

    @POST
    @Path(value = "/IIU_ConfiguracionANS")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Gestor"})
    public DSConfiguracionANSDetalle iiuIIUConfiguracionANS(ConfiguracionANSOid oid) {
        return service.iIUConfiguracionANS(oid);
    }

    @POST
    @Path(value = "/PIU_ConfiguracionANS")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Gestor"})
    public PIUConfiguracionANSResponse piuPIUConfiguracionANS(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUConfiguracionANSResponse response;
        try
        {
            response = new PIUConfiguracionANSResponse(service.piuPIUConfiguracionANS(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUConfiguracionANS(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUConfiguracionANSResponse();
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
