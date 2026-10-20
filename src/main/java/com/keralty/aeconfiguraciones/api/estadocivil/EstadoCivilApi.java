package com.keralty.aeconfiguraciones.api.estadocivil;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoCivilOid;
import com.keralty.aeconfiguraciones.service.EstadoCivilService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.estadocivil.piu.PIUEstadoCivilResponse;
import com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.DelEstadoCivilCRequest;
import com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.InsEstadoCivilCRequest;
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

@Path("/api/EstadoCivilApi")
@RequestScoped
public class EstadoCivilApi {

    EstadoCivilService service;

    public EstadoCivilApi(EstadoCivilService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{estadoCivilCodigo}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public EstadoCivil getInstance(Long estadoCivilCodigo) {
    	return service.getById(estadoCivilCodigo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<EstadoCivil> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getEstadoCivilPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public EstadoCivil getByOid(EstadoCivilOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public EstadoCivil getByOIDWithHV(EstadoCivilOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetEstadoCivilDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getEstadoCivilDynamic(QueryRequest request) {
        EstadoCivilOid oid = new EstadoCivilOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getEstadoCivilDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getEstadoCivilWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getEstadoCivilPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse estadoCivilCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse estadoCivilEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/InsEstadoCivilC")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse estadoCivilInsEstadoCivilC(InsEstadoCivilCRequest request) {
        return service.insEstadoCivilC(request);
    }
    

    @POST
    @Path(value = "/DelEstadoCivilC")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse estadoCivilDelEstadoCivilC(DelEstadoCivilCRequest request) {
        return service.delEstadoCivilC(request);
    }
    


    @POST
    @Path(value = "/PIU_EstadoCivil")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PIUEstadoCivilResponse piuPIUEstadoCivil(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUEstadoCivilResponse response;
        try
        {
            response = new PIUEstadoCivilResponse(service.piuPIUEstadoCivil(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUEstadoCivil(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUEstadoCivilResponse();
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
