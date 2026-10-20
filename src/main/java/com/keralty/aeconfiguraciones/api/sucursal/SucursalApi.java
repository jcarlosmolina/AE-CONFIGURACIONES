package com.keralty.aeconfiguraciones.api.sucursal;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.SucursalOid;
import com.keralty.aeconfiguraciones.persistence.Sucursal;
import com.keralty.aeconfiguraciones.service.SucursalService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import com.keralty.aeconfiguraciones.viewmodel.sucursal.piu.PIUSucursalObsResponse;
import com.keralty.aeconfiguraciones.viewmodel.sucursal.piu.PIUSucursalResponse;
import com.keralty.aeconfiguraciones.viewmodel.sucursal.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.sucursal.siu.EliminarRequest;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/SucursalApi")
@RequestScoped
public class SucursalApi {

    SucursalService service;

    public SucursalApi(SucursalService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{sucursalCodigo}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Sucursal getInstance(Long sucursalCodigo) {
    	return service.getById(sucursalCodigo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<Sucursal> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getSucursalPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Sucursal getByOid(SucursalOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Sucursal getByOIDWithHV(SucursalOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetSucursalDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getSucursalDynamic(QueryRequest request) {
        SucursalOid oid = new SucursalOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getSucursalDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getSucursalWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getSucursalPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse sucursalCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse sucursalEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    


    @POST
    @Path(value = "/PIU_Sucursal")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUSucursalResponse piuPIUSucursal(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUSucursalResponse response;
        try
        {
            response = new PIUSucursalResponse(service.piuPIUSucursal(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUSucursal(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUSucursalResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_Sucursal_Obs")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUSucursalObsResponse piuPIUSucursalObs(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUSucursalObsResponse response;
        try
        {
            response = new PIUSucursalObsResponse(service.piuPIUSucursalObs(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUSucursalObs(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUSucursalObsResponse();
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
