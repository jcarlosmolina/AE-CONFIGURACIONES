package com.keralty.aeconfiguraciones.api.logllamadasbh;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.LogLlamadasBH;
import com.keralty.aeconfiguraciones.persistence.oid.LogLlamadasBHOid;
import com.keralty.aeconfiguraciones.service.LogLlamadasBHService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasbh.ds.DSLogLlamadasBHIIU;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasbh.piu.PIULogLlamadasBHRequest;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasbh.piu.PIULogLlamadasBHResponse;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasbh.siu.CreateinstanceRequest;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasbh.siu.DeleteinstanceRequest;
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

@Path("/api/LogLlamadasBHApi")
@RequestScoped
public class LogLlamadasBHApi {

    LogLlamadasBHService service;

    public LogLlamadasBHApi(LogLlamadasBHService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{logLlamadasBHID}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public LogLlamadasBH getInstance(Long logLlamadasBHID) {
    	return service.getById(logLlamadasBHID);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public List<LogLlamadasBH> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public LogLlamadasBH getByOid(LogLlamadasBHOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public LogLlamadasBH getByOIDWithHV(LogLlamadasBHOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetLogLlamadasBHDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public InstanceResponseViewModel getLogLlamadasBHDynamic(QueryRequest request) {
        LogLlamadasBHOid oid = new LogLlamadasBHOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getLogLlamadasBHDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PopulationResponseViewModel getLogLlamadasBHPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/create_instance")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public ServiceResponse logLlamadasBHCreateinstance(CreateinstanceRequest request) {
        return service.createinstance(request);
    }
    

    @POST
    @Path(value = "/delete_instance")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse logLlamadasBHDeleteinstance(DeleteinstanceRequest request) {
        return service.deleteinstance(request);
    }
    

    @POST
    @Path(value = "/IIU_LogLlamadasBH")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public DSLogLlamadasBHIIU iiuIIULogLlamadasBH(LogLlamadasBHOid oid) {
        return service.iIULogLlamadasBH(oid);
    }

    @POST
    @Path(value = "/PIU_LogLlamadasBH")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo","UsuarioSistemaExterno"})
    public PIULogLlamadasBHResponse piuPIULogLlamadasBH(PIULogLlamadasBHRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIULogLlamadasBHResponse response;
        try
        {
            response = new PIULogLlamadasBHResponse(service.piuPIULogLlamadasBH(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIULogLlamadasBH(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIULogLlamadasBHResponse();
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
