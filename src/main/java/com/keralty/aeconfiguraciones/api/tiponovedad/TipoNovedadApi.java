package com.keralty.aeconfiguraciones.api.tiponovedad;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.TipoNovedadOid;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;
import com.keralty.aeconfiguraciones.service.TipoNovedadService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import com.keralty.aeconfiguraciones.viewmodel.tiponovedad.piu.PIUTipoNovedadNullResponse;
import com.keralty.aeconfiguraciones.viewmodel.tiponovedad.piu.PIUTipoNovedadResponse;
import com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu.ModificarRequest;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/TipoNovedadApi")
@RequestScoped
public class TipoNovedadApi {

    TipoNovedadService service;

    public TipoNovedadApi(TipoNovedadService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{tipoNovedadTipo}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public TipoNovedad getInstance(Long tipoNovedadTipo) {
    	return service.getById(tipoNovedadTipo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<TipoNovedad> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getTipoNovedadPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public TipoNovedad getByOid(TipoNovedadOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public TipoNovedad getByOIDWithHV(TipoNovedadOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetTipoNovedadDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getTipoNovedadDynamic(QueryRequest request) {
        TipoNovedadOid oid = new TipoNovedadOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getTipoNovedadDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getTipoNovedadWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getTipoNovedadPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tipoNovedadCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tipoNovedadEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tipoNovedadModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    


    @POST
    @Path(value = "/PIU_TipoNovedad")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public PIUTipoNovedadResponse piuPIUTipoNovedad(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUTipoNovedadResponse response;
        try
        {
            response = new PIUTipoNovedadResponse(service.piuPIUTipoNovedad(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUTipoNovedad(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUTipoNovedadResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_TipoNovedad_Null")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","Afiliaciones","Gestor","MesaDeApoyo"})
    public PIUTipoNovedadNullResponse piuPIUTipoNovedadNull(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUTipoNovedadNullResponse response;
        try
        {
            response = new PIUTipoNovedadNullResponse(service.piuPIUTipoNovedadNull(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUTipoNovedadNull(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUTipoNovedadNullResponse();
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
