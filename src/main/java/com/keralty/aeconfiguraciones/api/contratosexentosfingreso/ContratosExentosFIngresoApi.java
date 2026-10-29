package com.keralty.aeconfiguraciones.api.contratosexentosfingreso;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.ContratosExentosFIngreso;
import com.keralty.aeconfiguraciones.persistence.oid.ContratosExentosFIngresoOid;
import com.keralty.aeconfiguraciones.service.ContratosExentosFIngresoService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.piu.PIUContratosExentosFIngresoRequest;
import com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.piu.PIUContratosExentosFIngresoResponse;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/ContratosExentosFIngresoApi")
@RequestScoped
public class ContratosExentosFIngresoApi {

    ContratosExentosFIngresoService service;

    public ContratosExentosFIngresoApi(ContratosExentosFIngresoService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{contratosExentosFIngresoidContratosExentosFIngreso}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public ContratosExentosFIngreso getInstance(Long contratosExentosFIngresoidContratosExentosFIngreso) {
    	return service.getById(contratosExentosFIngresoidContratosExentosFIngreso);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public List<ContratosExentosFIngreso> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public List<PreloadViewModel> getContratosExentosFIngresoPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public ContratosExentosFIngreso getByOid(ContratosExentosFIngresoOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public ContratosExentosFIngreso getByOIDWithHV(ContratosExentosFIngresoOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetContratosExentosFIngresoDynamic")
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public InstanceResponseViewModel getContratosExentosFIngresoDynamic(QueryRequest request) {
        ContratosExentosFIngresoOid oid = new ContratosExentosFIngresoOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getContratosExentosFIngresoDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public PreloadResponseViewModel getContratosExentosFIngresoWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public PopulationResponseViewModel getContratosExentosFIngresoPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }



    @POST
    @Path(value = "/PIU_ContratosExentosFIngreso")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public PIUContratosExentosFIngresoResponse piuPIUContratosExentosFIngreso(PIUContratosExentosFIngresoRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUContratosExentosFIngresoResponse response;
        try
        {
            response = new PIUContratosExentosFIngresoResponse(service.piuPIUContratosExentosFIngreso(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUContratosExentosFIngreso(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUContratosExentosFIngresoResponse();
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
