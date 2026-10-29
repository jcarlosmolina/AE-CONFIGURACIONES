package com.keralty.aeconfiguraciones.api.contsindocsol;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.ContSinDocSol;
import com.keralty.aeconfiguraciones.persistence.oid.ContSinDocSolOid;
import com.keralty.aeconfiguraciones.service.ContSinDocSolService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.contsindocsol.piu.PIUContSinDocSolRequest;
import com.keralty.aeconfiguraciones.viewmodel.contsindocsol.piu.PIUContSinDocSolResponse;
import com.keralty.aeconfiguraciones.viewmodel.contsindocsol.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
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

@Path("/api/ContSinDocSolApi")
@RequestScoped
public class ContSinDocSolApi {

    ContSinDocSolService service;

    public ContSinDocSolApi(ContSinDocSolService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{contSinDocSolidContSinDocSol}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public ContSinDocSol getInstance(Long contSinDocSolidContSinDocSol) {
    	return service.getById(contSinDocSolidContSinDocSol);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public List<ContSinDocSol> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public ContSinDocSol getByOid(ContSinDocSolOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public ContSinDocSol getByOIDWithHV(ContSinDocSolOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetContSinDocSolDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getContSinDocSolDynamic(QueryRequest request) {
        ContSinDocSolOid oid = new ContSinDocSolOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getContSinDocSolDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getContSinDocSolPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse contSinDocSolCrear(CrearRequest request) {
        return service.crear(request);
    }
    


    @POST
    @Path(value = "/PIU_ContSinDocSol")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Gestor","MesaDeApoyo"})
    public PIUContSinDocSolResponse piuPIUContSinDocSol(PIUContSinDocSolRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUContSinDocSolResponse response;
        try
        {
            response = new PIUContSinDocSolResponse(service.piuPIUContSinDocSol(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUContSinDocSol(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUContSinDocSolResponse();
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
