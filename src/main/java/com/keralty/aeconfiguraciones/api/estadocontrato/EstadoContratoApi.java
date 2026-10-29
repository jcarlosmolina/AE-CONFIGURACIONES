package com.keralty.aeconfiguraciones.api.estadocontrato;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoContratoOid;
import com.keralty.aeconfiguraciones.service.EstadoContratoService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.estadocontrato.piu.PIUEstadoContratoResponse;
import com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.DelEstadoContratoCRequest;
import com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.DelEstadoContratoRequest;
import com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.InsEstadoContratoCRequest;
import com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.InsEstadoContratoRequest;
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

@Path("/api/EstadoContratoApi")
@RequestScoped
public class EstadoContratoApi {

    EstadoContratoService service;

    public EstadoContratoApi(EstadoContratoService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{estadoContratoCodigo}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public EstadoContrato getInstance(Long estadoContratoCodigo) {
    	return service.getById(estadoContratoCodigo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<EstadoContrato> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getEstadoContratoPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public EstadoContrato getByOid(EstadoContratoOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public EstadoContrato getByOIDWithHV(EstadoContratoOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetEstadoContratoDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getEstadoContratoDynamic(QueryRequest request) {
        EstadoContratoOid oid = new EstadoContratoOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getEstadoContratoDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getEstadoContratoWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getEstadoContratoPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse estadoContratoCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse estadoContratoEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/InsEstadoContratoC")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse estadoContratoInsEstadoContratoC(InsEstadoContratoCRequest request) {
        return service.insEstadoContratoC(request);
    }
    

    @POST
    @Path(value = "/DelEstadoContratoC")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse estadoContratoDelEstadoContratoC(DelEstadoContratoCRequest request) {
        return service.delEstadoContratoC(request);
    }
    

    @POST
    @Path(value = "/InsEstadoContrato")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse estadoContratoInsEstadoContrato(InsEstadoContratoRequest request) {
        return service.insEstadoContrato(request);
    }
    

    @POST
    @Path(value = "/DelEstadoContrato")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse estadoContratoDelEstadoContrato(DelEstadoContratoRequest request) {
        return service.delEstadoContrato(request);
    }
    


    @POST
    @Path(value = "/PIU_EstadoContrato")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PIUEstadoContratoResponse piuPIUEstadoContrato(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUEstadoContratoResponse response;
        try
        {
            response = new PIUEstadoContratoResponse(service.piuPIUEstadoContrato(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUEstadoContrato(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUEstadoContratoResponse();
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
