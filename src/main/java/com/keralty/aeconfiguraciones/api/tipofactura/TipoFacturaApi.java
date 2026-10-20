package com.keralty.aeconfiguraciones.api.tipofactura;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.TipoFacturaOid;
import com.keralty.aeconfiguraciones.persistence.TipoFactura;
import com.keralty.aeconfiguraciones.service.TipoFacturaService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import com.keralty.aeconfiguraciones.viewmodel.tipofactura.piu.PIUTipoFacturaResponse;
import com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu.ModificarRequest;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/TipoFacturaApi")
@RequestScoped
public class TipoFacturaApi {

    TipoFacturaService service;

    public TipoFacturaApi(TipoFacturaService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{tipoFacturaCodigo}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Gestor","MesaDeApoyo"})
    public TipoFactura getInstance(String tipoFacturaCodigo) {
    	return service.getById(tipoFacturaCodigo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Gestor","MesaDeApoyo"})
    public List<TipoFactura> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getTipoFacturaPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Gestor","MesaDeApoyo"})
    public TipoFactura getByOid(TipoFacturaOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Gestor","MesaDeApoyo"})
    public TipoFactura getByOIDWithHV(TipoFacturaOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetTipoFacturaDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getTipoFacturaDynamic(QueryRequest request) {
        TipoFacturaOid oid = new TipoFacturaOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getTipoFacturaDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getTipoFacturaWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getTipoFacturaPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tipoFacturaCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tipoFacturaEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tipoFacturaModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    


    @POST
    @Path(value = "/PIU_TipoFactura")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Gestor","MesaDeApoyo"})
    public PIUTipoFacturaResponse piuPIUTipoFactura(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUTipoFacturaResponse response;
        try
        {
            response = new PIUTipoFacturaResponse(service.piuPIUTipoFactura(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUTipoFactura(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUTipoFacturaResponse();
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
