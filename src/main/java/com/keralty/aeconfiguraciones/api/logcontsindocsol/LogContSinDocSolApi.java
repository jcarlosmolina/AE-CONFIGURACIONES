package com.keralty.aeconfiguraciones.api.logcontsindocsol;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.LogContSinDocSol;
import com.keralty.aeconfiguraciones.persistence.oid.LogContSinDocSolOid;
import com.keralty.aeconfiguraciones.service.LogContSinDocSolService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.piu.PIULogContSinDocSolRequest;
import com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.piu.PIULogContSinDocSolResponse;
import com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.siu.EliminarRequest;
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

@Path("/api/LogContSinDocSolApi")
@RequestScoped
public class LogContSinDocSolApi {

    LogContSinDocSolService service;

    public LogContSinDocSolApi(LogContSinDocSolService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{logContSinDocSolidLogContSinDocSol}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogContSinDocSol getInstance(Long logContSinDocSolidLogContSinDocSol) {
    	return service.getById(logContSinDocSolidLogContSinDocSol);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public List<LogContSinDocSol> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogContSinDocSol getByOid(LogContSinDocSolOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public LogContSinDocSol getByOIDWithHV(LogContSinDocSolOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetLogContSinDocSolDynamic")
    @RolesAllowed({"Admin","SuperAdmin"})
    public InstanceResponseViewModel getLogContSinDocSolDynamic(QueryRequest request) {
        LogContSinDocSolOid oid = new LogContSinDocSolOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getLogContSinDocSolDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin"})
    public PopulationResponseViewModel getLogContSinDocSolPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse logContSinDocSolEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    


    @POST
    @Path(value = "/PIU_LogContSinDocSol")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin"})
    public PIULogContSinDocSolResponse piuPIULogContSinDocSol(PIULogContSinDocSolRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIULogContSinDocSolResponse response;
        try
        {
            response = new PIULogContSinDocSolResponse(service.piuPIULogContSinDocSol(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIULogContSinDocSol(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIULogContSinDocSolResponse();
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
