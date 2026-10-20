package com.keralty.aeconfiguraciones.api.logpriorizacionsol;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.persistence.oid.LogPriorizacionSolOid;
import com.keralty.aeconfiguraciones.service.LogPriorizacionSolService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.piu.PIULogPriorizacionSolResponse;
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

@Path("/api/LogPriorizacionSolApi")
@RequestScoped
public class LogPriorizacionSolApi {

    LogPriorizacionSolService service;

    public LogPriorizacionSolApi(LogPriorizacionSolService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{logPriorizacionSolId}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogPriorizacionSol getInstance(Long logPriorizacionSolId) {
    	return service.getById(logPriorizacionSolId);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public List<LogPriorizacionSol> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogPriorizacionSol getByOid(LogPriorizacionSolOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogPriorizacionSol getByOIDWithHV(LogPriorizacionSolOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetLogPriorizacionSolDynamic")
    @RolesAllowed({"Admin","SuperAdmin"})
    public InstanceResponseViewModel getLogPriorizacionSolDynamic(QueryRequest request) {
        LogPriorizacionSolOid oid = new LogPriorizacionSolOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getLogPriorizacionSolDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin"})
    public PopulationResponseViewModel getLogPriorizacionSolPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }



    @POST
    @Path(value = "/PIU_LogPriorizacionSol")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public PIULogPriorizacionSolResponse piuPIULogPriorizacionSol(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIULogPriorizacionSolResponse response;
        try
        {
            response = new PIULogPriorizacionSolResponse(service.piuPIULogPriorizacionSol(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIULogPriorizacionSol(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIULogPriorizacionSolResponse();
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
