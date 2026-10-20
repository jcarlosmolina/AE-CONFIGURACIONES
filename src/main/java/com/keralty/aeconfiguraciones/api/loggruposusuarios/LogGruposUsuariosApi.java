package com.keralty.aeconfiguraciones.api.loggruposusuarios;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.LogGruposUsuarios;
import com.keralty.aeconfiguraciones.persistence.oid.LogGruposUsuariosOid;
import com.keralty.aeconfiguraciones.service.LogGruposUsuariosService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.piu.PIULogGruposUsuariosResponse;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
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

@Path("/api/LogGruposUsuariosApi")
@RequestScoped
public class LogGruposUsuariosApi {

    LogGruposUsuariosService service;

    public LogGruposUsuariosApi(LogGruposUsuariosService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{logGruposUsuariosId}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogGruposUsuarios getInstance(Long logGruposUsuariosId) {
    	return service.getById(logGruposUsuariosId);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public List<LogGruposUsuarios> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogGruposUsuarios getByOid(LogGruposUsuariosOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogGruposUsuarios getByOIDWithHV(LogGruposUsuariosOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetLogGruposUsuariosDynamic")
    @RolesAllowed({"Admin","SuperAdmin"})
    public InstanceResponseViewModel getLogGruposUsuariosDynamic(QueryRequest request) {
        LogGruposUsuariosOid oid = new LogGruposUsuariosOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getLogGruposUsuariosDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin"})
    public PopulationResponseViewModel getLogGruposUsuariosPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }



    @POST
    @Path(value = "/PIU_LogGruposUsuarios")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public PIULogGruposUsuariosResponse piuPIULogGruposUsuarios(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIULogGruposUsuariosResponse response;
        try
        {
            response = new PIULogGruposUsuariosResponse(service.piuPIULogGruposUsuarios(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIULogGruposUsuarios(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIULogGruposUsuariosResponse();
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
