package com.keralty.aeconfiguraciones.api.grupoasignacion;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;
import com.keralty.aeconfiguraciones.service.GrupoAsignacionService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.ds.DSGrupoAsignacion;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.piu.PIUGrupoAsignacion1Response;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.piu.PIUGrupoAsignacionRequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.piu.PIUGrupoAsignacionResponse;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TADDUSUARIORequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TCREARRequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TDELUSUARIORequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TELIMINARRequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TMODIFICARRequest;
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

@Path("/api/GrupoAsignacionApi")
@RequestScoped
public class GrupoAsignacionApi {

    GrupoAsignacionService service;

    public GrupoAsignacionApi(GrupoAsignacionService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{grupoAsignacionId}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public GrupoAsignacion getInstance(Long grupoAsignacionId) {
    	return service.getById(grupoAsignacionId);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public List<GrupoAsignacion> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public List<PreloadViewModel> getGrupoAsignacionPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public GrupoAsignacion getByOid(GrupoAsignacionOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public GrupoAsignacion getByOIDWithHV(GrupoAsignacionOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetGrupoAsignacionDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public InstanceResponseViewModel getGrupoAsignacionDynamic(QueryRequest request) {
        GrupoAsignacionOid oid = new GrupoAsignacionOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getGrupoAsignacionDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public PreloadResponseViewModel getGrupoAsignacionWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public PopulationResponseViewModel getGrupoAsignacionPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/TELIMINAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionTELIMINAR(TELIMINARRequest request) {
        return service.tELIMINAR(request);
    }
    

    @POST
    @Path(value = "/TELIMINARCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionTELIMINARCheckPreconditions(TELIMINARRequest request) {

        return service.tELIMINARCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TCREAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionTCREAR(TCREARRequest request) {
        return service.tCREAR(request);
    }
    

    @POST
    @Path(value = "/TMODIFICAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionTMODIFICAR(TMODIFICARRequest request) {
        return service.tMODIFICAR(request);
    }
    

    @POST
    @Path(value = "/TADDUSUARIO")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionTADDUSUARIO(TADDUSUARIORequest request) {
        return service.tADDUSUARIO(request);
    }
    

    @POST
    @Path(value = "/TADDUSUARIOCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionTADDUSUARIOCheckPreconditions(TADDUSUARIORequest request) {

        return service.tADDUSUARIOCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TDELUSUARIO")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsignacionTDELUSUARIO(TDELUSUARIORequest request) {
        return service.tDELUSUARIO(request);
    }
    

    @POST
    @Path(value = "/IIU_Grupo_M_Dominio")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public DSGrupoAsignacion iiuIIUGrupoMDominio(GrupoAsignacionOid oid) {
        return service.iIUGrupoMDominio(oid);
    }

    @POST
    @Path(value = "/IIU_GrupoMedico_M_Dominio")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public DSGrupoAsignacion iiuIIUGrupoMedicoMDominio(GrupoAsignacionOid oid) {
        return service.iIUGrupoMedicoMDominio(oid);
    }

    @POST
    @Path(value = "/PIU_GrupoAsignacion")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public PIUGrupoAsignacionResponse piuPIUGrupoAsignacion(PIUGrupoAsignacionRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUGrupoAsignacionResponse response;
        try
        {
            response = new PIUGrupoAsignacionResponse(service.piuPIUGrupoAsignacion(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUGrupoAsignacion(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUGrupoAsignacionResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_GrupoAsignacion1")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor"})
    public PIUGrupoAsignacion1Response piuPIUGrupoAsignacion1(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUGrupoAsignacion1Response response;
        try
        {
            response = new PIUGrupoAsignacion1Response(service.piuPIUGrupoAsignacion1(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUGrupoAsignacion1(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUGrupoAsignacion1Response();
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
