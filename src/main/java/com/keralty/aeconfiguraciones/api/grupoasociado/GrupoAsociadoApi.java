package com.keralty.aeconfiguraciones.api.grupoasociado;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsociadoOid;
import com.keralty.aeconfiguraciones.service.GrupoAsociadoService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.grupoasociado.piu.PIUGrupoAsociadoBusquedaRequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasociado.piu.PIUGrupoAsociadoBusquedaResponse;
import com.keralty.aeconfiguraciones.viewmodel.grupoasociado.piu.PIUGrupoAsociadoRequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasociado.piu.PIUGrupoAsociadoResponse;
import com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu.DelGrupoAsociadoRequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu.InsGrupoAsociadoRequest;
import com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu.ModificarRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
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

@Path("/api/GrupoAsociadoApi")
@RequestScoped
public class GrupoAsociadoApi {

    GrupoAsociadoService service;

    public GrupoAsociadoApi(GrupoAsociadoService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{grupoAsociadoCodigo}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public GrupoAsociado getInstance(Long grupoAsociadoCodigo) {
    	return service.getById(grupoAsociadoCodigo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<GrupoAsociado> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getGrupoAsociadoPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public GrupoAsociado getByOid(GrupoAsociadoOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public GrupoAsociado getByOIDWithHV(GrupoAsociadoOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetGrupoAsociadoDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getGrupoAsociadoDynamic(QueryRequest request) {
        GrupoAsociadoOid oid = new GrupoAsociadoOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getGrupoAsociadoDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getGrupoAsociadoWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getGrupoAsociadoPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse grupoAsociadoCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse grupoAsociadoEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse grupoAsociadoModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    

    @POST
    @Path(value = "/InsGrupoAsociado")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse grupoAsociadoInsGrupoAsociado(InsGrupoAsociadoRequest request) {
        return service.insGrupoAsociado(request);
    }
    

    @POST
    @Path(value = "/DelGrupoAsociado")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse grupoAsociadoDelGrupoAsociado(DelGrupoAsociadoRequest request) {
        return service.delGrupoAsociado(request);
    }
    


    @POST
    @Path(value = "/PIU_GrupoAsociado")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUGrupoAsociadoResponse piuPIUGrupoAsociado(PIUGrupoAsociadoRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUGrupoAsociadoResponse response;
        try
        {
            response = new PIUGrupoAsociadoResponse(service.piuPIUGrupoAsociado(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUGrupoAsociado(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUGrupoAsociadoResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_GrupoAsociadoBusqueda")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUGrupoAsociadoBusquedaResponse piuPIUGrupoAsociadoBusqueda(PIUGrupoAsociadoBusquedaRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUGrupoAsociadoBusquedaResponse response;
        try
        {
            response = new PIUGrupoAsociadoBusquedaResponse(service.piuPIUGrupoAsociadoBusqueda(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUGrupoAsociadoBusqueda(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUGrupoAsociadoBusquedaResponse();
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
