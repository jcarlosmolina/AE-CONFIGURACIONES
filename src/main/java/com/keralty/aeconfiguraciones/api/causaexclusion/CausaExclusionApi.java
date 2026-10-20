package com.keralty.aeconfiguraciones.api.causaexclusion;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.CausaExclusion;
import com.keralty.aeconfiguraciones.persistence.oid.CausaExclusionOid;
import com.keralty.aeconfiguraciones.service.CausaExclusionService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.causaexclusion.piu.PIUCausaExclusionResponse;
import com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.ModificarRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
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

@Path("/api/CausaExclusionApi")
@RequestScoped
public class CausaExclusionApi {

    CausaExclusionService service;

    public CausaExclusionApi(CausaExclusionService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{causaExclusionidCausaExclusion}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public CausaExclusion getInstance(Long causaExclusionidCausaExclusion) {
    	return service.getById(causaExclusionidCausaExclusion);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<CausaExclusion> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getCausaExclusionPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public CausaExclusion getByOid(CausaExclusionOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public CausaExclusion getByOIDWithHV(CausaExclusionOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetCausaExclusionDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getCausaExclusionDynamic(QueryRequest request) {
        CausaExclusionOid oid = new CausaExclusionOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getCausaExclusionDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getCausaExclusionWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getCausaExclusionPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse causaExclusionCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse causaExclusionEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse causaExclusionModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    


    @POST
    @Path(value = "/PIU_CausaExclusion")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public PIUCausaExclusionResponse piuPIUCausaExclusion(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUCausaExclusionResponse response;
        try
        {
            response = new PIUCausaExclusionResponse(service.piuPIUCausaExclusion(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUCausaExclusion(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUCausaExclusionResponse();
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
