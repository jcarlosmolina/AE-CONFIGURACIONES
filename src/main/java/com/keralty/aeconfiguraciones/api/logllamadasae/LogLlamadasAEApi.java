package com.keralty.aeconfiguraciones.api.logllamadasae;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.LogLlamadasAE;
import com.keralty.aeconfiguraciones.persistence.oid.LogLlamadasAEOid;
import com.keralty.aeconfiguraciones.service.LogLlamadasAEService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasae.ds.DSLogLlamadasAEIIU;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasae.piu.PIULogLlamadasAERequest;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasae.piu.PIULogLlamadasAEResponse;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasae.siu.CreateinstanceRequest;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasae.siu.DeleteinstanceRequest;
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

@Path("/api/LogLlamadasAEApi")
@RequestScoped
public class LogLlamadasAEApi {

    LogLlamadasAEService service;

    public LogLlamadasAEApi(LogLlamadasAEService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{logLlamadasAEID}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public LogLlamadasAE getInstance(Long logLlamadasAEID) {
    	return service.getById(logLlamadasAEID);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public List<LogLlamadasAE> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public LogLlamadasAE getByOid(LogLlamadasAEOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public LogLlamadasAE getByOIDWithHV(LogLlamadasAEOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetLogLlamadasAEDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public InstanceResponseViewModel getLogLlamadasAEDynamic(QueryRequest request) {
        LogLlamadasAEOid oid = new LogLlamadasAEOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getLogLlamadasAEDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PopulationResponseViewModel getLogLlamadasAEPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/create_instance")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public ServiceResponse logLlamadasAECreateinstance(CreateinstanceRequest request) {
        return service.createinstance(request);
    }
    

    @POST
    @Path(value = "/delete_instance")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse logLlamadasAEDeleteinstance(DeleteinstanceRequest request) {
        return service.deleteinstance(request);
    }
    

    @POST
    @Path(value = "/IIU_LogLlamadasAE")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSLogLlamadasAEIIU iiuIIULogLlamadasAE(LogLlamadasAEOid oid) {
        return service.iIULogLlamadasAE(oid);
    }

    @POST
    @Path(value = "/PIU_LogLlamadasAE")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PIULogLlamadasAEResponse piuPIULogLlamadasAE(PIULogLlamadasAERequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIULogLlamadasAEResponse response;
        try
        {
            response = new PIULogLlamadasAEResponse(service.piuPIULogLlamadasAE(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIULogLlamadasAE(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIULogLlamadasAEResponse();
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
