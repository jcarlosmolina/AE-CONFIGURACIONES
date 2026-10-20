package com.keralty.aeconfiguraciones.api.logcontratosexentosfingreso;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.LogContratosExentosFIngreso;
import com.keralty.aeconfiguraciones.persistence.oid.LogContratosExentosFIngresoOid;
import com.keralty.aeconfiguraciones.service.LogContratosExentosFIngresoService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.piu.PIULogContratosExentosFIngresRequest;
import com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.piu.PIULogContratosExentosFIngresResponse;
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

@Path("/api/LogContratosExentosFIngresoApi")
@RequestScoped
public class LogContratosExentosFIngresoApi {

    LogContratosExentosFIngresoService service;

    public LogContratosExentosFIngresoApi(LogContratosExentosFIngresoService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{logContratosExentosFIngresoidLogContratosExentosFIngreso}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public LogContratosExentosFIngreso getInstance(Long logContratosExentosFIngresoidLogContratosExentosFIngreso) {
    	return service.getById(logContratosExentosFIngresoidLogContratosExentosFIngreso);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public List<LogContratosExentosFIngreso> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public LogContratosExentosFIngreso getByOid(LogContratosExentosFIngresoOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public LogContratosExentosFIngreso getByOIDWithHV(LogContratosExentosFIngresoOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetLogContratosExentosFIngresoDynamic")
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public InstanceResponseViewModel getLogContratosExentosFIngresoDynamic(QueryRequest request) {
        LogContratosExentosFIngresoOid oid = new LogContratosExentosFIngresoOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getLogContratosExentosFIngresoDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public PopulationResponseViewModel getLogContratosExentosFIngresoPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }



    @POST
    @Path(value = "/PIU_LogContratosExentosFIngres")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public PIULogContratosExentosFIngresResponse piuPIULogContratosExentosFIngres(PIULogContratosExentosFIngresRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIULogContratosExentosFIngresResponse response;
        try
        {
            response = new PIULogContratosExentosFIngresResponse(service.piuPIULogContratosExentosFIngres(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIULogContratosExentosFIngres(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIULogContratosExentosFIngresResponse();
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
