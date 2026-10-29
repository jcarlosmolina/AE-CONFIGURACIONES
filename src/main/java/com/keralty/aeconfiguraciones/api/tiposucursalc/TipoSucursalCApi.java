package com.keralty.aeconfiguraciones.api.tiposucursalc;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.TipoSucursalCOid;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;
import com.keralty.aeconfiguraciones.service.TipoSucursalCService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.piu.PIUTipoSucursalCResponse;
import com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.DelTiposSucursalCRequest;
import com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.InsTiposSucursalCRequest;
import com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.ModificarRequest;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/TipoSucursalCApi")
@RequestScoped
public class TipoSucursalCApi {

    TipoSucursalCService service;

    public TipoSucursalCApi(TipoSucursalCService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{tipoSucursalCCodigo}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public TipoSucursalC getInstance(Long tipoSucursalCCodigo) {
    	return service.getById(tipoSucursalCCodigo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<TipoSucursalC> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public TipoSucursalC getByOid(TipoSucursalCOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public TipoSucursalC getByOIDWithHV(TipoSucursalCOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetTipoSucursalCDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getTipoSucursalCDynamic(QueryRequest request) {
        TipoSucursalCOid oid = new TipoSucursalCOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getTipoSucursalCDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getTipoSucursalCPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"Admin","SuperAdmin","Asesor"})
    public ServiceResponse tipoSucursalCCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"Admin","SuperAdmin","Asesor"})
    public ServiceResponse tipoSucursalCEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/eliminarCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","Asesor"})
    public ServiceResponse tipoSucursalCEliminarCheckPreconditions(EliminarRequest request) {

        return service.eliminarCheckPreconditions(request);
    }

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin","Asesor"})
    public ServiceResponse tipoSucursalCModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    

    @POST
    @Path(value = "/InsTiposSucursalC")
    @RolesAllowed({"Admin","SuperAdmin","Asesor"})
    public ServiceResponse tipoSucursalCInsTiposSucursalC(InsTiposSucursalCRequest request) {
        return service.insTiposSucursalC(request);
    }
    

    @POST
    @Path(value = "/DelTiposSucursalC")
    @RolesAllowed({"Admin","SuperAdmin","Asesor"})
    public ServiceResponse tipoSucursalCDelTiposSucursalC(DelTiposSucursalCRequest request) {
        return service.delTiposSucursalC(request);
    }
    


    @POST
    @Path(value = "/PIU_TipoSucursalC")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PIUTipoSucursalCResponse piuPIUTipoSucursalC(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUTipoSucursalCResponse response;
        try
        {
            response = new PIUTipoSucursalCResponse(service.piuPIUTipoSucursalC(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUTipoSucursalC(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUTipoSucursalCResponse();
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
