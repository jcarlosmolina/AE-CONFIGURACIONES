package com.keralty.aeconfiguraciones.api.grupoasignacionnovedad;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionNovedadOid;
import com.keralty.aeconfiguraciones.service.GrupoAsignacionNovedadService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.piu.PIUGrpAsigNovedadDDominioResponse;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TASGGRUPORequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.TCREARRequest;
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

@Path("/api/GrupoAsignacionNovedadApi")
@RequestScoped
public class GrupoAsignacionNovedadApi {

    GrupoAsignacionNovedadService service;

    public GrupoAsignacionNovedadApi(GrupoAsignacionNovedadService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{grupoAsignacionNovedadId}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public GrupoAsignacionNovedad getInstance(Long grupoAsignacionNovedadId) {
    	return service.getById(grupoAsignacionNovedadId);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public List<GrupoAsignacionNovedad> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public GrupoAsignacionNovedad getByOid(GrupoAsignacionNovedadOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public GrupoAsignacionNovedad getByOIDWithHV(GrupoAsignacionNovedadOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetGrupoAsignacionNovedadDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public InstanceResponseViewModel getGrupoAsignacionNovedadDynamic(QueryRequest request) {
        GrupoAsignacionNovedadOid oid = new GrupoAsignacionNovedadOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getGrupoAsignacionNovedadDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public PopulationResponseViewModel getGrupoAsignacionNovedadPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionNovedadEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/TCREAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionNovedadTCREAR(TCREARRequest request) {
        return service.tCREAR(request);
    }
    

    @POST
    @Path(value = "/TCREARCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionNovedadTCREARCheckPreconditions(TCREARRequest request) {

        return service.tCREARCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TASGGRUPO")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionNovedadTASGGRUPO(TASGGRUPORequest request) {
        return service.tASGGRUPO(request);
    }
    

    @POST
    @Path(value = "/TASGGRUPOCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionNovedadTASGGRUPOCheckPreconditions(TASGGRUPORequest request) {

        return service.tASGGRUPOCheckPreconditions(request);
    }


    @POST
    @Path(value = "/PIU_GrpAsigNovedad_D_Dominio")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public PIUGrpAsigNovedadDDominioResponse piuPIUGrpAsigNovedadDDominio(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUGrpAsigNovedadDDominioResponse response;
        try
        {
            response = new PIUGrpAsigNovedadDDominioResponse(service.piuPIUGrpAsigNovedadDDominio(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUGrpAsigNovedadDDominio(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUGrpAsigNovedadDDominioResponse();
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
