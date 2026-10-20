package com.keralty.aeconfiguraciones.api.priorizacionsolicitudes;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;
import com.keralty.aeconfiguraciones.service.PriorizacionSolicitudesService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.piu.PIUPriorizacionSolicitudesRequest;
import com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.piu.PIUPriorizacionSolicitudesResponse;
import com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TCREARRequest;
import com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TELIMINARRequest;
import com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu.TMODIFICARRequest;
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

@Path("/api/PriorizacionSolicitudesApi")
@RequestScoped
public class PriorizacionSolicitudesApi {

    PriorizacionSolicitudesService service;

    public PriorizacionSolicitudesApi(PriorizacionSolicitudesService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{priorizacionSolicitudesEstadoSolicitud}/{priorizacionSolicitudesEstadoDoc}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PriorizacionSolicitudes getInstance(Long priorizacionSolicitudesEstadoSolicitud, Long priorizacionSolicitudesEstadoDoc) {
    	return service.getById(priorizacionSolicitudesEstadoSolicitud, priorizacionSolicitudesEstadoDoc);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public List<PriorizacionSolicitudes> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PriorizacionSolicitudes getByOid(PriorizacionSolicitudesOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PriorizacionSolicitudes getByOIDWithHV(PriorizacionSolicitudesOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetPriorizacionSolicitudesDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public InstanceResponseViewModel getPriorizacionSolicitudesDynamic(QueryRequest request) {
        PriorizacionSolicitudesOid oid = new PriorizacionSolicitudesOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getPriorizacionSolicitudesDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PopulationResponseViewModel getPriorizacionSolicitudesPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/TCREAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse priorizacionSolicitudesTCREAR(TCREARRequest request) {
        return service.tCREAR(request);
    }
    

    @POST
    @Path(value = "/TELIMINAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse priorizacionSolicitudesTELIMINAR(TELIMINARRequest request) {
        return service.tELIMINAR(request);
    }
    

    @POST
    @Path(value = "/TMODIFICAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse priorizacionSolicitudesTMODIFICAR(TMODIFICARRequest request) {
        return service.tMODIFICAR(request);
    }
    


    @POST
    @Path(value = "/PIU_PriorizacionSolicitudes")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PIUPriorizacionSolicitudesResponse piuPIUPriorizacionSolicitudes(PIUPriorizacionSolicitudesRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUPriorizacionSolicitudesResponse response;
        try
        {
            response = new PIUPriorizacionSolicitudesResponse(service.piuPIUPriorizacionSolicitudes(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUPriorizacionSolicitudes(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUPriorizacionSolicitudesResponse();
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
