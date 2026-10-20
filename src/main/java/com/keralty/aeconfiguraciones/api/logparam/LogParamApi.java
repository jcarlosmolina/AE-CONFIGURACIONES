package com.keralty.aeconfiguraciones.api.logparam;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.LogParam;
import com.keralty.aeconfiguraciones.persistence.oid.LogParamOid;
import com.keralty.aeconfiguraciones.service.LogParamService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.logparam.piu.PIULogParamResponse;
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

@Path("/api/LogParamApi")
@RequestScoped
public class LogParamApi {

    LogParamService service;

    public LogParamApi(LogParamService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{logParamCodigo}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogParam getInstance(Long logParamCodigo) {
    	return service.getById(logParamCodigo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public List<LogParam> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogParam getByOid(LogParamOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogParam getByOIDWithHV(LogParamOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetLogParamDynamic")
    @RolesAllowed({"Admin","SuperAdmin"})
    public InstanceResponseViewModel getLogParamDynamic(QueryRequest request) {
        LogParamOid oid = new LogParamOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getLogParamDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin"})
    public PopulationResponseViewModel getLogParamPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }



    @POST
    @Path(value = "/PIU_LogParam")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public PIULogParamResponse piuPIULogParam(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIULogParamResponse response;
        try
        {
            response = new PIULogParamResponse(service.piuPIULogParam(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIULogParam(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIULogParamResponse();
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
