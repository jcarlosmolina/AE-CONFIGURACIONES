package com.keralty.aeconfiguraciones.api.tipoidentificacion;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.service.TipoIdentificacionService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.piu.PIUTipoIdentificacionNullResponse;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.piu.PIUTipoIdentificacionResponse;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdCDUsuNovedadRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentContratanteRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentificacionRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentUsuNovedadRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdCDUsuNovedadRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentContratanteRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentificacionRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentUsuNovedadRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.ModificarRequest;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/TipoIdentificacionApi")
@RequestScoped
public class TipoIdentificacionApi {

    TipoIdentificacionService service;

    public TipoIdentificacionApi(TipoIdentificacionService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{tipoIdentificacionCodigo}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public TipoIdentificacion getInstance(Long tipoIdentificacionCodigo) {
    	return service.getById(tipoIdentificacionCodigo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<TipoIdentificacion> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getTipoIdentificacionPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public TipoIdentificacion getByOid(TipoIdentificacionOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public TipoIdentificacion getByOIDWithHV(TipoIdentificacionOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetTipoIdentificacionDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getTipoIdentificacionDynamic(QueryRequest request) {
        TipoIdentificacionOid oid = new TipoIdentificacionOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getTipoIdentificacionDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getTipoIdentificacionWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getTipoIdentificacionPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tipoIdentificacionCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tipoIdentificacionEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tipoIdentificacionModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    

    @POST
    @Path(value = "/InsTipoIdentContratante")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tipoIdentificacionInsTipoIdentContratante(InsTipoIdentContratanteRequest request) {
        return service.insTipoIdentContratante(request);
    }
    

    @POST
    @Path(value = "/DelTipoIdentContratante")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tipoIdentificacionDelTipoIdentContratante(DelTipoIdentContratanteRequest request) {
        return service.delTipoIdentContratante(request);
    }
    

    @POST
    @Path(value = "/InsTipoIdentificacion")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tipoIdentificacionInsTipoIdentificacion(InsTipoIdentificacionRequest request) {
        return service.insTipoIdentificacion(request);
    }
    

    @POST
    @Path(value = "/DelTipoIdentificacion")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tipoIdentificacionDelTipoIdentificacion(DelTipoIdentificacionRequest request) {
        return service.delTipoIdentificacion(request);
    }
    

    @POST
    @Path(value = "/InsTipoIdentUsuNovedad")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tipoIdentificacionInsTipoIdentUsuNovedad(InsTipoIdentUsuNovedadRequest request) {
        return service.insTipoIdentUsuNovedad(request);
    }
    

    @POST
    @Path(value = "/DelTipoIdentUsuNovedad")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tipoIdentificacionDelTipoIdentUsuNovedad(DelTipoIdentUsuNovedadRequest request) {
        return service.delTipoIdentUsuNovedad(request);
    }
    

    @POST
    @Path(value = "/InsTipoIdCDUsuNovedad")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tipoIdentificacionInsTipoIdCDUsuNovedad(InsTipoIdCDUsuNovedadRequest request) {
        return service.insTipoIdCDUsuNovedad(request);
    }
    

    @POST
    @Path(value = "/DelTipoIdCDUsuNovedad")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tipoIdentificacionDelTipoIdCDUsuNovedad(DelTipoIdCDUsuNovedadRequest request) {
        return service.delTipoIdCDUsuNovedad(request);
    }
    


    @POST
    @Path(value = "/PIU_TipoIdentificacion")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUTipoIdentificacionResponse piuPIUTipoIdentificacion(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUTipoIdentificacionResponse response;
        try
        {
            response = new PIUTipoIdentificacionResponse(service.piuPIUTipoIdentificacion(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUTipoIdentificacion(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUTipoIdentificacionResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_TipoIdentificacion_Null")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUTipoIdentificacionNullResponse piuPIUTipoIdentificacionNull(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUTipoIdentificacionNullResponse response;
        try
        {
            response = new PIUTipoIdentificacionNullResponse(service.piuPIUTipoIdentificacionNull(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUTipoIdentificacionNull(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUTipoIdentificacionNullResponse();
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
